package io.github.fact.providers;

import com.amazonaws.services.lambda.runtime.Context;
import io.github.fact.Fact;
import io.github.fact.PlatformFact;
import io.github.fact.Provider;
import io.github.fact.Trace;

import java.io.IOException;

import static io.github.fact.Fact.readFile;

public class ACFFact implements PlatformFact {
    @Override
    public void init(Trace.Builder trace) {
        init(trace,null);
    }

    @Override
    public void init(Trace.Builder trace, Object context) {
        trace.setPlatform(Provider.ACF.name());
        trace.setContainerID(System.getenv("WEBSITE_HOSTNAME"));
        trace.setRegion(System.getenv("REGION_NAME"));
        trace.setHostID(System.getenv("COMPUTERNAME"));
        trace.putTags("service",System.getenv("WEBSITE_INSTANCE_ID"));
        trace.putTags("decrpytion_key",System.getenv("MACHINEKEY_DecryptionKey"));
        String uptime = readFile("/proc/uptime").trim();
        trace.putTags("uptime",uptime);

    }


    public void collect(Trace.Builder trace, Object context) {
        
    }
}
