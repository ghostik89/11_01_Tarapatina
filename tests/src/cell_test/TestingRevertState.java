package cell_test;

import model.Cell;
import model.CellState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestingRevertState {
    Cell revertedCell = new Cell(new Point(1,1));

    @Test
    public void revertStateFromSelectCellFromInserting(){
        this.revertedCell.updateCellState();
        this.revertedCell.revertCellState();
        Assertions.assertEquals(this.revertedCell.getCellState(), CellState.CELL_IS_EMPTY);
    }

    @Test
    public void revertStateFromInsertLetterAtTurn(){
        for(int i = 0; i < 2; i++)
            this.revertedCell.updateCellState();
        this.revertedCell.revertCellState();

        Assertions.assertEquals(this.revertedCell.getCellState(), CellState.CELL_IS_EMPTY);
        Assertions.assertEquals(this.revertedCell.getLetter(), '\0');
    }

    @Test
    public void revertStateFromSelecting(){
        for(int i = 0; i < 3; i++)
            this.revertedCell.updateCellState();
        this.revertedCell.revertCellState();

        Assertions.assertEquals(this.revertedCell.getCellState(), CellState.CELL_IS_BUSY);
    }

    @Test
    public void revertStateFromCellIsBusy(){
        for(int i = 0; i < 4; i++)
            this.revertedCell.updateCellState();
        this.revertedCell.revertCellState();

        Assertions.assertEquals(this.revertedCell.getCellState(), CellState.CELL_IS_SELECTED);
    }
}
