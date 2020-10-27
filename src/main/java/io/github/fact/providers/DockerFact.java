package io.github.fact.providers;

import io.github.fact.PlatformFact;
import io.github.fact.Provider;
import io.github.fact.Trace;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static io.github.fact.Fact.readFile;

public class DockerFact implements PlatformFact {
    @Override
    public void init(Trace.Builder trace) {
        init(trace,null);
    }

    @Override
    public void init(Trace.Builder trace, Object context) {
        trace.setPlatform(Provider.Dok.name());
        try {
            trace.setContainerID(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {}
        
        String uptime = readFile("/proc/uptime").trim();
        trace.putTags("uptime",uptime);
        
        
    }

    @Override
    public void collect(Trace.Builder trace, Object context) {

    }
}
