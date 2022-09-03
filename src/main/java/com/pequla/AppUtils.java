package com.pequla;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AppUtils {
    public static void sendError(JFrame frame, Exception e) {
        sendError(frame, e, e.getMessage());
    }

    public static void sendError(JFrame frame, Exception e, String message) {
        JOptionPane.showMessageDialog(frame, message, e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }

    public static void downloadFile(String url, String path) {
        try {
            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed to download " + url);
        }
    }
}
