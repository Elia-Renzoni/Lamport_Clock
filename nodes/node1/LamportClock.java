package nodes.node1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.locks.ReentrantLock;

public class LamportClock implements Runnable {
    private int lamportClock;
    private Socket connection;
    private ReentrantLock mutex;
    private String message;

    public LamportClock(final Socket conn) {
        this.connection = conn;
    }

    private void eventOccurred(int senderLamport) {
        this.mutex.lock();
        try {
            this.lamportClock = Math.max(this.lamportClock, senderLamport) + 1;
            System.out.println("Lamport Clock Value: " + this.lamportClock);
        } finally {
            this.mutex.unlock();
        }
    }

    private int extractSenderLamportClock(BufferedReader buffer) {
       int senderLamport = 0;
       try {
        this.message = buffer.readLine();
        senderLamport = Integer.parseInt(buffer.readLine());
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
            this.eventOccurred(this.extractSenderLamportClock(reader));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        // now the thread start a broadcast session.
    }
    
}
