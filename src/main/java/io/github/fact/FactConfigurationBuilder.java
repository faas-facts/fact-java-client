package io.github.fact;

import io.github.fact.io.ConsoleLogging;

public class FactConfigurationBuilder {
    private boolean lazyLoading;
    private FactIO io;
    private boolean sendOnUpdate;
    private boolean includeEnvironment = false;

    public FactConfigurationBuilder setLazyLoading(boolean lazyLoading) {
        this.lazyLoading = lazyLoading;
        return this;
    }

    public FactConfigurationBuilder setIo(FactIO io) {
        this.io = io;
        return this;
    }

    public FactConfigurationBuilder setToIncludeEnvironment() {
        this.includeEnvironment = true;
        return this;
    }

    public FactConfigurationBuilder setSendOnUpdate(boolean sendOnUpdate) {
        this.sendOnUpdate = sendOnUpdate;
        return this;
    }

    public FactConfiguration createFactConfiguration() {
        return new FactConfiguration(lazyLoading, io, sendOnUpdate, includeEnvironment);
    }

    public FactConfiguration createLazyLogger(){
        return new FactConfiguration(true,new ConsoleLogging(),true, false);
    }
}