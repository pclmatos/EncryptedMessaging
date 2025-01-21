package org.pclmatos.crypto.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private String className;

    public Logger(String className) {
        this.className = className;
    }

    public void printf(String format, Object... args) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = LocalDateTime.now().format(timeFormatter);
        System.out.printf("%s | %s | %s\n", formattedTime, className,
                String.format(format, args));
    }

    public void print(String message) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = LocalDateTime.now().format(timeFormatter);
        System.out.printf("%s | %s | %s\n", formattedTime, className,
                message);
    }

}
