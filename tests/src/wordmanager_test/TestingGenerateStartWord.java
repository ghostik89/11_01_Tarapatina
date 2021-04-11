package wordmanager_test;

import model.WordManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingGenerateStartWord {
    WordManager manager;

    @Test
    public void simpleGenerate(){
        int width = 3;
        try{
            this.manager = new WordManager();
            Assertions.assertEquals(this.manager.generateStartWord(width).length(), width);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void invalidGenerate(){
        int width = 0;
        try{
            this.manager = new WordManager();
            Assertions.assertThrows(IllegalArgumentException.class, () -> this.manager.generateStartWord(width));
        }catch (FileNotFoundException ex){
            throw new AssertionError();
        }
    }
}
