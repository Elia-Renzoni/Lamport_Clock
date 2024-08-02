package nodes.node1;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LamportServerSender extends Thread {
    private Socket conn;
    private LamportClock lamportClock;

    public LamportServerSender(final Socket conn, final int i) {
        super("sender " + i);
        this.conn = conn;
        this.lamportClock = LamportClock.getLamportClock();
    }

    public void run() {
        try {
            var writer = new OutputStreamWriter(this.conn.getOutputStream());
            this.lamportClock.eventOccurred(0);
            writer.write("" + this.lamportClock.getLogicalClock() + "\n " + "Hello From Node A");
            writer.flush();
            System.out.println("Message Sent!!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }    
}
