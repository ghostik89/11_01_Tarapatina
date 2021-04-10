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


	/**Конструктор класса
	 * @param point точка, в которой расположена ячейка
	 * */
	public Cell(@NotNull Point point) {
		this.cellPosition = point;
		this.letter = '\0';
	}

	/** Добавление соседа клетки
	 * @param neighbor новый сосед
	 * @throws IllegalArgumentException если данная ячейка не удовлетворяет условиям соседства
	 * */
	public void setNeighbor(@NotNull Cell neighbor){
		boolean isStranger = neighbor == this ||
				(Math.abs(neighbor.cellPosition.x - this.cellPosition.x) != 1 &&
						Math.abs(neighbor.cellPosition.y - this.cellPosition.y) != 1);
		if(isStranger)
			throw new IllegalArgumentException();

		if(this.neighbors.contains(neighbor))
			throw new IllegalArgumentException("We has this neighbor");


		this.neighbors.add(neighbor);
		neighbor.neighbors.add(this);
	}

	/**Обновление состояния ячейки
	 * */
	public void updateCellState() {
		switch (this.cellState){
			case CELL_WITH_SETTED_LETTER_AT_TURN -> this.cellState = CellState.CELL_IS_SELECTED;
			case CELL_SELECTED_FOR_INSERTING -> this.cellState = CellState.CELL_WITH_SETTED_LETTER_AT_TURN;
			case CELL_IS_EMPTY -> this.cellState = CellState.CELL_SELECTED_FOR_INSERTING;
			default -> this.cellState = CellState.CELL_IS_BUSY;
		}
	}

	/**Сеттер буквы
	 * */
	public void setLetter(char letter) {
		this.letter = letter;
	}

	/**Геттер текущей позиции
	 * */
	public Point getCellPosition() {
		return this.cellPosition;
	}

	/**Увеличить общий индекс клеток
	 * */
	public static int incIndex(){return Cell.index++;}

	/** Сеттер нового индекса у клетки
	 * */
	public void setIndex() {
		this.selectedIndex = Cell.incIndex();
	}

	/**Геттер текущего индекса
	 * */
	public int getSelectedIndex() {return this.selectedIndex;}

	public void resetCell() {
		this.cellState = CellState.CELL_IS_EMPTY;
		this.letter = '\0';
	}

	/**Откатить состояние у клетки
	 * */
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

	public void setStateToBusy(){this.cellState = CellState.CELL_IS_BUSY;}

	public List<Cell> getNeighbors() {
		return new ArrayList<>(this.neighbors);
	}

	public boolean isNeighbor(@NotNull Cell cell) {
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

	public static void resetStaticIndex(){Cell.index = 0;}

	public static int getIndex() {
		return index;
	}
}
