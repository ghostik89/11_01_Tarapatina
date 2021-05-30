package model;

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
        //fixme
        int randomX = rand.nextInt(this.getHeight() + 1);
        int randomY = rand.nextInt(this.getWidth() + 1);
        Cell blockedCell = getCellByPoint(new Point(randomX, randomY));
        if(blockedCell != null && blockedCell.getCellState() != CellState.CELL_IS_BLOCKED)
            blockedCell.setToBlocked();
        else
            this.blockRandomCellAfterTurn();
    }
}
