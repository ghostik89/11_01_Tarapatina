package game_test;

import model.Game;
import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingCreation {
    GameField field = new GameField(3,3);
    Game game;

    @Test
    public void simpleCreation(){
        try{
            this.game = new Game(this.field, "Name", "Name2");
            Assertions.assertEquals(this.game.getField(), this.field);
            Assertions.assertNotNull(this.game.getAlphabet());
            Assertions.assertEquals(this.game.getCurrentPlayer().getName(), "Name");
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void namesIsEquals(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.game = new Game(this.field, "Name", "Name");
        });
    }

    @Test
    public void somethingIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.game = new Game(null, "Name", "Name");
        });
    }
}
