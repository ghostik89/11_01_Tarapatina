package wordmanager_test;

import model.Alphabet;
import model.GameField;
import model.Player;
import model.WordManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingClearAll {
    Player player;
    WordManager manager;

    @Test
    public void simpleAdd(){
        Alphabet alphabet = new Alphabet("as");
        GameField field = new GameField(3,3);
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, "Some", alphabet, field);
            this.manager.addToSolvedWords(this.player, "asc");
            this.manager.clearAll();
            Assertions.assertEquals(this.manager.getAllSolvedWordsByPlayer(this.player).size(), 0);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void nothingAdded(){
        Alphabet alphabet = new Alphabet("as");
        GameField field = new GameField(3,3);
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, "Some", alphabet, field);
            this.manager.clearAll();
            Assertions.assertEquals(this.manager.getAllSolvedWordsByPlayer(this.player).size(), 0);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
