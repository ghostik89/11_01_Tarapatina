package cell_test;

import model.Cell;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TestingIndexes {
    @BeforeAll
    static void resetBefore(){Cell.resetStaticIndex();}

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
