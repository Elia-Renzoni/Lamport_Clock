package nodes.node1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ANodeServerListener extends Thread {
    private int listenPort;
    private int threadPoolSize;

    public ANodeServerListener(final int port, final int threads) {
        super("Listener");
        this.listenPort = port;
        this.threadPoolSize = threads;
    } 

    @Override
    public void run() {
        ExecutorService threadPool = Executors.newFixedThreadPool(this.threadPoolSize);

        try {
            ServerSocket conn = new ServerSocket(this.listenPort);
            System.out.println("A Node Server start to listen to the requests...");
            while (true) {
                Socket connection = conn.accept();
                System.out.println("Accepted!");
                LamportServer thread = new LamportServer(connection);
                threadPool.submit(thread);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}