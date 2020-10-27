package io.github.fact.providers;

import com.amazonaws.services.lambda.runtime.Context;
import io.github.fact.Fact;
import io.github.fact.PlatformFact;
import io.github.fact.Provider;
import io.github.fact.Trace;

import java.io.IOException;
import static io.github.fact.Fact.readFile;
import static io.github.fact.Fact.readFile;

public class ICFFact implements PlatformFact {
    @Override
    public void init(Trace.Builder trace) {
        init(trace,null);
    }

    @Override
    public void init(Trace.Builder trace, Object context) {
        trace.setPlatform(Provider.ICF.name());
        trace.setRegion(System.getenv("__OW_API_HOST"));
        trace.putTags("uptime",readFile("/proc/uptime").trim());
        trace.putTags("fname",System.getenv("__OW_ACTION_NAME"));
        trace.setHostID(readFile("/sys/hypervisor/uuid").trim());
        trace.setMemory(Math.toIntExact(Math.floorDiv(Runtime.getRuntime().maxMemory(), (int) 1e-6)));
    }

    @Override
    public void collect(Trace.Builder trace, Object context) {
        int memInMB = Math.toIntExact(Math.floorDiv(Runtime.getRuntime().maxMemory(), (int) 1e-6));
        trace.setMemory(memInMB);
        double elat = Math.floorDiv((System.currentTimeMillis() - Fact.startTime) ,1000L)+1;

        double cost = Math.floorDiv(memInMB, 1024) * 0.000017 * elat;
        trace.setCost(Double.valueOf(cost).floatValue());
    }
}
