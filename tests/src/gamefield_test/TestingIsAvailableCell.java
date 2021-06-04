package gamefield_test;
import model.GameField;
import model.GameState;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class TestingIsAvailableCell {
    private GameField field = new GameField(3,3);
    Point point = new Point(0,1);

    @Test
    public void selectForInsert(){
        this.field.setStartedWord("boo");
        Assertions.assertTrue(this.field.isAvailableCell(this.point, GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER));
        Assertions.assertTrue(this.field.isAvailableCell(this.point, GameState.PLAYER_INSERTING_LETTER));
        Assertions.assertFalse(this.field.isAvailableCell(this.point, GameState.PLAYER_SELECTING_CHARS));
        Assertions.assertFalse(this.field.isAvailableCell(this.point, GameState.PLAYER_SUBMITTED_TURN));
    }

    @Test
    public void justSelect(){
        this.field.setStartedWord("boo");
        this.field.selectCellByPoint(new Point(1,1));
        Assertions.assertFalse(this.field.isAvailableCell(this.point, GameState.PLAYER_SELECTING_CHARS));
        Assertions.assertFalse(this.field.isAvailableCell(this.point, GameState.PLAYER_SUBMITTED_TURN));
    }

    @Test
    public void cellIsInvalid(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field.isAvailableCell(new Point(5,5), GameState.PLAYER_SELECTING_CHARS);
        });
    }

    @Test
    public void invalidStatus(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field.isAvailableCell(new Point(5,5), null);
        });
    }

    @Test
    public void invalidPoint(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.field.isAvailableCell(null, GameState.PLAYER_SELECTING_CHARS);
        });
    }
}
