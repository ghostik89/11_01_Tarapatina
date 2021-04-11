package player_test;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;

public class TestingSubmitTurn {
    WordManager manager;
    Player player;
    Alphabet alphabet = new Alphabet("котад");
    GameField field = new GameField(3,3);
    String name = "Name";
    String startWord = "код";

    @AfterEach
    void clear(){
        field.clearAll();
    }

    private void init() throws FileNotFoundException {
        this.manager = new WordManager();
        this.player = new Player(this.manager, this.name, this.alphabet, this.field);

        this.field.setStartedWord(this.startWord);
    }

    @Test
    public void simpleSubmit(){
        try{
            this.init();

            this.player.selectCellForInsertLetter(new Point(0,1));
            this.player.insertLetterIntoCell('т');
            this.player.selectCell(new Point(1,0));
            this.player.selectCell(new Point(1,1));
            this.player.selectCell(new Point(0,1));

            Assertions.assertEquals(this.player.submitTurn(), SubmitState.TURN_SUCCESS);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void wordWithoutLetterSetAtTurn(){
        try{
            this.init();

            this.player.selectCellForInsertLetter(new Point(0,1));
            this.player.insertLetterIntoCell('т');
            this.player.selectCell(new Point(1,0));
            this.player.selectCell(new Point(1,1));
            this.player.selectCell(new Point(0,1));
            this.field.cleanFieldAfterPlayersTurn();

            this.player.selectCellForInsertLetter(new Point(0,2));
            this.player.insertLetterIntoCell('а');
            this.player.selectCell(new Point(1,0));
            this.player.selectCell(new Point(1,1));
            this.player.selectCell(new Point(0,1));


            Assertions.assertEquals(this.player.submitTurn(), SubmitState.GAMEFIELD_HAS_NOT_LETTER_SET_AT_TURN);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void wordHasNotInDict(){
        try{
            this.init();

            this.player.selectCellForInsertLetter(new Point(0,1));
            this.player.insertLetterIntoCell('а');
            this.player.selectCell(new Point(1,0));
            this.player.selectCell(new Point(1,1));
            this.player.selectCell(new Point(0,1));

            Assertions.assertEquals(this.player.submitTurn(), SubmitState.WORDMANAGER_ERROR_NOT_FOUND);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void wordEqualsToStarted(){
        try{
            this.init();

            this.manager.setStartedWord("код");
            this.player.selectCellForInsertLetter(new Point(0,1));
            this.player.insertLetterIntoCell('д');
            this.player.selectCell(new Point(1,0));
            this.player.selectCell(new Point(1,1));
            this.player.selectCell(new Point(0,1));

            Assertions.assertEquals(this.player.submitTurn(), SubmitState.WORDMANAGER_ERROR_IS_SOLVED);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void wordHasBeenSolved(){
        try{
            this.init();

            this.manager.addToSolvedWords(player, "кот");
            this.player.selectCellForInsertLetter(new Point(0,1));
            this.player.insertLetterIntoCell('т');
            this.player.selectCell(new Point(1,0));
            this.player.selectCell(new Point(1,1));
            this.player.selectCell(new Point(0,1));

            Assertions.assertEquals(this.player.submitTurn(), SubmitState.WORDMANAGER_ERROR_IS_SOLVED);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
