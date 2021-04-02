package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell {

	private CellState cellState = CellState.CELL_IS_EMPTY;

	private char letter;

	private Point cellPosition;

	private int selectedIndex;

	private static int index = 0;

	// спросить как их инициализировать
	private ArrayList<Cell> neighbors = new ArrayList<>();


	public Cell(Point point) {
		this.cellPosition = point;
		this.letter = '\0';
	}

	public void updateCellState() {
		switch (this.cellState){
			case CELL_IS_EMPTY -> this.cellState = CellState.CELL_WITH_SETTED_LETTER_AT_TURN;
			case CELL_WITH_SETTED_LETTER_AT_TURN -> this.cellState = CellState.CELL_IS_SELECTED;
			case CELL_IS_SELECTED -> this.cellState = CellState.CELL_IS_BUSY;
			// fixme return to me
			default -> this.cellState = CellState.CELL_IS_EMPTY;
		}
	}

	//todo update method with returning new state

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public Point getCellPosition() {
		return this.cellPosition;
	}

	public void setIndex() {
		this.selectedIndex = Cell.index++;
	}

	public int getSelectedIndex() {return this.selectedIndex;}

	public void resetCell() {
		this.cellState = CellState.CELL_IS_EMPTY;
		this.letter = '\0';
	}

	public void revertCellState(){
		switch (this.cellState){
			case CELL_IS_SELECTED:
				this.cellState = this.letter != '\0'? CellState.CELL_IS_BUSY : CellState.CELL_IS_EMPTY;
				break;
			case CELL_WITH_SETTED_LETTER_AT_TURN:
				this.cellState = CellState.CELL_IS_EMPTY;
				this.letter = '\0';
				break;
			default:
				//do something

		}
	}

	public List getNeighbors() {
		return new ArrayList(this.neighbors);
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

}

enum CellState {
	CELL_IS_EMPTY,
	CELL_IS_SELECTED,
	CELL_IS_BUSY,
	CELL_WITH_SETTED_LETTER_AT_TURN
}
