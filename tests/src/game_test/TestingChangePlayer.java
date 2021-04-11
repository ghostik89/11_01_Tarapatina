package game_test;

import model.Game;
import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingChangePlayer {
    GameField field = new GameField(3,3);
    Game game;

    @Test
    public void fromZeroToOne(){
        try{
            this.game = new Game(this.field, "Name", "Name2");
            this.game.changePlayer();
            Assertions.assertEquals(this.game.getCurrentPlayer().getName(), "Name2");
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void fromOneToZero(){
        try{
            this.game = new Game(this.field, "Name", "Name2");
            this.game.changePlayer();
            this.game.changePlayer();
            Assertions.assertEquals(this.game.getCurrentPlayer().getName(), "Name");
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
