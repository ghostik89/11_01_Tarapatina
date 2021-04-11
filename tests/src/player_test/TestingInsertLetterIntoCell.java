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

public class TestingInsertLetterIntoCell {
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
                .filter(cell -> cell.getCellState() == CellState.CELL_WITH_SETTED_LETTER_AT_TURN)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleSetLetter(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            Point point = new Point(1,2);
            this.player.selectCellForInsertLetter(point);
            this.player.insertLetterIntoCell('a');
            Assertions.assertEquals(getAllSelectedCells().size(), 1);
            Assertions.assertNotNull(this.field.getLetterSettedAtTurn());
            Assertions.assertEquals(this.field.getLetterSettedAtTurn().getLetter(), 'a');
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }

    @Test
    public void setLetterWithInvalidChar(){
        try{
            this.manager = new WordManager();
            this.player = new Player(this.manager, this.name, this.alphabet, this.field);
            Point point = new Point(1,2);
            this.player.selectCellForInsertLetter(point);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> this.player.insertLetterIntoCell('d'));
        }catch (FileNotFoundException ignored){
            throw new AssertionError();
        }
    }
}
