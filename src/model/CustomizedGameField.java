package model;

import event.GameStateEvent;
import event.GameStateListener;

import java.awt.*;
import java.util.Random;
public class CustomizedGameField extends GameField{
    private final Random rand = new Random();
    private final GameStateListener listener = new GameStateObserver();
    /**
     * Конструктор класса
     *
     * @param width  ширина поля
     * @param height высота поля
     * @throws IllegalArgumentException если ширина или длина больше 29 или меньше 3
     */
    public CustomizedGameField(int width, int height) {
        super(width, height);
    }


    public GameStateListener getListener() {
        return listener;
    }

    protected void blockCells(){
        int randomX = rand.nextInt(this.getHeight() + 1);
        int randomY = rand.nextInt(this.getWidth() + 1);
        Cell blockedCell = getCellByPoint(new Point(randomX, randomY));
        if(blockedCell != null && blockedCell.getCellState() != CellState.CELL_IS_BLOCKED)
            blockedCell.setToBlocked();
        else
            this.blockCells();
    }

    private class GameStateObserver implements GameStateListener{

        @Override
        public void turnIsEnded(GameStateEvent e) {
            blockCells();
        }
    }
}
