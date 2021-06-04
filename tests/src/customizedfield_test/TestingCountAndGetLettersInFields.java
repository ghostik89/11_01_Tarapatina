package customizedfield_test;

import model.Cell;
import model.CustomizedGameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.ArrayList;

public class TestingCountAndGetLettersInFields {
    private class TestingCustomizedField extends CustomizedGameField {
        /**
         * Конструктор класса
         *
         * @param width  ширина поля
         * @param height высота поля
         * @throws IllegalArgumentException если ширина или длина больше 29 или меньше 3
         */
        public TestingCustomizedField(int width, int height) {
            super(width, height);
        }

        @Override
        public int countLettersInFields(char letter){
            return super.countLettersInFields(letter);
        }

        @Override
        protected ArrayList<Cell> getCellsByLetter(char letter) {
            return super.getCellsByLetter(letter);
        }
    }
    TestingCustomizedField field = new TestingCustomizedField(3,3);

    @Test
    public void simpleCount(){
        field.getCellByPoint(new Point(1,1)).setLetter('a');
        Assertions.assertEquals(this.field.countLettersInFields('a'),1);
    }

    @Test
    public void simpleGetCells(){
        field.getCellByPoint(new Point(1,1)).setLetter('a');
        Assertions.assertEquals(this.field.getCellsByLetter('a').size(),1);
    }
}
