package org.pclmatos.crypto.server;

import java.io.IOException;
import java.net.Socket;

import org.pclmatos.crypto.Message;
import org.pclmatos.crypto.logging.Logger;

public class MessageAppClientHandler extends AbstractClientHandler {

    private final Logger logger = new Logger("MessageReceiver");

    public MessageAppClientHandler(Socket sock) throws IOException {
        super(sock);
    }

    @Override
    protected void handleClient() throws IOException {
        // Message msg = new Message("Hello from server");
        // sendMessage(msg);
        while (true) {
            int available = reader.available();
            if (available > 0) {
                int messageSize = reader.readInt();
                byte[] msgBytes = reader.readNBytes(messageSize);
                Message message = Message.deserialize(msgBytes);
                logger.printf("Message: %s", message.getContent());
                if (message.getContent().equals("exit")) {
                    logger.printf("Client closed the connection");
                    Thread.currentThread().interrupt();
                    break;
                } else {

                }
                sendMessage(new Message("Cunt"));
            }
        }

    }

}
