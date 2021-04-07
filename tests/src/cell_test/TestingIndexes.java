package cell_test;

import model.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestingIndexes {
    @Test
    public void simpleIncTest(){
        Assertions.assertEquals(Cell.incIndex(), 0);
    }

    @Test
    public void simpleSetIndexTest(){
        Cell testCell = new Cell(new Point(1,2));
        testCell.setIndex();
        Assertions.assertEquals(testCell.getSelectedIndex(), 1);
    }
}
