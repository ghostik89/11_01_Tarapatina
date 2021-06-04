package model;

public class ExamGameField extends CustomizedGameField{
    public ExamGameField(int width, int height) {
        super(width, height);
    }
    /**
     * Заблокировать случайную ячейку на поле
     */
    @Override
    protected void blockCells() {
        this.letterSettedAtTurn.getNeighbors().forEach(elem -> {
            if(elem.getCellState() == CellState.CELL_IS_EMPTY)
                elem.setToBlocked();
            else if(elem.getCellState() == CellState.CELL_IS_BLOCKED)
                elem.resetCell();
        });
    }
}
