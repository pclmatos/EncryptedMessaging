package com.pclmatos.crypto.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.pclmatos.crypto.logging.Logger;
import com.pclmatos.crypto.message.Message;

public abstract class AbstractClient {

    protected final Logger logger = new Logger("Client");

    private String host;
    private int port;
    private Socket sock;
    private DataOutputStream writer;
    private DataInputStream reader;

    // private Cipher cipher = Cipher.getInstance(host)

    public AbstractClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        sock = new Socket(host, port);
        writer = new DataOutputStream(sock.getOutputStream());
        reader = new DataInputStream(sock.getInputStream());

        logger.printf("Connected to server");
        handleConnection();
    }

    protected abstract void handleConnection() throws IOException;

    public void sendMessage(Message message) throws IOException {
        writer.write(message.serialize());
        writer.flush();
    }

    public String readMessage() {
        Message msg = null;
        try {
            // if (reader.available() > 0) {
            // logger.printf("Available: %d", reader.available());
            int size = reader.readInt();

            // logger.printf("Message Size: %d", size);
            byte[] bytes = reader.readNBytes(size);
            // byte[] bytes = reader.readAllBytes();
            msg = Message.deserialize(bytes);
            // logger.printf("bytes: %s", msg.getContent());
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg.getContent();
    }

    public void disconnect() throws IOException {
        if (sock != null) {
            sock.close();
        }
    }

    // private byte[] encrypt(String message) {
    // ByteArrayOutputStream bout = new ByteArrayOutputStream();
    // // bout.write
    // return bout.toByteArray();
    // }

}