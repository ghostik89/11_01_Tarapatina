package alphabet_test;

import model.Alphabet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestingCreation {
    @Test
    public void creationWithNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Alphabet alphabet = new Alphabet(null);
        });
    }

    @Test
    public void simpleCreation(){
        Alphabet alpha = new Alphabet("abc");
        Assertions.assertEquals(alpha.getCurrentAlphabet(), "abc");
    }
}
