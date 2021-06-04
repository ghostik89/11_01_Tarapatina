package customizedfield_test;

import model.Cell;
import model.CellState;
import model.CustomizedGameField;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TestingBlockCells {
    private class TestingCustomizedField extends CustomizedGameField{
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
        public void blockCells() {
            super.blockCells();
        }
    }
    private TestingCustomizedField field = new TestingCustomizedField(3,3);

    private ArrayList<Cell> getBlockedCells(){
        return this.field.getPlayField().stream()
                .filter(cell -> cell.getCellState() == CellState.CELL_IS_BLOCKED)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void simpleBlock(){
        this.field.blockCells();
        Assertions.assertTrue(!this.getBlockedCells().isEmpty());
    }
}
