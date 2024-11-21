package nagyhazi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class EncryptionsTest {


    @Test
    void base64() {
        assertEquals("VGVzdA==", new Encryptions().base64("Test"));
    }

    @Test
    void rot13() {
        assertEquals("Grfg", new Encryptions().rot13("Test"));
    }

    @Test
    void xor() {
        assertEquals("eW@E", new Encryptions().xor("Test", "123"));
    }

    @Test
    void caesar() {
        assertEquals("Zkyz", new Encryptions().caesar("Test", 6));
    }

    @Test
    void vigenere() {
        assertEquals("Mikm", new Encryptions().vigenere("Test", "Test"));
    }

    @Test
    void morse() {
        assertEquals("- . ... -", new Encryptions().morse("Test"));
    }

    @Test
    void hex() {
        assertEquals("54657374", new Encryptions().hex("Test"));
    }

    @Test
    void binary() {
        assertEquals("01010100011001010111001101110100", new Encryptions().binary("Test"));
    }

    @Test
    void atbash() {
        assertEquals("Gvhg", new Encryptions().atbash("Test"));
    }

    @Test
    void bacon() {
        assertEquals("BAABBAABAABAABABAABB".toLowerCase(), new Encryptions().bacon("Test"));
    }
}