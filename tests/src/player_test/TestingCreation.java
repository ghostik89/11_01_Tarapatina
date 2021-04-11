package player_test;

import model.Alphabet;
import model.GameField;
import model.Player;
import model.WordManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingCreation {
    WordManager manager;
    Player player;
    Alphabet alphabet = new Alphabet("as");
    GameField field = new GameField(3,3);
    String name = "Name";


    @Test
    public void simpleCreate(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            Assertions.assertEquals(this.player.getName(), this.name);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void invalidName(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.manager = new WordManager();
            this.player = new Player(this.manager,null, this.alphabet, this.field);
        });
    }

    @Test
    public void nameIsBlank(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.manager = new WordManager();
            this.player = new Player(this.manager,"  ", this.alphabet, this.field);
        });
    }
}
