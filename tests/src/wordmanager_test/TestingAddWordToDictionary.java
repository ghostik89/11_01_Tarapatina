package wordmanager_test;

import model.WordManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingAddWordToDictionary {
    WordManager manager;

    private String findInManager(String word){
        return this.manager.getDict().stream()
                .filter(elem -> elem.equals(word)).findAny().orElse(null);
    }

    @Test
    public void simpleAdd(){
        String wordToAdd = "foo";
        try{
            this.manager = new WordManager();
            this.manager.addWordToDictionary(wordToAdd);
            Assertions.assertNotNull(this.findInManager(wordToAdd));
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void nullString(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.manager = new WordManager();
            this.manager.addWordToDictionary(null);
        });
    }
}
