package nagyhazi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class PartnerHandler {

    protected HashMap<String, String> partners;

    public void addPartner(String partner, String encryptionType, String keys) throws IOException, ParseException {
        JSONObject partnerJson = new JSONObject();
        JSONObject savedPartners = new FileHandler().loadFromFile("partner.json");

        for (Object key : savedPartners.keySet()) {
            partnerJson.put(key.toString(), savedPartners.get(key.toString()));
        }

        partners.put(partner, encryptionType);

        new FileHandler().saveToFile((JSONObject) partners, "partner.json");
    }

    public void removePartner(String partner) {
        partners.remove(partner);
        try {
            new FileHandler().saveToFile((JSONObject) partners, "partner.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePartner(String partner, String encryptionType) {
        partners.remove(partner);
        partners.put(partner, encryptionType);
        try {
            new FileHandler().saveToFile((JSONObject) partners, "partner.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getUsers() {
        try {
            String[] users = new String[10];
            JSONObject jsonObject = new FileHandler().loadFromFile("partner.json");
            for (int i = 0; i < jsonObject.keySet().toArray().length; i++) {
                users[i] = jsonObject.keySet().toArray()[i].toString();
            }
            return users;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
