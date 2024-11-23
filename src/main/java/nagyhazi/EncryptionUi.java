package nagyhazi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class EncryptionUi {
    protected String[] items = {"Hex", "Binary", "Base64", "Rot13", "Caesar", "Vigenere", "Morse", "Atbash", "Bacon", "XOR"};

    JComboBox<String> dropdown;
    Encryptions encryptions = new Encryptions();
    Decryptions decryptions = new Decryptions();

    JTextField inputField = new JTextField(15);
    JTextField keyField = new JTextField(15);
    JTextField outputField = new JTextField(15);
    JTextField nameField = new JTextField(15);
    JTextField keyField2 = new JTextField(15);
    JTextField decryptField = new JTextField(15);

    protected void encryptButtonListener(ActionEvent e) {
        switch (dropdown.getSelectedItem().toString()) {
            case "Hex" -> outputField.setText(encryptions.hex(inputField.getText()));
            case "Binary" -> outputField.setText(encryptions.binary(inputField.getText()));
            case "Base64" -> outputField.setText(encryptions.base64(inputField.getText()));
            case "Rot13" -> outputField.setText(encryptions.rot13(inputField.getText()));
            case "Caesar" -> outputField.setText(encryptions.caesar(inputField.getText(), Integer.parseInt(keyField.getText())));
            case "Vigenere" -> outputField.setText(encryptions.vigenere(inputField.getText(), keyField.getText()));
            case "Morse" -> outputField.setText(encryptions.morse(inputField.getText()));
            case "Atbash" -> outputField.setText(encryptions.atbash(inputField.getText()));
            case "Bacon" -> outputField.setText(encryptions.bacon(inputField.getText()));
            case "XOR" -> outputField.setText(encryptions.xor(inputField.getText(), keyField.getText()));
            default -> {
                break;
            }
        }
    }

    protected void decryptButtonListener(ActionEvent e) {
        switch (dropdown.getSelectedItem().toString()) {
            case "Hex" -> decryptions.hex(inputField.getText());
            case "Binary" -> decryptions.binary(inputField.getText());
            case "Base64" -> decryptions.base64(inputField.getText());
            case "Rot13" -> decryptions.rot13(inputField.getText());
            case "Caesar" -> decryptions.caesar(inputField.getText(), Integer.parseInt(keyField.getText()));
            case "Vigenere" -> decryptions.vigenere(inputField.getText(), keyField.getText());
            case "Morse" -> decryptions.morse(inputField.getText());
            case "Atbash" -> decryptions.atbash(inputField.getText());
            case "Bacon" -> decryptions.bacon(inputField.getText());
            case "XOR" -> decryptions.xor(inputField.getText(), keyField.getText());
            default -> {
                break;
            }
        }
    }

    protected void saveButtonListener(ActionEvent e) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("input", inputField.getText());
            jsonObject.put("key", keyField.getText());
            jsonObject.put("output", outputField.getText());
            jsonObject.put("encryptionType", dropdown.getSelectedItem().toString());
            new FileHandler().saveToFile(jsonObject, "output.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void loadButtonListener(ActionEvent e) {
        try {
            JSONObject jsonObject = new FileHandler().loadFromFile("output.json");
            inputField.setText(jsonObject.get("input").toString());
            outputField.setText(jsonObject.get("output").toString());
            keyField.setText(jsonObject.get("key").toString());
            dropdown.setSelectedItem(jsonObject.get("encryptionType").toString());
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    protected void addButtons(JFrame frame) {
        // Top row: Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this::encryptButtonListener);
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this::decryptButtonListener);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveButtonListener);
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(this::loadButtonListener);
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        dropdown = new JComboBox<>(items);
        buttonPanel.add(dropdown);
        frame.add(buttonPanel, BorderLayout.NORTH);
    }

    protected void addIO(JFrame frame) {
        // Second section: Input/Output layout
        JPanel ioPanel = new JPanel(new GridBagLayout());
        ioPanel.setBorder(BorderFactory.createTitledBorder("Input/Output"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Input label and field
        gbc.gridx = 0; gbc.gridy = 0;
        ioPanel.add(new JLabel("Input:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1;
        ioPanel.add(inputField, gbc);

        // Add Output label and field (next to Input)
        gbc.gridx = 2; gbc.gridy = 0;
        ioPanel.add(new JLabel("Output:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0;
        ioPanel.add(outputField, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        ioPanel.add(new JLabel("Decrypt"), gbc);


        // Add Key label and field
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        ioPanel.add(new JLabel("Key:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1;
        ioPanel.add(keyField, gbc);

        // Add a blank label for alignment below Key
        gbc.gridx = 2; gbc.gridy = 1;
        ioPanel.add(new JLabel(""), gbc);

        frame.add(ioPanel, BorderLayout.CENTER);
    }

    protected void addButtonListener(ActionEvent e)  {
        String name = JOptionPane.showInputDialog("Enter a name for the new user:");
        if (name != null) {
            try {
                new PartnerHandler().addPartner(nameField.getText(), dropdown.getSelectedItem().toString(), keyField2.getText());
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void addPartners(JFrame frame) {
        // Third section: JList and fields
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("User Management"));

        // Left: JList
        JList<String> list = new JList<>(new PartnerHandler().getUsers());
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(150, 100));
        bottomPanel.add(scrollPane, BorderLayout.WEST);

        // Right: Form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add 'Add New User' field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Add New User:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this::addButtonListener);
        formPanel.add(addButton, gbc);

        // Add 'Name' field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(nameField, gbc);

        // Add 'Encryption Type' dropdown
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Encryption Type:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        JComboBox<String> encryptionDropdown = new JComboBox<>(items);
        formPanel.add(encryptionDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Key:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(keyField2, gbc);

        bottomPanel.add(formPanel, BorderLayout.CENTER);

        // Add the bottom panel to the frame
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void start() {
        // Create the frame
        JFrame frame = new JFrame("Swing UI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        addButtons(frame);

        addIO(frame);

        addPartners(frame);

        // Make the frame visible
        frame.setVisible(true);
        frame.revalidate();
    }
}
