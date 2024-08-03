package nodes.node3;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LamportServerSender extends Thread {
    private Socket conn;
    private LamportClock lamport;

    public LamportServerSender(final Socket connection) {
        super("Sender");
        this.conn = connection;
        this.lamport = LamportClock.getInstance();
    }

    public void run() {
        try {
            for (;;) {
                Thread.sleep(5000);
                
                var writer = new OutputStreamWriter(this.conn.getOutputStream());
                this.lamport.eventOccurred(0);
                writer.write("" + this.lamport.getLogicalClock() + " \n" + " Hello From Node C");
                writer.flush();
            }
        } catch (InterruptedException | IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                this.conn.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
