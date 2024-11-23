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

    public void saveToFile(JSONObject jsonObject, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/" + fileName);
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.close();
    }

    @SuppressWarnings("unused")
    public JSONObject loadFromFile(String fileName) throws IOException, ParseException {
        FileReader fileReader = new FileReader("./src/main/resources/" + fileName);
        if (fileReader == null) {
            return new JSONObject();
        }
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(fileReader);
        fileReader.close();
        if (jsonObject.isEmpty()) {
            return new JSONObject();
        } else {
            return jsonObject;
        }
    }

}
