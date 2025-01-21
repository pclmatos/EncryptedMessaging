package com.pclmatos.crypto;

import com.pclmatos.crypto.server.MessageServer;

public class RunServer {
    public static void main(String[] args) {
        MessageServer server = new MessageServer(5555);
        server.start();
    }
}
