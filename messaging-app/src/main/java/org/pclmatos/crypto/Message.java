package org.pclmatos.crypto;

import java.nio.ByteBuffer;

public class Message {
    // private static final long serialVersionUID = 1L;

    private String content;

    public Message(String content) {
        this.content = content;
    }

    public Message(byte[] contentBytes) {
        this.content = new String(contentBytes);
    }

    public String getContent() {
        return content;
    }

    public byte[] serialize() {
        ByteBuffer buff = ByteBuffer.allocate(content.length() + Integer.BYTES);
        buff.putInt(content.length());
        buff.put(content.getBytes());
        return buff.array();
    }

    public static Message deserialize(byte[] bytes) {
        // ByteBuffer buff = ByteBuffer.wrap(bytes);
        // int contentSize = buff.getInt();
        // byte[] msgBytes = new byte[contentSize];
        // buff.get(Integer.BYTES, msgBytes);
        Message msg = new Message(bytes);
        return msg;
    }

    @Override
    public String toString() {
        return content;
    }

}
