package nodes.node2;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// BNode has to send messages to NodeA and NodeC

public class BNode {
    private static final int LISTEN_PORT = 3032;
    private static final int THREADS = 200;
    public static void main(String... args) {
        LamportServerSender[] senders = new LamportServerSender[2];
        BNodeServerListener lamport = new BNodeServerListener(LISTEN_PORT, THREADS);
        lamport.start();

        try {
            senders[0] = new LamportServerSender(new Socket(NodeSystem.NODEA.getNodeHost(), NodeSystem.NODEA.getNodePort()), 0);
            senders[0].start();
            senders[1] = new LamportServerSender(new Socket(NodeSystem.NODEC.getNodeHost(), NodeSystem.NODEC.getNodePort()), 1);
            senders[1].start();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        try {
            lamport.join();
            senders[0].join();
            senders[1].join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
