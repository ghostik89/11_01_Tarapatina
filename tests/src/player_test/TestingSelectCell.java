package player_test;

import model.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestingSelectCell {
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
                .filter(cell -> cell.getCellState() == CellState.CELL_IS_SELECTED)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleSelect(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            Point point = new Point(1,2);
            this.player.selectCellForInsertLetter(point);
            this.player.insertLetterIntoCell('a');
            this.player.selectCell(point);
            Assertions.assertEquals(getAllSelectedCells().size(), 1);
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
