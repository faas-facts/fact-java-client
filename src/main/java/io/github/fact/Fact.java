package io.github.fact;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import io.github.fact.providers.AWSFact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Fact {

    private static Trace base;

    public enum Phase{
        provisioned,
        start,
        update,
        done
    }

    private final static UUID ContainerID = UUID.randomUUID();
    final static String RuntimeString =  System.getProperty("os.name") + " " + System.getProperty("os.version") + " " +
            "Java" + " " + System.getProperty("java.version");
    private static Provider provider;
    private static Trace.Builder trace;

    public static String readFile(String path){
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return "";
        }
    }

    public static class OperatingSystem {
        private static final String OS = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);
        public static final boolean isWindows = OS.contains("win");
        public static final boolean isMac = OS.contains("mac");
        public static final boolean isUnix = OS.contains("nux");
    }

    private static void fingerprint(Trace.Builder trace, Object context) {
        String aws_key = System.getenv("AWS_LAMBDA_LOG_STREAM_NAME");
        String gcf_key = System.getenv("X_GOOGLE_FUNCTION_NAME");
        String ow_key = System.getenv("__OW_ACTION_NAME");
        String acf_key = System.getenv("WEBSITE_HOSTNAME");


        if (aws_key != null){
            AWSFact.getInstances().init(trace,context);
            provider = Provider.AWS;
        } else if(gcf_key != null) {
            //TODO GCF.init
            provider = Provider.GCF;
        } else if(acf_key != null) {
            //TODO ACF.init
            provider = Provider.ACF;
        } else if(ow_key != null) {
            //TODO OW.init
            if (Files.exists(Path.of("/sys/hypervisor/uuid"))){
                provider = Provider.ICF;
            } else {
                provider = Provider.OWk;
            }
        } else {
            //TODO OW.init
            provider = Provider.UND;
            trace.setPlatform(provider.name());
            if(!OperatingSystem.isWindows) {
                String uptime = readFile("/proc/uptime").trim();
                trace.putTags("uptime", uptime);
                trace.setHostID("U"+uptime);
            }
        }
    }
    private static Timestamp now() {
        return Timestamp.newBuilder().setSeconds(Calendar.getInstance().getTime().getTime()).build();
    }

    public static FactConfiguration conf;

    static void send(Phase message){
        Trace trace = Fact.trace.build();
        try {
            conf.getIo().send(message.name(),trace);
        } catch (IOException e) {
            if(conf.isIncludeEnviroment()){
                System.err.printf("failed to send %s:%s - %s\n",message,trace,e.getMessage());
            } else {
                System.err.printf("failed to send %s:%s - %s\n",message,trace.getID(),e.getMessage());
            }

        }
    }


    public static void boot(FactConfiguration configuration) {
        //init Fact
        Fact.conf = configuration;
        Fact.trace = Trace.newBuilder();

        //gather first facts
        if (conf.isIncludeEnviroment()) {
            trace.putAllEnv(System.getenv());
        }
        trace.setBootTime(now());
        trace.setContainerID(ContainerID.toString());
        trace.setRuntime(RuntimeString);
        trace.setTimestamp(trace.getBootTime());
        
        if (!conf.isLazyLoading()) {
            load(null);
            if (conf.isSendOnUpdate()){
                send(Phase.provisioned);
            }
        }
        Fact.base = trace.build();
    }

    private static void load(Object context) {
        fingerprint(trace,context);
        conf.getIo().connect(System.getenv());
    }

    public static long startTime;

    public static void start(Object context, Object event){
        Fact.trace = Trace.newBuilder();
        trace.mergeFrom(Fact.base);

        startTime = System.currentTimeMillis();
        trace.setID(UUID.randomUUID().toString());
        trace.setStartTime(now());
        if (provider == null){
            load(context);
        }
        assert provider != null;
        switch (provider){
            case AWS:
                AWSFact.getInstances().collect(trace,context);
                break;


        }
        if (conf.isSendOnUpdate()){
            send(Phase.start);
        }
    }

    public static void setParent(String parentID){
        trace.setChildOf(parentID);
    }

    public static void update(Object context, String message, Map<String,String> tags){
        assert provider != null;
        assert conf.getIo().isConnected();

        trace.putLogs(System.currentTimeMillis(),message);
        trace.putAllTags(tags);
        switch (provider){
            case AWS:
                AWSFact.getInstances().collect(trace,context);
                break;

        }
        if (conf.isSendOnUpdate()){
            send(Phase.update);
        }
    }

    public static void done(Object context, String message, String ... args){
        assert provider != null;
        assert conf.getIo().isConnected();

        trace.setEndTime(now());
        trace.putLogs(System.currentTimeMillis(),message);
        trace.addAllArgs(Arrays.asList(args));
        //got to love java so short much wow!
        trace.setExecutionLatency(Duration.newBuilder().setSeconds(trace.getEndTime().getSeconds()-trace.getStartTime().getSeconds()).build());
        switch (provider){
            case AWS:
                AWSFact.getInstances().collect(trace,context);
                break;

        }


        send(Phase.done);


    }

    public static String getID(){
        return trace.getID();
    }

    public void setChildOf(String id){
        trace.setChildOf(id);
    }
}
