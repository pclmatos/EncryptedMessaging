package org.pclmatos.crypto;

import java.io.IOException;

import org.pclmatos.crypto.client.MessageAppClient;
import org.pclmatos.crypto.server.MessageServer;

public class App {
    public static void main(String[] args) {

        MessageServer server = new MessageServer(5555);
        server.start();

        try {
            MessageAppClient client = new MessageAppClient("localhost", 5555);
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
