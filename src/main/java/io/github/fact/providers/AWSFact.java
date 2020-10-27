package io.github.fact.providers;

import com.amazonaws.services.lambda.runtime.Context;
import io.github.fact.Fact;
import io.github.fact.PlatformFact;
import io.github.fact.Provider;
import io.github.fact.Trace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static io.github.fact.Fact.readFile;

public class AWSFact implements PlatformFact {

    enum AWSLambdaCost {
        _128(0.0000002083),_512(0.0000008333),_1024(0.0000016667),
                _1536	(0.0000025000),
                _2048	(0.0000033333),
                _3008	(0.0000048958);

        double costPer100MS;
        AWSLambdaCost(double cost){
            this.costPer100MS = cost;
        }

        static AWSLambdaCost getCloses(int mb){
            if(mb <= 128){
                return _128;
            } else if(mb <= 512){
                return _512;
            } else if(mb <= 1024){
                return _1024;
            } else if(mb <= 1536){
                return _1536;
            } else if(mb <= 2048){
                return _2048;
            } else {
                return _3008;
            }
        }
    }

    final String path;

    public AWSFact(String path) {
        this.path = path;
    }

    public static AWSFact getInstances(){
        return getInstances(null);
    }

    public static AWSFact getInstances(String basePath){
        return new AWSFact(Objects.requireNonNullElse(basePath, "/proc/self/cgroup"));
    }


    private static final int freezerOffset = "freezer:/sandbox-".length();
    public void readCgroupIDs(Trace.Builder trace) throws IOException {
        List<String> cgroup = Files.readAllLines(Path.of(path));
        int found = 0;
        for (String line : cgroup) {
            int index;
            if ((index = line.indexOf("freezer")) > 0) {
                trace.putTags("freezer",line.substring(index+freezerOffset).trim());
                found++;
            }
            if((index = line.indexOf("sandbox-root-")) > 0){
                line = line.substring(index);

                assert line.length() >= 57;

                String host = line.substring(13,19);
                trace.putTags("host",host);
                trace.setHostID(host);
                trace.putTags("service",line.substring(36,42));
                trace.putTags("sandbox",line.substring(51,57));
                found++;
            }
            if (found >= 2){
                break;
            }
        }
    }

    public void init(Trace.Builder trace){
        init(trace,null);
    }
    @Override
    public void init(Trace.Builder trace, Object context) {
        String logName = System.getenv("AWS_LAMBDA_LOG_STREAM_NAME");
        trace.setPlatform(Provider.AWS.name());
        trace.setContainerID(logName);
        trace.setRegion(System.getenv("AWS_REGION"));
        String uptime = readFile("/proc/uptime").trim();
        trace.putTags("uptime",uptime);
        try {
            readCgroupIDs(trace);
        } catch (IOException e) {
            trace.putTags("host","U"+uptime);
            trace.setHostID("U"+uptime);
            trace.putTags("service","undefined");
            trace.putTags("sandbox","undefined");
            trace.putTags("freezer","undefined");
        }
        if (context != null){
            collect(trace,context);
            Context ctx = (Context) context;
        }
    }


    public void collect(Trace.Builder trace, Object context) {
        assert context != null;

        Context ctx = (Context) context;

        trace.setMemory(ctx.getMemoryLimitInMB());
        trace.putTags("fname",ctx.getFunctionName());
        trace.putTags("fver",ctx.getFunctionVersion());
        trace.putTags("rid",ctx.getAwsRequestId());
        trace.putLogs(System.currentTimeMillis(), "RenamingTime"+String.valueOf(ctx.getRemainingTimeInMillis()));
        if(ctx.getClientContext() != null) {
            trace.putAllEnv(ctx.getClientContext().getEnvironment());
            trace.putAllEnv(ctx.getClientContext().getCustom());
        }

        double elat = Math.floorDiv((System.currentTimeMillis() - Fact.startTime) ,100L);
        double cost = elat * AWSLambdaCost.getCloses(ctx.getMemoryLimitInMB()).costPer100MS;
        trace.setCost(Double.valueOf(cost).floatValue());
    }
}
