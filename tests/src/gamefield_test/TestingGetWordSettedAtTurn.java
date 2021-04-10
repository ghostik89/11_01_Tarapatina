package gamefield_test;

import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class TestingGetWordSettedAtTurn {
    GameField field = new GameField(3,3);

    @AfterEach
    void clearAll(){
        this.field.clearAll();
    }

    @Test
    public void getWord(){
        Point point = new Point(1,2);
        this.field.selectCellForInsertLetterByPoint(point);
        this.field.setCharIntoCellAtTurn('a');
        this.field.selectCellByPoint(point);
        Assertions.assertEquals(this.field.getWordSettedAtTurn(), "a");
    }

    @Test
    public void haveNotWordInField(){
        Point point = new Point(1,2);
        this.field.selectCellForInsertLetterByPoint(point);
        this.field.setCharIntoCellAtTurn('a');
        Assertions.assertEquals(this.field.getWordSettedAtTurn(), "");
    }

    @Test
    public void fieldIsEmpty(){
        Assertions.assertEquals(this.field.getWordSettedAtTurn(), "");
    }
}
