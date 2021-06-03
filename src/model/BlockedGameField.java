package model;

import event.GameStateEvent;
import event.GameStateListener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;
public class BlockedGameField extends GameField{
    private final Random rand = new Random();
    private final GameStateListener listener = new GameStateObserver();
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


    public GameStateListener getListener() {
        return listener;
    }

    public void blockRandomCellAfterTurn(){
        int randomX = rand.nextInt(this.getHeight() + 1);
        int randomY = rand.nextInt(this.getWidth() + 1);
        Cell blockedCell = getCellByPoint(new Point(randomX, randomY));
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

    @Override
    public void revertField() {
        this.playFiled.forEach(cell -> {
            if(cell.equals(this.letterSettedAtTurn))
                cell.resetCell();
            if(cell.getCellState() != CellState.CELL_IS_BUSY && cell.getCellState() != CellState.CELL_IS_BLOCKED)
                cell.revertCellState();
        });
        this.letterSettedAtTurn = null;
        Cell.resetStaticIndex();
    }

    private class GameStateObserver implements GameStateListener{

        @Override
        public void turnIsEnded(GameStateEvent e) {
            blockRandomCellAfterTurn();
        }
    }
}
