package io.github.fact;

public class FactConfiguration {

    private final boolean lazyLoading;
    private final FactIO io;
    private final boolean sendOnUpdate;


    public FactConfiguration(boolean lazyLoading, FactIO io, boolean sendOnUpdate) {
        this.lazyLoading = lazyLoading;
        this.io = io;
        this.sendOnUpdate = sendOnUpdate;
    }

    public boolean isLazyLoading() {
        return lazyLoading;
    }

    public FactIO getIo() {
        return io;
    }

    public boolean isSendOnUpdate() {
        return sendOnUpdate;
    }
}
