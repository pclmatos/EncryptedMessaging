package org.pclmatos.crypto;

import java.io.IOException;

import org.pclmatos.crypto.client.MessageAppClient;

public class RunClient {
    public static void main(String[] args) {
        try {
            MessageAppClient client = new MessageAppClient("localhost", 5555);
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
