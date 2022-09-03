package com.pequla;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pequla.dto.CurseResponse;
import com.pequla.dto.FileData;
import com.pequla.dto.PackManifest;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
                        if (!file.getName().endsWith("manifest.json"))
                            throw new RuntimeException("You need to select manifest.json");

                        // Creating mods directory
                        File modsDir = new File(file.getParent() + File.separator + "mods");
                        if (!modsDir.exists()) {
                            modsDir.mkdir();
                        }

                        // Converting manifest.json to java object
                        PackManifest manifest = mapper.readValue(file, PackManifest.class);
                        System.out.println("Modpack: " + manifest.getName());
                        System.out.println("Modloader: " + manifest.getMinecraft().getModLoaders().get(0).getId());
                        System.out.println("Total mods:" + manifest.getFiles().size());

                        // Downloading mods
                        for (FileData data : manifest.getFiles()) {
                            URL url = new URL("https://api.pequla.com/curse/" + data.getProjectID() + '/' + data.getFileID());
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");

                            int status = con.getResponseCode();
                            if (status != 200) {
                                System.out.println("Failed to retrieve data for mod id " + data.getFileID() + ", http status: " + status);
                                continue;
                            }

                            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            CurseResponse rsp = mapper.readValue(in, CurseResponse.class);

                            System.out.println("Downloading Mod (ID: " + rsp.getId() + ") " + rsp.getName());
                            AppUtils.downloadFile(rsp.getUrl(), modsDir.getPath() + File.separator + rsp.getName());
                        }
                    } catch (Exception ex) {
                        AppUtils.sendError(frame, ex);
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
