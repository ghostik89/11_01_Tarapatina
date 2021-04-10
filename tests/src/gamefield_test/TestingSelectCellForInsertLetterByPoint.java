package gamefield_test;

import model.Cell;
import model.CellState;
import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestingSelectCellForInsertLetterByPoint {
    GameField field = new GameField(3,3);

    @AfterEach
    void clear(){
        field.clearAll();
    }

    private List<Cell> getAllSelectedCells(){
        return this.field.getPlayField().stream()
                .filter(cell -> cell.getCellState() == CellState.CELL_SELECTED_FOR_INSERTING)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleGetCell(){
        this.field.selectCellForInsertLetterByPoint(new Point(1,2));
        Assertions.assertEquals(getAllSelectedCells().size(), 1);
        Assertions.assertNotNull(this.field.getLetterSettedAtTurn());
    }

    @Test
    public void tryToGetOutsidePoint(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.field.selectCellForInsertLetterByPoint(new Point(7,7)));
        Assertions.assertEquals(getAllSelectedCells().size(), 0);
    }

    @Test
    public void tryToGetNullPoint(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.field.selectCellForInsertLetterByPoint(null));
        Assertions.assertEquals(getAllSelectedCells().size(), 0);
    }
}
