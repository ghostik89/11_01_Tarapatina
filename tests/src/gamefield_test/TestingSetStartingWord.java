package gamefield_test;

import model.Cell;
import model.CellState;
import model.GameField;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TestingSetStartingWord {
    GameField field;

    @AfterEach
    void clear(){
        field.clearAll();
    }

    private String getStringByBusyCells(){
        return this.field.getPlayField().stream()
                .filter(cell -> cell.getCellState() == CellState.CELL_IS_BUSY)
                .sorted((Comparator.comparingInt(Cell::getSelectedIndex))).map(Cell::getLetter)
                .map(String::valueOf).collect(Collectors.joining());
    }

    @Test
    public void simpleSetWord(){
        this.field = new GameField(3,3);
        String startWord = "foo";
        this.field.setStartedWord(startWord);

        Assertions.assertEquals(this.getStringByBusyCells(), startWord);
    }

    @Test
    public void setWordLessThanField(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field = new GameField(4,3);
            String startWord = "foo";
            this.field.setStartedWord(startWord);
        });
    }

    @Test
    public void setWordMoreThanField(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field = new GameField(3,3);
            String startWord = "fooo";
            this.field.setStartedWord(startWord);
        });
    }

    @Test
    public void setWordNullField(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field = new GameField(3,3);
            this.field.setStartedWord(null);
        });
    }

    @Test
    public void setWordIsBlankField(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field = new GameField(3,3);
            this.field.setStartedWord("   \t\n");
        });
    }

    @Test
    public void setWordIsEmptyField(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field = new GameField(3,3);
            this.field.setStartedWord("");
        });
    }
}
