package nagyhazi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class PartnerHandler {

    protected HashMap<String, String[]> partners;

    @SuppressWarnings("unchecked")
    protected HashMap<String, String[]> toMap(JSONObject json) {
        HashMap<String, String[]> map = new HashMap<String, String[]>();

        Iterator<Object> keys = json.keySet().iterator();
        while (keys.hasNext()) {
            JSONObject key = (JSONObject) keys.next();
            map.put(key.get("name").toString(), new String[] {key.get("encryptionType").toString(), key.get("key").toString()});
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public void addPartner(String partner, String encryptionType, String keys) throws IOException, ParseException {
        JSONObject savedPartners = new FileHandler().loadFromFile("partner.json");

        if (savedPartners.isEmpty()) {
            partners = new HashMap<>();
        } else {
            partners = toMap(savedPartners);
        }

        partners.put(partner, new String[]{encryptionType, keys});

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

    public void updatePartner(String partner, String encryptionType, String key) {
        partners.remove(partner);
        partners.put(partner, [encryptionType, key]);
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
                users[i] = (String) jsonObject.get("name");
            }
            return users;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
