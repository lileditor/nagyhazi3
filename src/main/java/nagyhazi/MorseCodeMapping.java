package nagyhazi;

import java.util.HashMap;

public class MorseCodeMapping {
    private HashMap<String, String> morseCodeMap = new HashMap<>();
    MorseCodeMapping() {

        morseCodeMap.put("A", ".-");
        morseCodeMap.put("B", "-...");
        morseCodeMap.put("C", "-.-.");
        morseCodeMap.put("D", "-..");
        morseCodeMap.put("E", ".");
        morseCodeMap.put("F", "..-.");
        morseCodeMap.put("G", "--.");
        morseCodeMap.put("H", "....");
        morseCodeMap.put("I", "..");
        morseCodeMap.put("J", ".---");
        morseCodeMap.put("K", "-.-");
        morseCodeMap.put("L", ".-..");
        morseCodeMap.put("M", "--");
        morseCodeMap.put("N", "-.");
        morseCodeMap.put("O", "---");
        morseCodeMap.put("P", ".--.");
        morseCodeMap.put("Q", "--.-");
        morseCodeMap.put("R", ".-.");
        morseCodeMap.put("S", "...");
        morseCodeMap.put("T", "-");
        morseCodeMap.put("U", "..-");
        morseCodeMap.put("V", "...-");
        morseCodeMap.put("W", ".--");
        morseCodeMap.put("X", "-..-");
        morseCodeMap.put("Y", "-.--");
        morseCodeMap.put("Z", "--..");
        morseCodeMap.put("ZERO", "-----");
        morseCodeMap.put("ONE", ".----");
        morseCodeMap.put("TWO", "..---");
        morseCodeMap.put("THREE", "...--");
        morseCodeMap.put("FOUR", "....-");
        morseCodeMap.put("FIVE", ".....");
        morseCodeMap.put("SIX", "-....");
        morseCodeMap.put("SEVEN", "--...");
        morseCodeMap.put("EIGHT", "---..");
        morseCodeMap.put("NINE", "----.");
        morseCodeMap.put("SPACE", " ");
        morseCodeMap.put("DOT", ".-.-.-");
    }

    public String getMorseCode(String letter) {
        return morseCodeMap.get(letter);
    }

    public String getLetter(String morseCode) {
        for (String key : morseCodeMap.keySet()) {
            if (morseCodeMap.get(key).equals(morseCode)) {
                return key;
            }
        }
        return null;
    }
}
