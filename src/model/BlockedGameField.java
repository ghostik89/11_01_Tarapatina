package model;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;
public class BlockedGameField extends GameField{
    private Random rand;
    /**
     * Конструктор класса
     *
     * @param width  ширина поля
     * @param height высота поля
     * @throws IllegalArgumentException если ширина или длина больше 29 или меньше 3
     */
    public BlockedGameField(int width, int height) {
        super(width, height);
    }

    public void blockRandomCellAfterTurn(){
        int randomX = rand.nextInt(this.getHeight() + 1);
        int randomY = rand.nextInt(this.getWidth() + 1);
        BlockedCell blockedCell = (BlockedCell) getCellByPoint(new Point(randomX, randomY));
        if(blockedCell != null && blockedCell.getCellState() != CellState.CELL_IS_BLOCKED)
            blockedCell.setToBlocked();
        else
            this.blockRandomCellAfterTurn();
    }

    @Override
    public boolean isAvailableCell(@NotNull Point point, @NotNull GameState gameState) {
        Cell cell = getCellByPoint(point);
        return cell.getCellState() != CellState.CELL_IS_BLOCKED  && super.isAvailableCell(point, gameState);
    }
}
