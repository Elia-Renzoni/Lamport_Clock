package nodes.node2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BNodeLamportServer extends Thread {
    private int listenPort;
    private int threadPoolSize;

    public BNodeLamportServer(int port, int threads) {
        super("Listener");
        this.listenPort = port;
        this.threadPoolSize = threads;
    }

    public void run() {
        ExecutorService threadPool = Executors.newFixedThreadPool(this.threadPoolSize);

        try {
            ServerSocket conn = new ServerSocket(this.listenPort);
            System.out.println("B Node Server start to listen to the requests...");

            while (true) {
                Socket connection = conn.accept();
                LamportClock lamport = new LamportClock(connection);
                threadPool.submit(lamport);
            }
        } catch (IOException ex) {
            System.out.println(ex);;
        }
    }
}
