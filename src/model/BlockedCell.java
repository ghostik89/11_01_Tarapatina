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

    public void setToBlocked(){this.cellState = CellState.CELL_IS_BLOCKED;}
}
