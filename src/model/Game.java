package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game {

	private GameState currentState;

	private int currentPlayerIndex = 0;

	private Player currentPlayer;

	private ArrayList<Player> players;

	public Game(GameField field) throws FileNotFoundException {
		Alphabet alphabet = new Alphabet("абвгдеёжзийклмнопрстуфхцчшщъыьэюя");
		WordManager wordManager = new WordManager();
		Player player1 = new Player(wordManager, "Player 1", alphabet, field);
		Player player2 = new Player(wordManager, "Player 2", alphabet, field);

		this.currentState = GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER;
		this.players.add(player1);
		this.players.add(player2);
		this.currentPlayer = player1;
	}

	public void changePlayer() {
		this.currentPlayerIndex = this.currentPlayerIndex == 1 ? 0 : 1;
		this.currentPlayer = this.players.get(this.currentPlayerIndex);
	}

	public String determinateWinner() {
		String winner = null;

		if(this.currentPlayer.getGameFiled().isFullField())
			winner = this.players.get(0).calcScore() > this.players.get(1).calcScore()?
					this.players.get(0).getName() : this.players.get(1).getName();

		return winner;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public GameState getCurrentState() {
		return this.currentState;
	}

	public void updateCurrentState() {
		switch (this.currentState){
			case PLAYER_SELECT_CELL_FOR_INSERT_LETTER -> {
				this.currentState = GameState.PLAYER_INSERTING_LETTER;
			}
			case PLAYER_INSERTING_LETTER -> {
				this.currentState = GameState.PLAYER_SELECTING_CHARS;
			}
			case PLAYER_SELECTING_CHARS -> {
				this.currentState = GameState.PLAYER_SUBMITTED_TURN;
			}
			case PLAYER_SUBMITTED_TURN -> {
				this.currentState = GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER;
			}
		}
	}

	public void revertState() {
		switch (this.currentState){
			case PLAYER_INSERTING_LETTER -> {
				this.currentState = GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER;
			}
			case PLAYER_SELECTING_CHARS -> {
				this.currentState = GameState.PLAYER_INSERTING_LETTER;
			}
			case PLAYER_SUBMITTED_TURN -> {
				this.currentState = GameState.PLAYER_SELECTING_CHARS;
			}
		}
	}


	public void endGame() {
		this.currentPlayer.getWordManager().clearAll();
		this.currentPlayer.getGameFiled().clearAll();
	}

}


enum GameState {
	PLAYER_SELECT_CELL_FOR_INSERT_LETTER,
	PLAYER_INSERTING_LETTER,
	PLAYER_SELECTING_CHARS,
	PLAYER_SUBMITTED_TURN
}
