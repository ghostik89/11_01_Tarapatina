package wordmanager_test;

import model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingAddToSolvedWords {
    Player player;
    WordManager manager;

    @Test
    public void simpleAdd(){
        Alphabet alphabet = new Alphabet("as");
        GameField field = new GameField(3,3);
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, "Some", alphabet, field);
            Assertions.assertTrue(this.manager.addToSolvedWords(this.player, "asc"));
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void invalidAdd(){
        String wordToAdd = "foo";
        Alphabet alphabet = new Alphabet("as");
        GameField field = new GameField(3,3);
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, "Some", alphabet, field);
            Assertions.assertTrue(this.manager.addToSolvedWords(this.player, wordToAdd));
            Assertions.assertFalse(this.manager.addToSolvedWords(this.player, wordToAdd));

        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
