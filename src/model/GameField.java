package model;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GameField {

	private final ArrayList<Cell> playFiled = new ArrayList<>(); //игровое поле
	private Cell letterSettedAtTurn; //буква, которую добавили в текущем поле

	/** Конструктор класса
	 * @param width ширина поля
	 * @param height высота поля
	 * @throws IllegalArgumentException если ширина или длина больше 29 или меньше 3
	 * */
	public GameField(int width, int height) {
		if(height < 3 || width > 15 || width < 3 || height > 15)
			throw new IllegalArgumentException("Размеры поля не вадилдны");
		this.generateField(width, height);
	}

	/** Сгенерировать поле
	 * @param width ширина поля
	 * @param height высота поля
	 * */
	private void generateField(int width, int height){
		for(int x = 0; x < height; x++)
			for(int y = 0; y < width; y++)
				this.playFiled.add(new Cell(new Point(x,y)));

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

	/** Получить игровое поле
	 * */
	public List<Cell> getPlayField(){
		return new ArrayList<>(this.playFiled);
	}

	/** Написать стартовое слово на поле
	 * @param word слово, которое необходимо вписать на поле
	 * @throws IllegalArgumentException если слово больше или меньше ширины поля
	 * */
	public void setStartedWord(@NotNull String word){
		boolean isInvalid = word.isEmpty() || word.isBlank() || word.length() > this.getWidth()
				|| word.length() < this.getWidth();
		if(isInvalid)
			throw new IllegalArgumentException();

		int place = (this.getHeight() - 1) / 2;

		for(int i = 0; i < this.getWidth(); i++){
			Cell cell = this.getCellByPoint(new Point(place,i));
			cell.setLetter(word.toCharArray()[i]);
			cell.setStateToBusy();
		}
	}

	/** Получить ширину поля
	 * */
	public int getWidth() {
		return this.playFiled.stream()
				.mapToInt(v -> (int) v.getCellPosition().getY())
				.max().orElseThrow(NoSuchElementException::new) + 1;
	}

	/** Получить высоту поля
	 * */
	public int getHeight() {
		return this.playFiled.stream()
				.mapToInt(v -> (int) v.getCellPosition().getX())
				.max().orElseThrow(NoSuchElementException::new) + 1;
	}

	/** Получить клетку с поля по координате
	 * @param point координата, по которой необходимо получить клетку
	 * */
	public Cell getCellByPoint(@NotNull Point point) {
		return this.playFiled.stream()
				.filter(elem -> elem.getCellPosition().equals(point))
				.findAny()
				.orElse(null);
	}

	/** Выбрать клетку, чтобы вставить букву
	 * @param point координата клетки
	 * @throws IllegalArgumentException если точка за пределами поля
	 * */
	public void selectCellForInsertLetterByPoint(@NotNull Point point) {
		Cell cell = this.getCellByPoint(point);
		if(cell != null && cell != this.letterSettedAtTurn) {
			cell.updateCellState();
			if(this.letterSettedAtTurn != null)
				this.letterSettedAtTurn.revertCellState();

			this.letterSettedAtTurn = cell;
		}else if (cell != null){
			this.letterSettedAtTurn.revertCellState();
			this.letterSettedAtTurn = null;
		}
		else
			throw new IllegalArgumentException();
	}

	/** Выбрать клетку
	 * @param point координата, по которой необходимо выбрать клетку
	 * @throws IllegalArgumentException если точка за пределами поля
	 * */
	public void selectCellByPoint(@NotNull Point point){
		Cell cell = this.getCellByPoint(point);
		if(cell != null) {
			cell.updateCellState();
			cell.setIndex();
		}
		else
			throw new IllegalArgumentException();
	}

	/** Вставить букву в клетку
	 * @param letter буква, которую необходимо вставить в клетку
	 * */
	public void setCharIntoCellAtTurn(char letter){
		this.letterSettedAtTurn.setLetter(letter);
		this.letterSettedAtTurn.updateCellState();
	}

	/** Доступна ли клетка в данный момент
	 * @param point координата клетки
	 * @param gameState текущее состояние игры
	 * @throws IllegalArgumentException если клетка не найдена по текущей точке
	 * */
	public boolean isAvailableCell(@NotNull Point point,@NotNull GameState gameState) {
		boolean isAvailable = false;
		Cell cell = this.getCellByPoint(point);

		if (cell == null)
			throw new IllegalArgumentException();

		if ((gameState == GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER
				|| gameState == GameState.PLAYER_INSERTING_LETTER) && cell.getCellState() != CellState.CELL_IS_BUSY) {
			for (Cell elem : cell.getNeighbors())
				if (elem.getCellState() == CellState.CELL_IS_BUSY) {
					isAvailable = true;
					break;
				}
		} else if (gameState == GameState.PLAYER_SELECTING_CHARS) {
			if (this.getAllSelectedCells().isEmpty()) {
				for (Cell elem : cell.getNeighbors())
					if (elem.getCellState() == CellState.CELL_IS_BUSY && (cell.getCellState() == CellState.CELL_IS_BUSY
							|| cell.getCellState() == CellState.CELL_WITH_SETTED_LETTER_AT_TURN)) {
						isAvailable = true;
						break;
					}
			}
			else {
				for (Cell newElem : cell.getNeighbors()) {
					isAvailable = newElem.getCellState() == CellState.CELL_IS_SELECTED
							&& newElem.getSelectedIndex() == Cell.getIndex() - 1 &&
							(cell.getCellState() == CellState.CELL_IS_BUSY ||
									cell.getCellState() == CellState.CELL_WITH_SETTED_LETTER_AT_TURN);
					if (isAvailable)
						break;
				}
			}
		}

		return isAvailable;
	}

	/** Получить все клетки, которые были выбраны
	 * */
	public List<Cell> getAllSelectedCells() {
		return this.playFiled.stream()
				.filter(cell -> cell.getCellState() == CellState.CELL_IS_SELECTED)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/** Получить клетку, в которую вставили букву в это ходу
	 * */
	public Cell getLetterSettedAtTurn() {
		return this.letterSettedAtTurn;
	}

	/** Получить игровое поле
	 * */
	public List<Cell> getPlayFiled() {
		return new ArrayList<>(playFiled);
	}

	/** Получить слово, которое получилось в этом ходу
	 * */
	public String getWordSettedAtTurn() {
		return this.playFiled.stream()
				.filter(cell -> cell.getCellState() == CellState.CELL_IS_SELECTED)
				.sorted((Comparator.comparingInt(Cell::getSelectedIndex))).map(Cell::getLetter)
				.map(String::valueOf).collect(Collectors.joining());
	}

	/** Почистить поле после хода игрока
	 * */
	public void cleanFieldAfterPlayersTurn() {
		this.getAllSelectedCells().forEach(Cell::updateCellState);
		this.letterSettedAtTurn = null;
	}

	public void revertField(){
		this.playFiled.forEach(cell -> {
			if(cell.equals(this.letterSettedAtTurn))
				cell.resetCell();
			if(cell.getCellState() != CellState.CELL_IS_BUSY)
				cell.revertCellState();
		});
		this.letterSettedAtTurn = null;
		Cell.resetStaticIndex();
	}

	/** Очистить поле полностью
	 * */
	public void clearAll(){
		this.playFiled.forEach(Cell::resetCell);
	}

	/** Является ли поле заполненным
	 * */
	public boolean isFullField(){
		boolean isFull = true;
		for(Cell elem : this.playFiled)
			if (elem.getCellState() == CellState.CELL_IS_EMPTY) {
				isFull = false;
				break;
			}

		return isFull;
	}
}
