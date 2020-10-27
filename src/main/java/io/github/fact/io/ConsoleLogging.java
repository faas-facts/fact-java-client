package io.github.fact.io;

import io.github.fact.FactIO;
import io.github.fact.Trace;

import java.io.IOException;
import java.util.Map;

public class ConsoleLogging implements FactIO {

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public void connect(Map<String, String> enviroment) {}

    @Override
    public void send(String message, Trace trace) throws IOException {
        System.out.printf("[%d] [%s] %s",System.currentTimeMillis(),message,trace.toString());
    }
}
