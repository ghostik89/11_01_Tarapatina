package model;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GameField {

	private final ArrayList<Cell> playFiled = new ArrayList<>();
	private Cell letterSettedAtTurn;

	public GameField(int width, int height) {
		if(width < 3 || height < 3 || width > 29)
			throw new IllegalArgumentException();
		this.generateField(width, height);
	}

	private void generateField(int width, int height){
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				this.playFiled.add(new Cell(new Point(x,y)));

		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++){
				try{
					if(x > 0)
						this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x-1,y)));
					if(y > 0)
						this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x,y-1)));
					if(y < height-1)
						this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x,y+1)));
					if(x < width - 1)
						this.getCellByPoint(new Point(x,y)).setNeighbor(this.getCellByPoint(new Point(x+1,y)));
				}catch (IllegalArgumentException ex){
					if(!ex.getMessage().equals("We has this neighbor"))
						throw new IllegalArgumentException();
				}catch (NullPointerException ignored){}
			}
	}


	public List<Cell> getPlayField(){
		return new ArrayList<>(this.playFiled);
	}

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

	public int getWidth() {
		return this.playFiled.stream()
				.mapToInt(v -> (int) v.getCellPosition().getX())
				.max().orElseThrow(NoSuchElementException::new) + 1;
	}

	public int getHeight() {
		return this.playFiled.stream()
				.mapToInt(v -> (int) v.getCellPosition().getY())
				.max().orElseThrow(NoSuchElementException::new) + 1;
	}

	private Cell getCellByPoint(@NotNull Point point) {
		return this.playFiled.stream()
				.filter(elem -> elem.getCellPosition().equals(point))
				.findAny()
				.orElse(null);
	}

	public void selectCellForInsertLetterByPoint(@NotNull Point point) {
		Cell cell = this.getCellByPoint(point);
		if(cell != null) {
			cell.updateCellState();
			this.letterSettedAtTurn = cell;
		}
		else
			throw new IllegalArgumentException();
	}

	public void selectCellByPoint(@NotNull Point point){
		Cell cell = this.getCellByPoint(point);
		if(cell != null) {
			cell.updateCellState();
			cell.setIndex();
		}
		else
			throw new IllegalArgumentException();
	}

	public void setCharIntoCellAtTurn(char letter){
		this.letterSettedAtTurn.setLetter(letter);
		this.letterSettedAtTurn.updateCellState();
	}


	public boolean isAvailableCell(@NotNull Point point,@NotNull GameState gameState) {
		boolean isAvailable = false;
		Cell cell = this.getCellByPoint(point);

		if (cell == null)
			throw new IllegalArgumentException();

		if (gameState == GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER
				|| gameState == GameState.PLAYER_INSERTING_LETTER) {
			for (Cell elem : cell.getNeighbors())
				if (elem.getCellState() == CellState.CELL_IS_BUSY) {
					isAvailable = true;
					break;
				}
		} else if (gameState == GameState.PLAYER_SELECTING_CHARS) {
			if (this.getAllSelectedCells().isEmpty())
				for (Cell elem : cell.getNeighbors())
					if (elem.getCellState() == CellState.CELL_IS_BUSY) {
						isAvailable = true;
						break;
					} else {
						for (Cell newElem : cell.getNeighbors())
							if (newElem.getCellState() == CellState.CELL_IS_SELECTED) {
								isAvailable = true;
								break;
							}
					}
		}

		return isAvailable;
	}

	public List<Cell> getAllSelectedCells() {
		return this.playFiled.stream()
				.filter(cell -> cell.getCellState() == CellState.CELL_IS_SELECTED)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public Cell getLetterSettedAtTurn() {
		return this.letterSettedAtTurn;
	}

	public List<Cell> getPlayFiled() {
		return new ArrayList<>(playFiled);
	}

	public String getWordSettedAtTurn() {
		return this.playFiled.stream()
				.filter(cell -> cell.getCellState() == CellState.CELL_IS_SELECTED)
				.sorted((Comparator.comparingInt(Cell::getSelectedIndex))).map(Cell::getLetter)
				.map(String::valueOf).collect(Collectors.joining());
	}

	public void cleanFieldAfterPlayersTurn() {
		this.getAllSelectedCells().forEach(Cell::updateCellState);
		this.letterSettedAtTurn = null;
	}

	public void clearAll(){
		this.playFiled.forEach(Cell::resetCell);
	}

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
