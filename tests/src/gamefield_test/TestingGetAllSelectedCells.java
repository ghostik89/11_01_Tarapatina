package gamefield_test;

import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class TestingGetAllSelectedCells {
    GameField field = new GameField(3,3);

    @Test
    public void simpleGetAllCells(){
        Point point = new Point(1,2);
        this.field.selectCellForInsertLetterByPoint(point);
        this.field.setCharIntoCellAtTurn('a');
        this.field.selectCellByPoint(point);
        Assertions.assertEquals(field.getAllSelectedCells().size(), 1);
    }

    @Test
    public void emptyArrayAfterTurn(){
        Point point = new Point(1,2);
        this.field.selectCellForInsertLetterByPoint(point);
        this.field.setCharIntoCellAtTurn('a');
        this.field.selectCellByPoint(point);
        this.field.cleanFieldAfterPlayersTurn();
        Assertions.assertEquals(field.getAllSelectedCells().size(), 0);
    }

    @Test
    public void justEmptyArray(){
        Assertions.assertEquals(field.getAllSelectedCells().size(), 0);
    }

    @Test
    public void emptyArrayAfterInit(){
        this.field.setStartedWord("woo");
        Assertions.assertEquals(field.getAllSelectedCells().size(), 0);
    }
}
