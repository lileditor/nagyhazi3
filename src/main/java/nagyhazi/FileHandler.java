package nagyhazi;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandler {
    // { "encryptionType": "Caesar", "input": "hello", "output": "khoor" }

    // { "partnerName": "John Doe", "encryptionType": "Caesar" }

    public void save(String input, String key, String output, String encryptionType) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("encryptionType", encryptionType);
        jsonObject.put("input", input);
        jsonObject.put("output", output);
        jsonObject.put("key", key);
        FileWriter fileWriter = new FileWriter("./src/main/resources/output.json");
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.close();
    }

    public void load(JTextField inputField, JTextField keyField, JTextField outputField, JComboBox<String> encryptionType) throws IOException, ParseException {
        JSONObject jsonObject;
        FileReader fileReader = new FileReader("./src/main/resources/output.json");
        jsonObject = (JSONObject) new JSONParser().parse(fileReader);
        inputField.setText((String) jsonObject.get("input"));
        keyField.setText((String) jsonObject.get("key"));
        outputField.setText((String) jsonObject.get("output"));
        encryptionType.setSelectedItem(jsonObject.get("encryptionType"));
    }

}
