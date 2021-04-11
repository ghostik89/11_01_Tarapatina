package game_test;

import model.Game;
import model.GameField;
import model.GameState;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingUpdateCurrentState {
    GameField field = new GameField(3,3);
    Game game;

    @Test
    public void fromInsertLetterToSelectCells(){
        try{
            this.game = new Game(this.field, "Name", "Name2");
            for(int i=0; i < 1; i++)
                this.game.updateCurrentState();

            this.game.revertState();
            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void fromSelectCellToInsert(){
        try{
            this.game = new Game(this.field, "Name", "Name2");
            for(int i=0; i < 2; i++)
                this.game.updateCurrentState();

            this.game.revertState();
            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_INSERTING_LETTER);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void fromSubmitToSelect(){
        try{
            this.game = new Game(this.field, "Name", "Name2");
            for(int i=0; i < 3; i++)
                this.game.updateCurrentState();

            this.game.revertState();
            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_SELECTING_CHARS);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
