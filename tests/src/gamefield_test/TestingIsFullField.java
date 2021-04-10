package gamefield_test;

import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class TestingIsFullField {
    GameField field = new GameField(3,3);

    @AfterEach
    void clear(){this.field.clearAll();}

    @Test
    public void fillAllField(){
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.field.selectCellByPoint(new Point(x, y));

        Assertions.assertTrue(this.field.isFullField());
    }

    @Test
    public void segmentOfFullField(){
        for(int x = 0; x < 2; x++)
            for(int y = 0; y < 2; y++)
                this.field.selectCellByPoint(new Point(x, y));

        Assertions.assertFalse(this.field.isFullField());

    }

    @Test
    public void fieldIsEmpty(){
        Assertions.assertFalse(this.field.isFullField());
    }
}
