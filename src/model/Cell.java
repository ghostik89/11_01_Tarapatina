package model;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell {

	private CellState cellState = CellState.CELL_IS_EMPTY; // текущее состояние клетки
	private char letter; // текущая буква клетки
	private final Point cellPosition; // позиция клетки на поле
	private int selectedIndex; // индекс клетки, после того как она была выбрана
	private static int index = 0;
	private final ArrayList<Cell> neighbors = new ArrayList<>(); // соседи клетки


	/**Конструктор класса
	 * @param point точка, в которой расположена ячейка
	 * */
	public Cell(@NotNull Point point) {
		this.cellPosition = point;
		this.letter = '\0';
	}

	/** Добавление соседей для клетки
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
			case CELL_WITH_SETTED_LETTER_AT_TURN, CELL_IS_BUSY -> this.cellState = CellState.CELL_IS_SELECTED;
			case CELL_SELECTED_FOR_INSERTING -> this.cellState = CellState.CELL_WITH_SETTED_LETTER_AT_TURN;
			case CELL_IS_EMPTY -> this.cellState = CellState.CELL_SELECTED_FOR_INSERTING;
			default -> this.cellState = CellState.CELL_IS_BUSY;
		}
	}

	/**Установить текущую букву
	 * @param letter буква, которую необходимо установить в клетку
	 * */
	public void setLetter(char letter) {
		this.letter = letter;
	}

	/** Получить текущую позицию
	 * @return текущая позиция клетки
	 * */
	public Point getCellPosition() {
		return this.cellPosition;
	}

	/**Увеличить общий индекс клеток
	 * */
	public static int incIndex(){return Cell.index++;}

	/** Установить новый индекс у клетки
	 * */
	public void setIndex() {
		this.selectedIndex = Cell.incIndex();
	}

	/**Получить текущий индекс
	 * @return текущий индекс клетки
	 * */
	public int getSelectedIndex() {return this.selectedIndex;}

	/** Обнулить клетку
	 * */
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

	/** Сделать клетку занятой
	 * */
	public void setStateToBusy(){this.cellState = CellState.CELL_IS_BUSY;}

	public void setToBlocked(){this.cellState = CellState.CELL_IS_BLOCKED;}

	/** Получить всех соседей клетки
	 * @return массив соседей клетки
	 * */
	public List<Cell> getNeighbors() {
		return new ArrayList<>(this.neighbors);
	}

	/** Проверка, является ли клетка соседней
	 * @return true - клетка соседняя, иначе - false
	 * */
	public boolean isNeighbor(@NotNull Cell cell) {
		return this.neighbors.contains(cell);
	}

	/** Обнулить индекс клетки
	 * */
	public void resetIndex() {
		this.selectedIndex = 0;
	}

	/** Получить состояние клетки
	 * @return текущее состояние
	 * */
	public CellState getCellState() {
		return this.cellState;
	}

	/** Получить текущую букву внутри клетки
	 * @return буква внутри клетки
	 * */
	public char getLetter() {
		return letter;
	}

	/** Обнулить глобальный индекс клетки
	 * */
	public static void resetStaticIndex(){Cell.index = 0;}

	/** Получить глобальный индекс клеток
	 * */
	public static int getIndex() {
		return index;
	}
}
