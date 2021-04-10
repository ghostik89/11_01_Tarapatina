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

public class TestingSetCharIntoCellAtTurn {
    GameField field = new GameField(3,3);

    @AfterEach
    void clear(){
        field.clearAll();
    }

    private List<Cell> getAllSelectedCells(){
        return this.field.getPlayField().stream()
                .filter(cell -> cell.getCellState() == CellState.CELL_WITH_SETTED_LETTER_AT_TURN)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleGetCell(){
        Point point = new Point(1,2);
        this.field.selectCellForInsertLetterByPoint(point);
        this.field.setCharIntoCellAtTurn('a');
        Assertions.assertEquals(getAllSelectedCells().size(), 1);
        Assertions.assertNotNull(this.field.getLetterSettedAtTurn());
        Assertions.assertEquals(this.field.getLetterSettedAtTurn().getLetter(), 'a');
    }
}
