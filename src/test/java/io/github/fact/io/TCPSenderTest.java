package io.github.fact.io;

import io.github.fact.Trace;
import junit.framework.TestCase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TCPSenderTest extends TestCase {

    class TestServer implements Runnable {
        ArrayList<Trace> traces = new ArrayList<>();
        ServerSocket soc;
        Thread thread;

        public void start() throws IOException {
            soc = new ServerSocket(9999);
            thread = new Thread(this);
            thread.start();
        }

        public Trace[] stop() throws InterruptedException {
            thread.interrupt();
            thread.join();
            return traces.toArray(new Trace[0]);
        }

        @Override
        public void run() {
            while (!Thread.interrupted()){
                try {
                    Socket socket = soc.accept();
                    Trace trace = Trace.parseFrom(socket.getInputStream());
                    traces.add(trace);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testConnection() throws IOException, InterruptedException {
        TestServer server = new TestServer();
        server.start();
        TCPSender sender = new TCPSender();
        Map<String,String> map = new HashMap<>();
        map.put("fact_tcp_address",null);
        map.put("fact_tcp_port","9999");

        sender.connect(map);

        Trace.Builder builder = Trace.newBuilder();
        builder.setID(UUID.randomUUID().toString());
        Trace trace = builder.build();
        sender.send("testing",trace);

        Trace[] received = server.stop();
        assertEquals(trace.getID(),received[0].getID());

    }

}