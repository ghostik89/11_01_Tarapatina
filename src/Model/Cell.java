package Model;

import java.awt.*;
import java.util.ArrayList;

public class Cell {

	private CellState cellState = CellState.CELL_IS_EMPTY;

	private char letter;

	private Point cellPosition;

	private int selectedIndex;

	private static int index = 0;

	private ArrayList<Cell> neighbors = new ArrayList<>();


	public void Cell(Point point) {

	}

	public void updateCellState() {
		switch (this.cellState){
		}
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public Point getCellPosition() {
		return this.cellPosition;
	}

	public void setIndex() {
		this.selectedIndex = Cell.index++;
	}

	public void resetCell() {
		this.cellState = CellState.CELL_IS_EMPTY;
		this.letter = '\0';
	}

	public void revertCellState(){
		switch (this.cellState){}
	}

	public ArrayList getNeighbors() {
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
