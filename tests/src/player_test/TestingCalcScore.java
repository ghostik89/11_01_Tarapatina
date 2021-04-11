package player_test;

import model.Alphabet;
import model.GameField;
import model.Player;
import model.WordManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingCalcScore {
    Player player;
    WordManager manager;
    Alphabet alphabet = new Alphabet("as");
    GameField field = new GameField(3,3);
    String name = "my name";

    @Test
    public void simpleCalc(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            this.manager.addToSolvedWords(this.player, "asc");
            Assertions.assertEquals(this.player.calcScore(), 3);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void nothingAdded(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            Assertions.assertEquals(this.player.calcScore(), 0);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
