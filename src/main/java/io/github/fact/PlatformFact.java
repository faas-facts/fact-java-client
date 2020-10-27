package io.github.fact;

public interface PlatformFact {
    void init(Trace.Builder trace);
    void init(Trace.Builder trace, Object context);
    void collect(Trace.Builder trace, Object context);
}
