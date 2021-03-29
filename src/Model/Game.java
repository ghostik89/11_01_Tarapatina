package Model;

import java.util.ArrayList;

public class Game {

	private GameState currentState;

	private Player players;

	private Player currentPlayer;

	private ArrayList<Player> player;

	private Alphabet alphabet;

	public void Game(GameField field) {

	}

	public void changePlayer() {

	}

	public String determinateWinner() {
		return null;
	}

	public void endGame() {

	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public static GameState getCurrentState() {
		return null;
	}

	public void updateCurrentState() {

	}

	public void revertState() {

	}

}
