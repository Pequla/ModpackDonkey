package com.pequla;

import javax.swing.*;

public class AppUtils {
    public static void sendError(JFrame frame, Exception e) {
        sendError(frame, e, e.getMessage());
    }

    public static void sendError(JFrame frame, Exception e, String message) {
        JOptionPane.showMessageDialog(frame, message,e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }
}
