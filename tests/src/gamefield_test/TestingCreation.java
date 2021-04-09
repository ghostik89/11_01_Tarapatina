package gamefield_test;
import model.GameField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestingCreation {
    @Test
    public void simpleCreate(){
        int width = 3;
        int height = 3;
        GameField field = new GameField(width,height);

        Assertions.assertEquals(field.getWidth(),width);
        Assertions.assertEquals(field.getHeight(),height);
        Assertions.assertEquals(field.getPlayFiled().size(), 9);
        Assertions.assertNull(field.getLetterSettedAtTurn());
    }

    @Test
    public void simpleCreateWithDifferentWidthAndHeight(){
        int width = 4;
        int height = 5;
        GameField field = new GameField(width,height);

        Assertions.assertEquals(field.getWidth(),width);
        Assertions.assertEquals(field.getHeight(),height);
        Assertions.assertEquals(field.getPlayFiled().size(), 20);
        Assertions.assertNull(field.getLetterSettedAtTurn());
    }

    @Test
    public void tryToCreateLessThanThree(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int width = 2;
            int height = 2;
            GameField field = new GameField(width,height);
        });
    }

    @Test
    public void tryToCreateMoreThanTwentyNine(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int width = 29;
            int height = 2;
            GameField field = new GameField(width,height);
        });
    }
}
