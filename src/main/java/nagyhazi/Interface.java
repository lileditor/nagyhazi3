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

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        encryptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        decryptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(encryptButton);
        panel.add(decryptButton);

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
                case "Hex":
                    outputField.setText(encrypt.hex(inputField.getText()));
                    break;
                case "Binary":
                    outputField.setText(encrypt.binary(inputField.getText()));
                    break;
                case "Base64":
                    outputField.setText(encrypt.base64(inputField.getText()));
                    break;
                case "Rot13":
                    outputField.setText(encrypt.rot13(inputField.getText()));
                    break;
                case "Caesar":
                    outputField.setText(encrypt.caesar(inputField.getText(), Integer.parseInt(keyField.getText())));
                    break;
                case "Vigenere":
                    outputField.setText(encrypt.vigenere(inputField.getText(), keyField.getText()));
                    break;
                case "Morse":
                    outputField.setText(encrypt.morse(inputField.getText()));
                    break;
                case "Atbash":
                    outputField.setText(encrypt.atbash(inputField.getText()));
                    break;
                case "Bacon":
                    outputField.setText(encrypt.bacon(inputField.getText()));
                    break;
                case "XOR":
                    outputField.setText(encrypt.xor(inputField.getText() , keyField.getText()));
                    break;
            }
        });

        decryptButton.addActionListener(e -> {
            Decrypt decrypt = new Decrypt();
            switch (comboBox.getSelectedItem().toString()) {
                case "Hex":
                    outputField.setText(decrypt.hex(inputField.getText()));
                    break;
                case "Binary":
                    outputField.setText(decrypt.binary(inputField.getText()));
                    break;
                case "Base64":
                    outputField.setText(decrypt.base64(inputField.getText()));
                    break;
                case "Rot13":
                    outputField.setText(decrypt.rot13(inputField.getText()));
                    break;
                case "Caesar":
                    outputField.setText(decrypt.caesar(inputField.getText(), Integer.parseInt(keyField.getText())));
                    break;
                case "Vigenere":
                    outputField.setText(decrypt.vigenere(inputField.getText(), keyField.getText()));
                    break;
                case "Morse":
                    outputField.setText(decrypt.morse(inputField.getText()));
                    break;
                case "Atbash":
                    outputField.setText(decrypt.atbash(inputField.getText()));
                    break;
                case "Bacon":
                    outputField.setText(decrypt.bacon(inputField.getText()));
                    break;
                case "XOR":
                    outputField.setText(decrypt.xor(inputField.getText(), keyField.getText()));
                    break;
            }
        });
    }
}
