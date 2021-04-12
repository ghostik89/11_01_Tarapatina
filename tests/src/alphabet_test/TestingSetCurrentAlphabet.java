package alphabet_test;

import model.Alphabet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestingSetCurrentAlphabet {
    Alphabet alphabet = new Alphabet("abcd");
    @Test
    public void creationWithNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.alphabet.setCurrentAlphabet(null);
        });
    }

    @Test
    public void simpleCreation(){
        this.alphabet.setCurrentAlphabet("abc");
        Assertions.assertEquals(this.alphabet.getCurrentAlphabet(), "abc");
    }

    @Test
    public void creationWithInvalidAlphabet(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.alphabet.setCurrentAlphabet("abфб");
        });
    }
}
