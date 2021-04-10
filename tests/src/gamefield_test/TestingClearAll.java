package gamefield_test;

import model.Cell;
import model.CellState;
import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestingClearAll {
    GameField field = new GameField(3,3);

    private List<Cell> getAllEmptyCells(){
        return this.field.getPlayField().stream()
                .filter(cell -> cell.getCellState() == CellState.CELL_IS_EMPTY)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleClean(){
        Point point = new Point(1,2);
        this.field.selectCellForInsertLetterByPoint(point);
        this.field.setCharIntoCellAtTurn('a');
        this.field.selectCellByPoint(point);
        this.field.clearAll();
        Assertions.assertEquals(this.getAllEmptyCells().size(), 9);
    }
}
