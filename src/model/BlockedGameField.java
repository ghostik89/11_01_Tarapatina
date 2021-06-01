package model;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;
public class BlockedGameField extends GameField{
    private final Random rand = new Random();
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

    @Override
    protected void generateField(int width, int height) {
        for(int x = 0; x < height; x++)
            for(int y = 0; y < width; y++)
                this.playFiled.add(new BlockedCell(new Point(x,y)));

        for(int x = 0; x < height; x++)
            for(int y = 0; y < width; y++){
                try{
                    if(x > 0)
                        this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x-1,y)));
                    if(y > 0)
                        this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x,y-1)));
                    if(y < width - 1)
                        this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x,y+1)));
                    if(x < height - 1)
                        this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x+1,y)));
                }catch (IllegalArgumentException ex){
                    if(!ex.getMessage().equals("We has this neighbor"))
                        throw new IllegalArgumentException();
                }catch (NullPointerException ignored){}
            }
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
}
