package Model;
import java.awt.*;
import java.util.ArrayList;

public class GameField {

	private ArrayList playFiled;

	private Cell letterSetedAtTurn;

	private Player player;

	private Cell[] cell;

	public void GameField(int width, int height) {

	}

	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}

	public Cell getCellByPoint(Point point) {
		return null;
	}

	public void setLetterAtTurn(char letter, Cell cell) {

	}

	public void selectCell(Cell cell) {

	}

	public boolean isAwaibleCell(Point point) {
		return false;
	}

	public ArrayList getAllSelectedCells() {
		return null;
	}

	public Cell getLetterSettedAtTurn() {
		return null;
	}

	public String getWordSettedAtTurn() {
		return null;
	}

	public void cleanFieldAfterPlayersTurn() {

	}

}

enum GameState {
	PLAYER_INSERTING_LETTER,
	PLAYER_SELECTING_CHARS,
	PLAYER_SUBMITTED_TURN
}
