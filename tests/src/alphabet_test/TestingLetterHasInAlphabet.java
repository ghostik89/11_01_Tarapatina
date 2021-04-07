package alphabet_test;
import model.Alphabet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestingLetterHasInAlphabet {
   private Alphabet alphabet;

   @BeforeEach
   void init(){
      alphabet = new Alphabet("abc");
   }

   @Test
   public void letterHasInAlphabet(){
      Assertions.assertTrue(this.alphabet.letterHasInAlphabet('a'));
   }

   @Test
   public void letterNotInAlphabet(){
      Assertions.assertFalse(this.alphabet.letterHasInAlphabet('f'));
   }
}
