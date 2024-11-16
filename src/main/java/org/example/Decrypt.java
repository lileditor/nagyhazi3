package org.example;

import java.util.HexFormat;

public class Decrypt {

    public String base64(String input) {
        String binary = "";
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            binary += String.format("%6s", Integer.toBinaryString(input.charAt(i))).replace(' ', '0');
        }
        for (int i = 0; i < binary.length(); i += 8) {
            String chunk = binary.substring(i, i + 8);
            int decimal = Integer.parseInt(chunk, 2);
            char character = (char) decimal;
            output += character;
        }
        return input;
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

    public String xor(String input) {
        return input;
    }

    public String caesar(String input) {
        return input;
    }

    public String vigenere(String input) {
        return input;
    }

    public String morse(String input) {
        return input;
    }

    public String hex(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i += 2) {
            output += String.valueOf((char) HexFormat.fromHexDigit(Integer.parseInt(input.substring(i, i + 2), 16)));
        }
        return output;
    }

    public String binary(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i += 8) {
            output += String.valueOf(Byte.parseByte(input.substring(i, i + 8)));

        }
        return output;
    }

    public String atbash(String input) {
        return input;
    }

    public String bacon(String input) {
        return input;
    }
}
