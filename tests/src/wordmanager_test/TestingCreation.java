package wordmanager_test;

import model.WordManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingCreation {
    @Test
    public void justCreate(){
        try {
            WordManager manager = new WordManager();
            Assertions.assertNotNull(manager.getDict());
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
