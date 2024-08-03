package nodes.node1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// node A must send few messages to node B and C

public class ANode {
    private static final int LISTEN_PORT = 3030;
    private static final int THREADS = 200;
    public static void main(String... args) {
        LamportServerSender[] senders = new LamportServerSender[2];
        ANodeServerListener lamport = new ANodeServerListener(LISTEN_PORT, THREADS);
        // Node A is now listening at the requestss
        lamport.start();
        
        try {
            senders[0] = new LamportServerSender(new Socket(NodeSystem.NODEB.getNodeHost(), NodeSystem.NODEB.getNodePort()), 0);
            senders[0].start();
            senders[1] = new LamportServerSender(new Socket(NodeSystem.NODEC.getNodeHost(), NodeSystem.NODEC.getNodePort()), 1);
            senders[1].start();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        try {
            lamport.join();
            for (int i = 0; i < senders.length; i++) {
                senders[i].join();;
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

    }
}