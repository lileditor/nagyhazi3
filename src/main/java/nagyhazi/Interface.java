package nagyhazi;

import javax.swing.*;
import java.awt.*;

public class Interface {

    public void start() {
        JFrame frame = new JFrame("Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        encryptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        decryptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(encryptButton);
        panel.add(decryptButton);
        panel.add(saveButton);
        panel.add(loadButton);

        JTextField inputField = new JTextField();
        inputField.setColumns(10);
        panel.add(inputField);

        JTextField keyField = new JTextField();
        keyField.setColumns(10);
        panel.add(keyField);

        JTextField outputField = new JTextField();
        outputField.setColumns(10);
        outputField.setEditable(false);
        panel.add(outputField);




        String[] items = {"Hex", "Binary", "Base64", "Rot13", "Caesar", "Vigenere", "Morse", "Atbash", "Bacon", "XOR"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        panel.add(comboBox);

        encryptButton.addActionListener(e -> {
            Encrypt encrypt = new Encrypt();
            switch (comboBox.getSelectedItem().toString()) {
                case "Hex" -> outputField.setText(encrypt.hex(inputField.getText()));
                case "Binary" -> outputField.setText(encrypt.binary(inputField.getText()));
                case "Base64" -> outputField.setText(encrypt.base64(inputField.getText()));
                case "Rot13" -> outputField.setText(encrypt.rot13(inputField.getText()));
                case "Caesar" ->
                        outputField.setText(encrypt.caesar(inputField.getText(), Integer.parseInt(keyField.getText())));
                case "Vigenere" -> outputField.setText(encrypt.vigenere(inputField.getText(), keyField.getText()));
                case "Morse" -> outputField.setText(encrypt.morse(inputField.getText()));
                case "Atbash" -> outputField.setText(encrypt.atbash(inputField.getText()));
                case "Bacon" -> outputField.setText(encrypt.bacon(inputField.getText()));
                case "XOR" -> outputField.setText(encrypt.xor(inputField.getText(), keyField.getText()));
            }
        });

        decryptButton.addActionListener(e -> {
            Decrypt decrypt = new Decrypt();
            switch (comboBox.getSelectedItem().toString()) {
                case "Hex" -> outputField.setText(decrypt.hex(inputField.getText()));
                case "Binary" -> outputField.setText(decrypt.binary(inputField.getText()));
                case "Base64" -> outputField.setText(decrypt.base64(inputField.getText()));
                case "Rot13" -> outputField.setText(decrypt.rot13(inputField.getText()));
                case "Caesar" ->
                        outputField.setText(decrypt.caesar(inputField.getText(), Integer.parseInt(keyField.getText())));
                case "Vigenere" -> outputField.setText(decrypt.vigenere(inputField.getText(), keyField.getText()));
                case "Morse" -> outputField.setText(decrypt.morse(inputField.getText()));
                case "Atbash" -> outputField.setText(decrypt.atbash(inputField.getText()));
                case "Bacon" -> outputField.setText(decrypt.bacon(inputField.getText()));
                case "XOR" -> outputField.setText(decrypt.xor(inputField.getText(), keyField.getText()));
            }
        });

        saveButton.addActionListener(e -> {
            FileHandler save = new FileHandler();
            try {
                save.save(inputField.getText(), keyField.getText(), outputField.getText(), comboBox.getSelectedItem().toString());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        loadButton.addActionListener(e -> {
            FileHandler load = new FileHandler();
            try {
                load.load(inputField, keyField, outputField, comboBox);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
