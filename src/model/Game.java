package model;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game {

	private GameState currentState;

	private int currentPlayerIndex = 0;

	//todo create gamefield, alphabet, wordmanager
	//done
	private final GameField field;

	public Alphabet getAlphabet() {
		return alphabet;
	}

	public GameField getField() {
		return field;
	}

	private final Alphabet alphabet;

	private final WordManager wordManager;

	private Player currentPlayer;

	private final ArrayList<Player> players = new ArrayList<>();

	public Game(@NotNull GameField field, @NotNull String player1Name, @NotNull String player2Name)
			throws FileNotFoundException {
		this.alphabet = new Alphabet("абвгдежзийклмнопрстуфхцчшщъыьэюя");
		this.wordManager = new WordManager();
		this.field = field;

		if(player1Name.equals(player2Name))
			throw new IllegalArgumentException("Player's names equals");

		Player player1 = new Player(this.wordManager, player1Name, this.alphabet, this.field);
		Player player2 = new Player(this.wordManager, player2Name, this.alphabet, this.field);


		this.currentState = GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER;
		this.players.add(player1);
		this.players.add(player2);
		this.currentPlayer = player1;
	}

	public void changePlayer() {
		this.currentPlayerIndex = this.currentPlayerIndex == 1 ? 0 : 1;
		this.currentPlayer = this.players.get(this.currentPlayerIndex);
	}

	//test only
	public WordManager getWordManager() {
		return wordManager;
	}

	public String determinateWinner() {
		String winner = null;

		if(this.field.isFullField())
			if(this.players.get(0).calcScore() == this.players.get(1).calcScore())
				winner = "Ничья";
			else if(this.players.get(0).calcScore() > this.players.get(1).calcScore())
				winner = this.players.get(0).getName();
			else
				winner = this.players.get(1).getName();

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
		this.wordManager.clearAll();
		this.field.clearAll();
	}

	public List<Player> getPlayers() {
		return new ArrayList<>(players);
	}
}
