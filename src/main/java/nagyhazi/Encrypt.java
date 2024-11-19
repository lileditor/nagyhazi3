package nagyhazi;

public class Encrypt {
    public String base64(String input) {
        String binary = "";
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            binary += String.format("%8s", Integer.toBinaryString(input.charAt(i))).replace(' ', '0');
        }
        for (int i = 0; i < binary.length(); i += 6) {
            String chunk = binary.substring(i, i + 6);
            int decimal = Integer.parseInt(chunk, 2);
            char character = (char) decimal;
            output += character;
        }
        return output;
    }

    public String rot13(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character >= 'a' && character <= 'm') {
                output += (char) (character + 13);
            } else if (character >= 'n' && character <= 'z') {
                output += (char) (character - 13);
            } else {
                output += character;
            }
        }
        return output;
    }

    public String xor(String input, String key) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output += (char) (input.charAt(i) ^ key.charAt(i % key.length()));
        }
        return output;
    }

    public String caesar(String input , int key) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z') {
                output += (char) (character + key);
            } else {
                output += (char) (character + key - 26);
            }

        }
        return output;
    }

    // key -> shift with the same of the key char
    public String vigenere(String input) {
        return input;
    }

    // charset
    public String morse(String input) {
        return input;
    }

    public String hex(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            output += String.format("%2s", Integer.toHexString(character)).replace(' ', '0');
        }
        return output;
    }

    public String binary(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            output += String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0');
        }
        return output;
    }

    //reverse alphabet
    public String atbash(String input) {
        return input;
    }

    // distance form a => 0 = a 1 = b
    public String bacon(String input) {
        return input;
    }
}
