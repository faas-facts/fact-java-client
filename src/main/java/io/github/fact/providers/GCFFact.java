package io.github.fact.providers;

import com.amazonaws.services.lambda.runtime.Context;
import io.github.fact.Fact;
import io.github.fact.PlatformFact;
import io.github.fact.Provider;
import io.github.fact.Trace;

import java.io.IOException;

import static io.github.fact.Fact.readFile;

public class GCFFact implements PlatformFact {
    enum GCFCost {
        _128(0.000000231),_256(0.000000463),_512(0.000000925),_1024(0.000001650),
        _2048	(0.000002900),
        _4096	(0.000005800);

        double costPer100MS;
        GCFCost(double cost){
            this.costPer100MS = cost;
        }

        static GCFCost getCloses(int mb){
            if(mb <= 128){
                return _128;
            } else if(mb <= 256){
                return _256;
            } else if(mb <= 512){
                return _512;
            } else if(mb <= 1024){
                return _1024;
            }  else if(mb <= 2048){
                return _2048;
            } else {
                return _4096;
            }
        }
    }
    /**
     * 128MB 	200MHz 	$0.000000231
     * 256MB 	400MHz 	$0.000000463
     * 512MB 	800MHz 	$0.000000925
     * 1024MB 	1.4 GHz 	$0.000001650
     * 2048MB 	2.4 GHz 	$0.000002900
     * 4096MB 	4.8 GHz 	$0.000005800
     * @param trace
     */

    @Override
    public void init(Trace.Builder trace) {
        init(trace,null);
    }

    @Override
    public void init(Trace.Builder trace, Object context) {
        trace.putTags("service",System.getenv("SUPERVISOR_HOSTNAME"));
        trace.setPlatform(Provider.GCF.name());

        trace.setRegion(System.getenv("X_GOOGLE_FUNCTION_REGION"));
        String uptime = readFile("/proc/uptime").trim();
        trace.putTags("uptime",uptime);
        trace.putTags("fname",System.getenv("X_GOOGLE_FUNCTION_NAME"));
        trace.putTags("service",System.getenv("X_GOOGLE_SUPERVISOR_HOSTNAME"));
        trace.setMemory(Integer.parseInt(System.getenv("X_GOOGLE_FUNCTION_MEMORY_MB")));

        String host = uptime.split(" ")[0].split("\\.")[0];
        trace.setHostID(host);

    }

    @Override
    public void collect(Trace.Builder trace, Object context) {
    
        double elat = Math.floorDiv((System.currentTimeMillis() - Fact.startTime) ,100L);
        int mem = Integer.parseInt(System.getenv("X_GOOGLE_FUNCTION_MEMORY_MB"));
        trace.setCost(Double.valueOf(GCFCost.getCloses(mem).costPer100MS*elat).floatValue());

    }
}
