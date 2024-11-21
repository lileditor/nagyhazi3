package nagyhazi;

import javax.swing.*;
import java.awt.*;

public class Interface {

    /*public void start() {
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

        encryptButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        decryptButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loadButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(encryptButton);
        panel.add(decryptButton);
        panel.add(saveButton);
        panel.add(loadButton);

        JTextField inputField = new JTextField();
        inputField.setColumns(5);
        panel.add(inputField);

        JTextField keyField = new JTextField();
        keyField.setColumns(5);
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
    }*/
    public void start() {
        // Create the frame
        JFrame frame = new JFrame("Swing UI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Top row: Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (int i = 1; i <= 4; i++) {
            buttonPanel.add(new JButton("Button " + i));
        }
        frame.add(buttonPanel, BorderLayout.NORTH);

        // Second row: Dropdown
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] dropdownItems = {"Option 1", "Option 2", "Option 3"};
        JComboBox<String> dropdown = new JComboBox<>(dropdownItems);
        dropdownPanel.add(dropdown);
        frame.add(dropdownPanel, BorderLayout.CENTER);

        // Third section: Input/Output layout
        JPanel ioPanel = new JPanel(new GridBagLayout());
        ioPanel.setBorder(BorderFactory.createTitledBorder("Input/Output"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Input label and field
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel comp = new JLabel("Input:");
        comp.setSize(new Dimension(10, 10));
        comp.setVisible(true);
        System.out.println(comp.getVisibleRect());
        ioPanel.add(comp, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1;
        ioPanel.add(new JTextField(15), gbc);

        // Add Output label and field (next to Input)
        gbc.gridx = 2; gbc.gridy = 0;
        ioPanel.add(new JLabel("Output:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0;
        ioPanel.add(new JTextField("asd",15 ), gbc);

        // Add Key label and field
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        ioPanel.add(new JLabel("Key:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1;
        ioPanel.add(new JTextField(15), gbc);

        // Add a blank label for alignment below Key
        gbc.gridx = 2; gbc.gridy = 1;
        ioPanel.add(new JLabel(""), gbc);

        frame.add(ioPanel, BorderLayout.AFTER_LAST_LINE);

        // Bottom section: JList and fields
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Additional Section"));

        // Left: JList
        JList<String> list = new JList<>(new String[]{"Item 1", "Item 2", "Item 3"});
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(150, 100));
        bottomPanel.add(scrollPane, BorderLayout.WEST);

        // Right: Form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add 'Add New User' field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Add New User:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(new JButton("Add"), gbc);

        // Add 'Name' field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(new JTextField(15), gbc);

        // Add 'Encryption Type' dropdown
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Encryption Type:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        JComboBox<String> encryptionDropdown = new JComboBox<>(new String[]{"AES", "RSA", "DES"});
        formPanel.add(encryptionDropdown, gbc);

        bottomPanel.add(formPanel, BorderLayout.CENTER);

        // Add the bottom panel to the frame
        frame.add(bottomPanel, BorderLayout.AFTER_LAST_LINE);

        // Make the frame visible
        frame.setVisible(true);
        frame.revalidate();
    }
}
