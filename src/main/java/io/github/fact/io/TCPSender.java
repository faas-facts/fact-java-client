package io.github.fact.io;

import io.github.fact.FactIO;
import io.github.fact.Trace;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class TCPSender implements FactIO {


    private String address;
    private int port;
    private boolean connected = false;
    @Override
    public boolean isConnected() {
        return connected;
    }

    /**
     * Warning @param environment should contain `fact_tcp_address` and `fact_tcp_port`.
     */
    @Override
    public void connect(Map<String, String> environment) {
        try {
            address = environment.get("fact_tcp_address");
            port = Integer.parseInt(environment.getOrDefault("fact_tcp_port", "9999"));
            connected = true;
        } catch (NumberFormatException | NullPointerException ignored){}
    }

    @Override
    public void send(String message, Trace trace) throws IOException {
        try (Socket s = new Socket(address,port)){
            OutputStream outputStream = s.getOutputStream();
            Trace build = trace.toBuilder().putLogs(System.currentTimeMillis(), message).build();
            build.writeTo(outputStream);
            outputStream.flush();
        }
    }


}
