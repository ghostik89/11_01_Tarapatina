package player_test;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestingSelectCellForInsertLetter {
    WordManager manager;
    Player player;
    Alphabet alphabet = new Alphabet("as");
    GameField field = new GameField(3,3);
    String name = "Name";

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
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            this.player.selectCellForInsertLetter(new Point(1,2));
            Assertions.assertEquals(getAllSelectedCells().size(), 1);
            Assertions.assertNotNull(this.field.getLetterSettedAtTurn());
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void tryToGetOutsidePoint(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            Assertions.assertThrows(IllegalArgumentException.class, () ->
                    this.player.selectCellForInsertLetter(new Point(7,7)));
            Assertions.assertEquals(getAllSelectedCells().size(), 0);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void tryToGetNullPoint(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            Assertions.assertThrows(IllegalArgumentException.class, () ->
                    this.player.selectCellForInsertLetter(null));
            Assertions.assertEquals(getAllSelectedCells().size(), 0);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
