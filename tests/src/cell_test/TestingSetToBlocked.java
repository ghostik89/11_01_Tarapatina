package cell_test;

import model.Cell;
import model.CellState;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class TestingSetToBlocked {
    private final Cell blockedCell = new Cell(new Point(1,1));

    @Test
    public void simpleBlock(){
        blockedCell.setToBlocked();
        Assert.assertEquals(blockedCell.getCellState(), CellState.CELL_IS_BLOCKED);
    }
}
