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

    @Override
    public void run() {
        Socket connection = this.conn;
        try {
            for (;;) {
                Thread.sleep(4000);

                var writer = new OutputStreamWriter(connection.getOutputStream());
                this.lamportClock.eventOccurred(0);
                writer.write("" + this.lamportClock.getLogicalClock() + "\n" + "Hello From Node A");
                writer.flush();
                System.out.println("Message Sent!!");
                connection.close();
                
                connection = new Socket(this.conn.getLocalAddress().getHostAddress(), this.conn.getPort());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("ErroreA");
            System.out.println(e);
        }
    }    
}
