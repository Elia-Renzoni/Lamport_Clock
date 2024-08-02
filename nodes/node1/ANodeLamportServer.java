package nodes.node1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ANodeLamportServer {
    private int listenPort;
    private int threadPoolSize;

    public ANodeLamportServer(final int port, final int threads) {
        this.listenPort = port;
        this.threadPoolSize = threads;
    } 

    public void start() {
        ExecutorService threadPool = Executors.newFixedThreadPool(this.threadPoolSize);

        try {
            ServerSocket conn = new ServerSocket(this.listenPort);
            System.out.println("A Node Server start to listen to the requests...");
            while (true) {
                Socket connection = conn.accept();
                LamportClock thread = new LamportClock(connection);
                threadPool.submit(thread);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}