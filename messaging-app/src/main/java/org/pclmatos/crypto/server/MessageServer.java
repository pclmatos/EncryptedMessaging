package org.pclmatos.crypto.server;

import java.io.IOException;
import java.net.Socket;

public class MessageServer extends AbstractServer {

    public MessageServer(int port) {
        super(port);
    }

    @Override
    protected void handleClient(Socket clientSock) throws IOException {
        MessageAppClientHandler handler = new MessageAppClientHandler(clientSock);
        Thread clientHandler = new Thread(handler);
        clientHandler.start();
        connectedClients.add(clientHandler);
    }

}
