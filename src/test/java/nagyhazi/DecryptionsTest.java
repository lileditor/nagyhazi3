package nagyhazi;

import static org.junit.jupiter.api.Assertions.*;

class DecryptionsTest {

    @org.junit.jupiter.api.Test
    void base64() {
        assertEquals("Test", new Decryptions().base64("VGVzdA=="));
    }

    @org.junit.jupiter.api.Test
    void rot13() {
        assertEquals("Test", new Decryptions().rot13("Grfg"));
    }

    @org.junit.jupiter.api.Test
    void xor() {
        assertEquals("Test", new Decryptions().xor("eW@E", "123"));
    }

    @org.junit.jupiter.api.Test
    void caesar() {
        assertEquals("Test", new Decryptions().caesar("Zkyz", 6));
    }

    @org.junit.jupiter.api.Test
    void vigenere() {
        assertEquals("Test", new Decryptions().vigenere("Mikm", "Test"));
    }

    @org.junit.jupiter.api.Test
    void morse() {
        assertEquals("TEST", new Decryptions().morse("- . ... -"));
    }

    @org.junit.jupiter.api.Test
    void hex() {
        assertEquals("Test", new Decryptions().hex("54657374"));
    }

    @org.junit.jupiter.api.Test
    void binary() {
        assertEquals("Test", new Decryptions().binary("01010100011001010111001101110100"));
    }

    @org.junit.jupiter.api.Test
    void atbash() {
        assertEquals("Test", new Decryptions().atbash("Gvhg"));
    }

    @org.junit.jupiter.api.Test
    void bacon() {
        assertEquals("test", new Decryptions().bacon("BAABBAABAABAABABAABB".toLowerCase()));
    }
}