package game_test;

import model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestingEndGame {
    GameField field = new GameField(3,3);
    Game game;

    private List<Cell> getAllEmptyCells(){
        return this.field.getPlayField().stream()
                .filter(cell -> cell.getCellState() == CellState.CELL_IS_EMPTY)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleClean(){
        try{
            this.game = new Game(this.field, "Name", "Name2", GameDifficult.EASY);
            Point point = new Point(1,2);
            this.field.selectCellForInsertLetterByPoint(point);
            this.field.setCharIntoCellAtTurn('a');
            this.field.selectCellByPoint(point);
            this.game.getWordManager().addToSolvedWords(this.game.getCurrentPlayer(), "asc");
            this.game.getWordManager().clearAll();
            this.game.endGame();
            Assertions.assertEquals(this.getAllEmptyCells().size(), 9);
            Assertions.assertEquals(this.game.getWordManager()
                    .getAllSolvedWordsByPlayer(this.game.getCurrentPlayer()).size(), 0);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
