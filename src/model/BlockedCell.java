package model;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class BlockedCell extends Cell{
    /**
     * Конструктор класса
     *
     * @param point точка, в которой расположена ячейка
     */
    public BlockedCell(@NotNull Point point) {
        super(point);
    }

    public void setToBlocked(){
        this.cellState = CellState.CELL_IS_BLOCKED;
        this.resetIndex();
    }

    /**
     * Обновление состояния ячейки
     */
    @Override
    public void updateCellState() {
        this.cellState = switch (this.cellState){
            case CELL_WITH_SETTED_LETTER_AT_TURN, CELL_IS_BUSY ->  CellState.CELL_IS_SELECTED;
            case CELL_SELECTED_FOR_INSERTING -> CellState.CELL_WITH_SETTED_LETTER_AT_TURN;
            case CELL_IS_EMPTY -> CellState.CELL_SELECTED_FOR_INSERTING;
            case CELL_IS_BLOCKED -> CellState.CELL_IS_BLOCKED;
            default -> CellState.CELL_IS_BUSY;
        };
    }

    /**
     * Откатить состояние у клетки
     */
    @Override
    public void revertCellState() {
        switch (this.cellState) {
            case CELL_IS_SELECTED -> this.cellState = CellState.CELL_IS_BUSY;
            case CELL_WITH_SETTED_LETTER_AT_TURN -> {
                this.cellState = CellState.CELL_IS_EMPTY;
                this.letter = '\0';
            }
            case CELL_IS_BUSY -> this.cellState = CellState.CELL_IS_SELECTED;
            case CELL_IS_BLOCKED -> {}
            default -> this.cellState = CellState.CELL_IS_EMPTY;
        }
    }
}
