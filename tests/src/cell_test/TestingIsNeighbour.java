package cell_test;

import model.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestingIsNeighbour {
    Cell mySimpleCell = new Cell(new Point(1,1));
    Cell neighbour = new Cell(new Point(0,1));

    @BeforeEach
    void init(){
        this.mySimpleCell.setNeighbor(this.neighbour);
    }

    @Test
    public void cellIsNeighbor(){
        Assertions.assertTrue(this.mySimpleCell.isNeighbor(this.neighbour));
    }

    @Test
    public void cellIsStranger(){
        Assertions.assertFalse(this.mySimpleCell.isNeighbor(new Cell(new Point(1,2))));
    }

    @Test
    public void cellIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.mySimpleCell.isNeighbor(null);
        });
    }
}
