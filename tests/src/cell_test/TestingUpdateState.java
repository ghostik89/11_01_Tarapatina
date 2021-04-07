package cell_test;

import model.Cell;
import model.CellState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestingUpdateState {
    Cell updatedCell = new Cell(new Point(1,1));

    @Test
    public void updateStateFromEmptyState(){
        this.updatedCell.updateCellState();
        Assertions.assertEquals(this.updatedCell.getCellState(), CellState.CELL_SELECTED_FOR_INSERTING);
    }

    @Test
    public void updateStateFromSelectCellForInserting(){
        for(int i = 0; i < 2; i++)
            this.updatedCell.updateCellState();

        Assertions.assertEquals(this.updatedCell.getCellState(), CellState.CELL_WITH_SETTED_LETTER_AT_TURN);
    }

    @Test
    public void updateStateFromCellWithSettedLetterAtTurn(){
        for(int i = 0; i < 3; i++)
            this.updatedCell.updateCellState();

        Assertions.assertEquals(this.updatedCell.getCellState(), CellState.CELL_IS_SELECTED);
    }

    @Test
    public void updateStateFromCellIsSelected(){
        for(int i = 0; i < 4; i++)
            this.updatedCell.updateCellState();

        Assertions.assertEquals(this.updatedCell.getCellState(), CellState.CELL_IS_BUSY);
    }
}
