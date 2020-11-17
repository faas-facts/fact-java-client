package io.github.fact;

public class FactConfiguration {

    private final boolean lazyLoading;
    private final FactIO io;
    private final boolean sendOnUpdate;

    public boolean isIncludeEnviroment() {
        return includeEnviroment;
    }

    private final boolean includeEnviroment;


    public FactConfiguration(boolean lazyLoading, FactIO io, boolean sendOnUpdate, boolean includeEnviroment) {
        this.lazyLoading = lazyLoading;
        this.io = io;
        this.sendOnUpdate = sendOnUpdate;
        this.includeEnviroment = includeEnviroment;
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

    @Override
    public String toString() {
        return "FactConfiguration{" +
                "lazyLoading=" + lazyLoading +
                ", io=" + io.getClass().getName() +
                ", sendOnUpdate=" + sendOnUpdate +
                ", includeEnviroment=" + includeEnviroment +
                '}';
    }
}
