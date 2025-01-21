package com.pclmatos.crypto.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.pclmatos.crypto.message.Message;

public abstract class AbstractClientHandler implements Runnable {

    protected Socket sock;
    protected DataOutputStream writer;
    protected DataInputStream reader;

    public AbstractClientHandler(Socket sock) throws IOException {
        this.sock = sock;
        this.writer = new DataOutputStream(sock.getOutputStream());
        this.reader = new DataInputStream(sock.getInputStream());
    }

    @Override
    public void run() {
        try {
            handleClient();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void sendMessage(Message message) throws IOException {
        byte[] msgBytes = message.serialize();
        writer.write(msgBytes);
        writer.flush();
    }

    public void stop() {
        try {
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void handleClient() throws IOException;

}
