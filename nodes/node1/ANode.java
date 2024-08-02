package nodes.node1;

public class ANode {
    private static final int LISTEN_PORT = 3030;
    private static final int THREADS = 200;
    public static void main(String... args) {
        ANodeLamportServer lamport = new ANodeLamportServer(LISTEN_PORT, THREADS);
        lamport.start();
    }
}