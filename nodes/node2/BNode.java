package nodes.node2;

public class BNode {
    private static final int LISTEN_PORT = 3032;
    private static final int THREADS = 200;
    public static void main(String... args) {
        BNodeLamportServer lamport = new BNodeLamportServer(LISTEN_PORT, THREADS);
        lamport.start();
    }
}
