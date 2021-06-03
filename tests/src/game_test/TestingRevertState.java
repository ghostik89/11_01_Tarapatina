package game_test;

import model.Game;
import model.GameDifficult;
import model.GameField;
import model.GameState;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class TestingRevertState {
    GameField field = new GameField(3,3);
    Game game;

    @Test
    public void fromSelectCellForInsertToInsertLetter(){
        try{
            this.game = new Game(this.field, "Name", "Name2", GameDifficult.EASY);
            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER);

            this.game.updateCurrentState();
            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_INSERTING_LETTER);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void fromInsertLetterToSelectCells(){
        try{
            this.game = new Game(this.field, "Name", "Name2", GameDifficult.EASY);
            for(int i=0; i < 2; i++)
                this.game.updateCurrentState();

            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_SELECTING_CHARS);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void fromSelectCellToSubmit(){
        try{
            this.game = new Game(this.field, "Name", "Name2", GameDifficult.EASY);
            for(int i=0; i < 3; i++)
                this.game.updateCurrentState();

            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_SUBMITTED_TURN);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void fromSubmitToSelectForInsert(){
        try{
            this.game = new Game(this.field, "Name", "Name2", GameDifficult.EASY);
            for(int i=0; i < 4; i++)
                this.game.updateCurrentState();


            Assertions.assertEquals(this.game.getCurrentState(), GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
