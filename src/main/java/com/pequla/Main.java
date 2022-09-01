package com.pequla;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Modpack Donkey");
        frame.setSize(320, 320);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("logo.jpg");
        frame.setIconImage(icon.getImage());
        //frame.getContentPane().setBackground(Color.GRAY);

        JButton button = new JButton();
        button.setText("Select manifest.json");
        frame.add(button);

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));

        button.addActionListener(e -> {
            if (e.getSource() == button) {
                int returnVal = chooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();

                        File file = chooser.getSelectedFile();

                    } catch (Exception ex) {
                        AppUtils.sendError(frame, ex);
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
