package nodes.node2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LamportServerSender extends Thread {
    private Socket conn;
    private LamportClock lamport;

    public LamportServerSender(final Socket connection, final int i) {
        super("Sender " + i);
        this.conn = connection;
        this.lamport = LamportClock.getInstance();
    }

    public void run() {
       try {
           for (;;) {
               Thread.sleep(8000);

               var writer = new OutputStreamWriter(this.conn.getOutputStream());
               this.lamport.eventOccurred(0);

               writer.write("" + this.lamport.getLogicalClock() + "\n" + " Hello From Node B!");
               writer.flush();
               System.out.println("Message Sent!");
           }
       } catch (IOException | InterruptedException ex) {
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

