package nodes.node1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// node A must send few messages to node B and C
// each node must receive 5 messages from node A

public class ANode {
    private static final int LISTEN_PORT = 3030;
    private static final int THREADS = 200;
    public static void main(String... args) {
        LamportServerSender[] senders = new LamportServerSender[10];
        ANodeServerListener lamport = new ANodeServerListener(LISTEN_PORT, THREADS);
        // Node A is now listening at the requestss
        lamport.start();
        
        // Node is now sendind some few messages
        for (int i = 0; i < senders.length; i++) {
            if (i < 5) {
                try {
                    senders[i] = new LamportServerSender(new Socket(NodeSystem.NODEB.getNodeHost(), NodeSystem.NODEB.getNodePort()), i);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    senders[i] = new LamportServerSender(new Socket(NodeSystem.NODEC.getNodeHost(), NodeSystem.NODEC.getNodePort()), i);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            senders[i].start();
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