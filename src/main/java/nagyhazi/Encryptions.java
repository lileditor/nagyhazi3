package nagyhazi;

public class Encryptions {
    public String base64(String input) {
        byte[] bytes = input.getBytes();
        final char[] BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        StringBuilder encoded = new StringBuilder();
        int paddingCount = 0;

        for (int i = 0; i < bytes.length; i += 3) {
            int chunk = 0;
            int bytesToProcess = Math.min(3, bytes.length - i);

            for (int j = 0; j < bytesToProcess; j++) {
                chunk |= (bytes[i + j] & 0xFF) << (16 - j * 8); // Combine bytes
            }

            paddingCount = 3 - bytesToProcess;

            for (int j = 0; j < 4 - paddingCount; j++) {
                int index = (chunk >> (18 - j * 6)) & 0x3F; // Get 6-bit segment
                encoded.append(BASE64_ALPHABET[index]);
            }
        }
        while (paddingCount-- > 0) {
            encoded.append('=');
        }

        return encoded.toString();
    }

    public String rot13(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character >= 'a' && character <= 'm' || character >= 'A' && character <= 'M') {
                output.append((char) (character + 13));
            } else if (character >= 'n' && character <= 'z' || character >= 'N' && character <= 'Z') {
                output.append((char) (character - 13));
            }
        }
        return output.toString();
    }

    public String xor(String input, String key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }
        return output.toString();
    }

    public String caesar(String input , int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z') {
                output.append((char) (character + key));
            } else {
                output.append((char) (character + key - 26));
            }

        }
        return output.toString();
    }

    // key -> shift with the same of the key char
    public String vigenere(String input, String key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int keyShift = key.toLowerCase().charAt(i % key.length()) - 'a';
            if (character + keyShift >= 'a' && character + keyShift <= 'z' && Character.isLowerCase(character) || character + keyShift >= 'A' && character + keyShift <= 'Z' && Character.isUpperCase(character)) {
                output.append((char) (character + keyShift));
            } else {
                output.append((char) (character + keyShift - 26));
            }
        }
        return output.toString();
    }

    // charset
    public String morse(String input) {
        StringBuilder output = new StringBuilder();
        MorseCodeMapping morseCodeMapping = new MorseCodeMapping();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z') {
                output.append(morseCodeMapping.getMorseCode(String.valueOf(character).toUpperCase()));
            }
            output.append(" ");
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    public String hex(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            output.append(String.format("%2s", Integer.toHexString(character)).replace(' ', '0'));
        }
        return output.toString();
    }

    public String binary(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            output.append(String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0'));
        }
        return output.toString();
    }

    //reverse alphabet
    public String atbash(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character >= 'a' && character <= 'z') {
                output.append((char) ('z' - character + 'a'));
            } else if (character >= 'A' && character <= 'Z') {
                output.append((char) ('Z' - character + 'A'));
            } else {
                output.append(character);
            }
        }
        return output.toString();
    }

    // distance form a => 0 = a 1 = b
    public String bacon(String input) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int distance = 0;
            if (Character.isLowerCase(input.charAt(i))) {
                distance = input.charAt(i) - 'a';
            } else {
                distance = input.charAt(i) - 'A';
            }
            binary.append(String.format("%5s", Integer.toBinaryString(distance)).replace(' ', '0'));
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < binary.length(); i ++) {
            if (binary.charAt(i) == '0') {
                output.append("a");
            } else {
                output.append("b");
            }
        }
        return output.toString();
    }
}
