package nagyhazi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HexFormat;

public class Decryptions {

    private static final char[] BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final HashMap<Character, Integer> BASE64_LOOKUP = new HashMap<>();

    static {
        // Build a lookup map for decoding
        for (int i = 0; i < BASE64_ALPHABET.length; i++) {
            BASE64_LOOKUP.put(BASE64_ALPHABET[i], i);
        }
    }


    public String base64(String input) {
        // Remove padding characters
        int paddingCount = 0;
        int length = input.length();
        while (length > 0 && input.charAt(length - 1) == '=') {
            paddingCount++;
            length--;
        }

        // Prepare the output byte array
        int outputLength = (input.length() * 6 / 8) - paddingCount;
        byte[] decodedBytes = new byte[outputLength];
        int decodedIndex = 0;

        // Process 4 Base64 characters at a time
        for (int i = 0; i < input.length(); i += 4) {
            int chunk = 0;
            int validChars = 0;

            // Combine 4 Base64 characters into a 24-bit chunk
            for (int j = 0; j < 4; j++) {
                if (i + j < input.length() && input.charAt(i + j) != '=') {
                    chunk |= BASE64_LOOKUP.get(input.charAt(i + j)) << (18 - j * 6);
                    validChars++;
                }
            }

            // Convert the 24-bit chunk into bytes
            for (int j = 0; j < validChars - 1; j++) {
                if (decodedIndex < outputLength) {
                    decodedBytes[decodedIndex++] = (byte) ((chunk >> (16 - j * 8)) & 0xFF);
                }
            }
        }

        return new String(decodedBytes);
    }

    public String rot13(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (Character.isLowerCase(character)) {
                output.append((char) ((character - 'a' + 13) % 26 + 'a'));
            } else if (Character.isUpperCase(character)) {
                output.append((char) ((character - 'A' + 13) % 26 + 'A'));
            } else {
                output.append(character);
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

    public String caesar(String input, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z') {
                output.append((char) (character - key));
            } else {
                output.append((char) (character - key + 26));
            }
        }
        return output.toString();
    }

    public String vigenere(String input, String key) {
        StringBuilder decodedText = new StringBuilder();
        key = key.toUpperCase(); // Ensure the key is uppercase
        int keyIndex = 0; // Tracks the position in the key

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                // Calculate the shift value based on the key
                int shift = key.charAt(keyIndex) - 'A';

                if (Character.isUpperCase(c)) {
                    // Decode uppercase letters
                    char decodedChar = (char) ((c - shift - 'A' + 26) % 26 + 'A');
                    decodedText.append(decodedChar);
                } else {
                    // Decode lowercase letters
                    char decodedChar = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                    decodedText.append(decodedChar);
                }

                // Move to the next character in the key, wrapping around if necessary
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                // Non-alphabetic characters are added as-is
                decodedText.append(c);
            }
        }

        return decodedText.toString();
    }

    public String morse(String input) {
        StringBuilder output = new StringBuilder();
        MorseCodeMapping morseCodeMapping = new MorseCodeMapping();
        String[] morse = input.split(" ");
        for (String morseCode : morse) {
            output.append(morseCodeMapping.getLetter(morseCode));
        }
        return output.toString();
    }

    public String hex(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            output.append((char) Integer.parseInt(input.substring(i, i + 2), 16));
        }
        return output.toString();
    }

    public String binary(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i += 8) {
            output.append((char) Integer.parseInt(input.substring(i, i + 8), 2));
        }
        return output.toString();
    }

    public String atbash(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int decimal;
            if (Character.isUpperCase(character)) {
                decimal = (int) character - 'A';
                output.append((char) ('Z' - decimal));
            } else {
                decimal = (int) character - 'a';
                output.append((char) ('z' - decimal));
            }
        }
        return output.toString();
    }

    public String bacon(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i += 5) {
            StringBuilder binary = new StringBuilder();
            String chunk = input.substring(i, i + 5);
            for (int j = 0; j < chunk.length(); j++) {
                if (chunk.charAt(j) == 'a') {
                    binary.append("0");
                } else {
                    binary.append("1");
                }
            }
            output.append((char) (Integer.parseInt(binary.toString(), 2) + 'a'));
        }
        return output.toString();
    }
}
