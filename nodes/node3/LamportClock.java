package nodes.node3;

import java.net.Socket;

public class LamportClock implements Runnable {
    private Socket conn;

    public LamportClock(final Socket conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
