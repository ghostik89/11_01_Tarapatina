package cell_test;

import model.Cell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

public class TestSettingNeighbor {
    private final ArrayList<Cell> neighbors1 = new ArrayList<>();
    private final ArrayList<Cell> neighbors2 = new ArrayList<>();
    private Cell normalCell1 = new Cell(new Point(1,2));
    private Cell normalCell2 = new Cell(new Point(2,1));
    private Cell stranger = new Cell(new Point(5,6));

    @BeforeEach
    void init(){
        this.neighbors1.add(this.normalCell2);
        this.neighbors2.add(this.normalCell1);
    }

    @AfterEach
    void shutDown(){
        this.normalCell1 = new Cell(new Point(1,2));
        this.normalCell2 = new Cell(new Point(2,2));
        this.stranger = new Cell(new Point(5,6));
    }

    @Test
    public void simpleSet(){
        this.normalCell1.setNeighbor(this.normalCell2);
        Assertions.assertEquals(this.neighbors1, this.normalCell1.getNeighbors());
        Assertions.assertEquals(this.neighbors2, this.normalCell2.getNeighbors());
    }

    @Test
    public void tryToSetTwice(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.normalCell1.setNeighbor(this.normalCell2);
            this.normalCell2.setNeighbor(normalCell1);
        });
    }

    @Test
    public void tryToSetStranger(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.normalCell1.setNeighbor(this.stranger);
        });
    }

    @Test
    public void tryToSetNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           this.normalCell1.setNeighbor(null);
        });
    }
}
