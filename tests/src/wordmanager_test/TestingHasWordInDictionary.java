package wordmanager_test;

import model.WordManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingHasWordInDictionary {
    WordManager manager;

    @Test
    public void wordHasInDict(){
        try{
            this.manager = new WordManager();
            Assertions.assertTrue(this.manager.hasWordInDictionary("абверовец"));
        }catch (FileNotFoundException ignored){}
    }

    @Test
    public void wordNotInDict(){
        try{
            this.manager = new WordManager();
            Assertions.assertFalse(this.manager.hasWordInDictionary("asa"));
        }catch (FileNotFoundException ignored){}
    }

    @Test
    public void numbersTest(){
        try{
            this.manager = new WordManager();
            Assertions.assertFalse(this.manager.hasWordInDictionary("123"));
        }catch (FileNotFoundException ignored){}
    }

    @Test
    public void whiteSpace(){
        try{
            this.manager = new WordManager();
            Assertions.assertFalse(this.manager.hasWordInDictionary("\t"));
        }catch (FileNotFoundException ignored){}
    }
}
