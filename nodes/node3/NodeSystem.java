package nodes.node3;

public enum NodeSystem {
    NODEA("localhost", 3030),
    NODEB("localhost", 3032),
    NODEC("localhost", 3034);

    private String nodeHost;
    private int nodePort;

    private NodeSystem(final String host, final int port) {
        this.nodeHost = host;
        this.nodePort = port;
    }

    public String getNodeHost() {
        return this.nodeHost;
    }

    public int getNodePort() {
        return this.nodePort;
    }
}
