package nodes.node3;

import java.io.IOException;
import java.net.Socket;

public class CNode {
    private static final int LISTEN_PORT = 3034;
    private static final int THREADS = 200;
    public static void main(String... args) {
        LamportServerSender senders = null;
        CNodeLamportServer lamport = new CNodeLamportServer(LISTEN_PORT, THREADS);
        lamport.start();

        // Only for Sync.
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            senders = new LamportServerSender(new Socket(NodeSystem.NODEA.getNodeHost(), NodeSystem.NODEA.getNodePort()));
            senders.start();
        } catch (IOException ex) {
            System.out.println("errorC");
            System.out.println(ex);
        }

        try {
            lamport.join();
            senders.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
