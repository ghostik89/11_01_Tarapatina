package gamefield_test;

import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class TestingGetLetterSettedAtTurn {
    GameField field = new GameField(3,3);

    @Test
    public void simpleGetCell(){
        this.field.selectCellForInsertLetterByPoint(new Point(1,2));
        Assertions.assertNotNull(this.field.getLetterSettedAtTurn());
        Assertions.assertEquals(this.field.getLetterSettedAtTurn().getLetter(), '\0');
    }

    @Test
    public void simpleGetCellAfterInsert(){
        this.field.selectCellForInsertLetterByPoint(new Point(1,2));
        this.field.setCharIntoCellAtTurn('a');
        Assertions.assertNotNull(this.field.getLetterSettedAtTurn());
        Assertions.assertEquals(this.field.getLetterSettedAtTurn().getLetter(), 'a');
    }

    @Test
    public void nothingSet(){
        Assertions.assertNull(this.field.getLetterSettedAtTurn());
    }
}
