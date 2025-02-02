package nodes.node1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.locks.ReentrantLock;

public class LamportServer implements Runnable {
    private LamportClock lamportClock;
    private Socket connection;
    private String message;
    
    public LamportServer(final Socket conn) {
        this.connection = conn;
        this.lamportClock = LamportClock.getLamportClock();
    }
    
    private int extractSenderLamportClock(BufferedReader buffer) {
       int senderLamport = 0;
       try {
        senderLamport = Integer.parseInt(buffer.readLine());
        this.message = buffer.readLine();
       } catch (IOException ex) {
        System.out.println(ex);
       }
       return senderLamport;
    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            var reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            lamportClock.eventOccurred(this.extractSenderLamportClock(reader));
            System.out.println(this.message);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            this.closeConnection();
        }
    }
}
