package nodes.node3;

import java.util.concurrent.locks.ReentrantLock;

public class LamportClock {
    private int lamportClock;
    private ReentrantLock mutex;
    private static LamportClock myInstance;

    private LamportClock() {
        this.mutex = new ReentrantLock();
    }

    public static LamportClock getInstance() {
        if (LamportClock.myInstance == null) {
            LamportClock.myInstance = new LamportClock();
        } 
        return LamportClock.myInstance;
    }

    public void eventOccurred(int senderClock) {
        this.mutex.lock();
        try {
            this.lamportClock = Math.max(this.lamportClock, senderClock) + 1;
            System.out.println("Node C Lamport Clock... " + this.lamportClock);
        } finally {
            this.mutex.unlock();
        }
    }

    public int getLogicalClock() {
        this.mutex.lock();
        try {
            return this.lamportClock;
        } finally {
            this.mutex.unlock();
        }
    }
}
