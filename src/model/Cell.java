package model;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell {

	private CellState cellState = CellState.CELL_IS_EMPTY;

	private char letter;

	private final Point cellPosition;

	private int selectedIndex;

	private static int index = 0;

	private final ArrayList<Cell> neighbors = new ArrayList<>();


	public Cell(@NotNull Point point) {
		this.cellPosition = point;
		this.letter = '\0';
	}

	public void setNeighbor(@NotNull Cell neighbor){
		if(neighbor == this || this.neighbors.contains(neighbor))
			throw new IllegalArgumentException();

		this.neighbors.add(neighbor);
		neighbor.setNeighbor(this);
	}

	public void updateCellState() {
		switch (this.cellState){
			case CELL_WITH_SETTED_LETTER_AT_TURN -> {
				this.cellState = CellState.CELL_IS_SELECTED;
			}
			case CELL_SELECTED_FOR_INSERTING -> {
				this.cellState = CellState.CELL_WITH_SETTED_LETTER_AT_TURN;
			}
			case CELL_IS_EMPTY -> {
				this.cellState = CellState.CELL_SELECTED_FOR_INSERTING;
			}
			default -> {
				this.cellState = CellState.CELL_IS_BUSY;
			}
		}
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public Point getCellPosition() {
		return this.cellPosition;
	}

	public static int incIndex(){return Cell.index++;}

	public void setIndex() {
		this.selectedIndex = Cell.incIndex();
	}

	public int getSelectedIndex() {return this.selectedIndex;}

	public void resetCell() {
		this.cellState = CellState.CELL_IS_EMPTY;
		this.letter = '\0';
	}

	public void revertCellState(){
		switch (this.cellState) {
			case CELL_IS_SELECTED -> this.cellState = CellState.CELL_IS_BUSY;
			case CELL_WITH_SETTED_LETTER_AT_TURN -> {
				this.cellState = CellState.CELL_IS_EMPTY;
				this.letter = '\0';
			}
			case CELL_IS_BUSY -> this.cellState = CellState.CELL_IS_SELECTED;
			default -> this.cellState = CellState.CELL_IS_EMPTY;
		}
	}

	public List<Cell> getNeighbors() {
		return new ArrayList<>(this.neighbors);
	}

	public boolean isNeighbor(Cell cell) {
		return this.neighbors.contains(cell);
	}

	public void resetIndex() {
		this.selectedIndex = 0;
	}

	public CellState getCellState() {
		return this.cellState;
	}

	public char getLetter() {
		return letter;
	}

	public static int getIndex() {
		return index;
	}
}

enum CellState {
	CELL_SELECTED_FOR_INSERTING,
	CELL_IS_EMPTY,
	CELL_IS_SELECTED,
	CELL_IS_BUSY,
	CELL_WITH_SETTED_LETTER_AT_TURN
}
