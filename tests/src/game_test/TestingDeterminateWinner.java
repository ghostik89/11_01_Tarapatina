package game_test;

import model.Game;
import model.GameDifficult;
import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.FileNotFoundException;

public class TestingDeterminateWinner {
    GameField field = new GameField(3,3);
    Game game;
    String name1 = "Name";
    String name2 = "Name2";

    private void fillField(){
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.field.selectCellByPoint(new Point(x, y));
    }

    @Test
    public void simpleDeterminate(){
        try{
            this.game = new Game(this.field, this.name1,  this.name2, GameDifficult.EASY);
            this.fillField();
            this.game.getWordManager().addToSolvedWords(this.game.getCurrentPlayer(), "asd");
            this.game.changePlayer();
            this.game.getWordManager().addToSolvedWords(this.game.getCurrentPlayer(), "as");
            Assertions.assertEquals(this.game.determinateWinner(), this.name1);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void drawDeterminate(){
        try{
            this.game = new Game(this.field, this.name1,  this.name2, GameDifficult.EASY);
            this.fillField();
            this.game.getWordManager().addToSolvedWords(this.game.getCurrentPlayer(), "asd");
            this.game.changePlayer();
            this.game.getWordManager().addToSolvedWords(this.game.getCurrentPlayer(), "asb");
            Assertions.assertEquals(this.game.determinateWinner(), "Ничья");
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void noWinnersDeterminate(){
        try{
            this.game = new Game(this.field, this.name1,  this.name2, GameDifficult.EASY);
            this.game.getWordManager().addToSolvedWords(this.game.getCurrentPlayer(), "asd");
            this.game.changePlayer();
            this.game.getWordManager().addToSolvedWords(this.game.getCurrentPlayer(), "asd");
            Assertions.assertNull(this.game.determinateWinner());
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
