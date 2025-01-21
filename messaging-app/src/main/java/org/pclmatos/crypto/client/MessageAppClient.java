package org.pclmatos.crypto.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pclmatos.crypto.Message;

public class MessageAppClient extends AbstractClient {

    public MessageAppClient(String host, int port) {
        super(host, port);

    }

    @Override
    protected void handleConnection() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        new Thread(() -> {
            while (true) {
                String serverMessage = readMessage();
                logger.printf("Message from server: " + serverMessage);
            }
        }).start();

        while (true) {
            String message = read.readLine();
            sendMessage(new Message(message));
            if (message.equals("exit")) {
                disconnect();
                System.exit(1);
            }
        }
    }

}
