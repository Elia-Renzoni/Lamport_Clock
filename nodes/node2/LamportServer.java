package nodes.node2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class LamportServer implements Runnable {
    private Socket conn;
    private LamportClock lamport;
    private String message;

    public LamportServer(final Socket conn) {
        this.conn = conn;
        this.lamport = LamportClock.getInstance();
    }

    public int extractSenderLamportClock(BufferedReader buffer)  {
       int senderLamport = 0;
       try {
        senderLamport = Integer.parseInt(buffer.readLine());
        this.message = buffer.readLine();
       } catch (Exception ex) {
        System.out.println(ex);
       } 
       return senderLamport;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            var reader = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
            this.lamport.eventOccurred(this.extractSenderLamportClock(reader));
            System.out.println(this.message);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            this.closeConnection();
        }
    }
    
}
