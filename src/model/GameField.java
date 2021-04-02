package model;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GameField {

	private ArrayList<Cell> playFiled;
	private Cell letterSettedAtTurn;

	public GameField(int width, int height) {
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				playFiled.add(new Cell(new Point(i,j)));
		//todo adding neighbor here
	}

	public int getWidth() {
		return this.playFiled.stream()
				.mapToInt(v -> (int) v.getCellPosition().getX())
				.max().orElseThrow(NoSuchElementException::new);
	}

	public int getHeight() {
		return this.playFiled.stream()
				.mapToInt(v -> (int) v.getCellPosition().getY())
				.max().orElseThrow(NoSuchElementException::new);
	}

	public Cell getCellByPoint(Point point) {
		return this.playFiled.stream()
				.filter(elem -> elem.getCellPosition().equals(point))
				.findAny()
				.orElse(null);
	}

	public void setLetterAtTurn(char letter, Cell cell) {
		if(cell != null){
			cell.setLetter(letter);
			this.letterSettedAtTurn = cell;
		}
	}

	public void selectCell(Cell cell) {
		//fixme maybe this useless
		if(cell != null){
			cell.updateCellState();
		}
	}

	public boolean isAvailableCell(Point point, GameState gameState) {
		boolean isAvailable = false;

		switch (gameState){
			case PLAYER_INSERTING_LETTER:
				//do something
				break;
			case PLAYER_SELECTING_CHARS:
				//do something
				break;
			default:
				//do something
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

	public String getWordSettedAtTurn() {
		return this.playFiled.stream()
				.filter(cell -> cell.getCellState() == CellState.CELL_IS_SELECTED)
				.sorted((Comparator.comparingInt(Cell::getSelectedIndex)))
				.collect(Collectors.toCollection(ArrayList::new)).toString();
	}

	public void cleanFieldAfterPlayersTurn() {
		this.getAllSelectedCells().forEach(Cell::updateCellState);
		this.letterSettedAtTurn = null;
	}

}
