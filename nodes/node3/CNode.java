package nodes.node3;

public class CNode {
    private static final int LISTEN_PORT = 3034;
    private static final int THREADS = 200;
    public static void main(String... args) {
        CNodeLamportServer lamport = new CNodeLamportServer(LISTEN_PORT, THREADS);
        lamport.start();
    }
}
