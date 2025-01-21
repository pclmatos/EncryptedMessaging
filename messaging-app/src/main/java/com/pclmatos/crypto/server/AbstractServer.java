package com.pclmatos.crypto.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import com.pclmatos.crypto.logging.Logger;

public abstract class AbstractServer {

    protected final Logger logger = new Logger("Server");

    protected final HashSet<Thread> connectedClients = new HashSet<>();

    private int port;
    private ServerSocket server;

    public AbstractServer(int port) {
        this.port = port;
    }

    public void start() {
        System.out.printf("Starting server on port %d\n", port);
        try {
            server = new ServerSocket(port);
            server.setReuseAddress(true);
            while (true) {
                Socket sock = server.accept();
                System.out.printf("Received connection from %s\n", sock.getRemoteSocketAddress());
                handleClient(sock);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected abstract void handleClient(Socket clientSock) throws IOException;

    public void stop() throws IOException {
        if (server != null) {
            server.close();
        }
    }

}
