package nagyhazi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class PartnerHandler {

    protected HashMap<String, JSONObject> partners = new HashMap<>();


    public void addPartner(String partner, String encryptionType, String keys) throws IOException, ParseException {
        JSONObject savedPartners = new FileHandler().loadFromFile("partner.json");

        if (!savedPartners.isEmpty()) {
            for (Object o: savedPartners.keySet()) {
                String key = (String) o;
                JSONObject data = (JSONObject) savedPartners.get(key);
                partners.put(key, data);
            }
        }

        JSONObject data = new JSONObject();
        data.put("name", partner);
        data.put("encryptionType", encryptionType);
        data.put("key", keys);

        partners.put(partner, data);
        savedPartners.put(partner ,data);

        System.out.println("Partners in addPartner: " + partners);

        new FileHandler().saveToFile(savedPartners, "partner.json");
    }

    public String getEncryptionType(String name) {
        System.out.println("Partners in getEncryptionType: " + partners);
        if (partners.isEmpty()) {
            return "Hex";
        }
        return partners.get(name).get("encryptionType").toString();
    }

    public String getKey(String name) {
        if (partners.isEmpty()) {
            return "";
        }
        System.out.println(partners);
        return partners.get(name).get("key").toString();
    }

    public void savePartner(JSONObject jsonObject) {
        for (Object o: jsonObject.keySet()) {
            String key = (String) o;
            JSONObject data = (JSONObject) jsonObject.get(key);
            System.out.println("Key: " + key);
            System.out.println("Data: " + data);
            partners.put(key, data);
        }
        System.out.println("Partners in savePartner: " + partners);
    }

    public String[] getUsers() {
        try {
            String[] users = new String[10];
            JSONObject jsonObject = new FileHandler().loadFromFile("partner.json");
            for (int i = 0; i < jsonObject.keySet().toArray().length; i++) {
                JSONObject data = (JSONObject) jsonObject.get(jsonObject.keySet().toArray()[i]);
                users[i] = data.get("name").toString();
            }
            savePartner(jsonObject);
            return users;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
