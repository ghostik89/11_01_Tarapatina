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

public class TestingCleanFieldAfterPlayersTurn {
    GameField field = new GameField(3,3);

    private List<Cell> getAllBusyCells(){
        return this.field.getPlayField().stream()
                .filter(cell -> cell.getCellState() == CellState.CELL_IS_BUSY)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleClean(){
        this.field.setStartedWord("foo");
        Point point = new Point(1,2);
        this.field.selectCellForInsertLetterByPoint(point);
        this.field.setCharIntoCellAtTurn('a');
        this.field.selectCellByPoint(point);
        this.field.cleanFieldAfterPlayersTurn();
        Assertions.assertEquals(this.getAllBusyCells().size(), 4);
    }

    @Test
    public void onlyBusyCells(){
        this.field.setStartedWord("foo");
        this.field.cleanFieldAfterPlayersTurn();
        Assertions.assertEquals(this.getAllBusyCells().size(), 3);
    }
}
