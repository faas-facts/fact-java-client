package io.github.fact;

import java.io.IOException;
import java.util.Map;

public interface FactIO {
    boolean isConnected();

    void connect(Map<String,String> enviroment);

    void send(String message, Trace trace) throws IOException;
}
