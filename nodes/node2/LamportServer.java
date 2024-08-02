package nodes.node2;

import java.net.Socket;

public class LamportServer implements Runnable {
    private Socket conn;

    public LamportServer(final Socket conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
