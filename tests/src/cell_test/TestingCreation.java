package cell_test;
import model.Cell;
import model.CellState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestingCreation {
    private char zeroChar = '\0';
    private Point point = new Point(1,2);

    @Test
    public void simpleCreation(){
        Cell cell = new Cell(this.point);

        Assertions.assertEquals(cell.getCellPosition(), this.point);
        Assertions.assertEquals(cell.getLetter(), zeroChar);
        Assertions.assertEquals(cell.getCellState(), CellState.CELL_IS_EMPTY);
    }

    @Test
    public void creationWithNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Cell cell = new Cell(null);
        });
    }
}
