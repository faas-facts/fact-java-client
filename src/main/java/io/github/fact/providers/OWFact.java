package io.github.fact.providers;

import io.github.fact.Fact;
import io.github.fact.PlatformFact;
import io.github.fact.Provider;
import io.github.fact.Trace;

import static io.github.fact.Fact.readFile;

public class OWFact implements PlatformFact {
    @Override
    public void init(Trace.Builder trace) {
        init(trace,null);
    }

    @Override
    public void init(Trace.Builder trace, Object context) {
        trace.setPlatform(Provider.OWk.name());
        trace.setRegion(System.getenv("__OW_API_HOST"));
        trace.putTags("uptime",readFile("/proc/uptime").trim());
        trace.putTags("fname",System.getenv("__OW_ACTION_NAME"));
        trace.setHostID(readFile("/sys/hypervisor/uuid").trim());
        trace.setMemory(Math.toIntExact(Math.floorDiv(Runtime.getRuntime().maxMemory(), (int) 1e-6)));
    }

    @Override
    public void collect(Trace.Builder trace, Object context) {
        trace.setMemory(Math.toIntExact(Math.floorDiv(Runtime.getRuntime().maxMemory(), (int) 1e-6)));
    }
}
