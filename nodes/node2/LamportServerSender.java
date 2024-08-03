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

    @Override
    public void run() {
       Socket connection = this.conn;
       try {
           for (;;) {
               Thread.sleep(4000);

               var writer = new OutputStreamWriter(connection.getOutputStream());
               this.lamport.eventOccurred(0);

               writer.write("" + this.lamport.getLogicalClock() + "\n" + "Hello From Node B!");
               writer.flush();
               System.out.println("Message Sent!");
               
               connection.close();
               connection = new Socket(this.conn.getLocalAddress().getHostAddress(), this.conn.getPort());
           }
       } catch (IOException | InterruptedException ex) {
        System.out.println("ErroreB");
        System.out.println(ex);
       }
    }
    
}

