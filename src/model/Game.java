package model;

import event.GameStateEvent;
import event.GameStateListener;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game {

	private GameState currentState; //текущее состояние игры
	private int currentPlayerIndex = 0; //индекс текущего игрока
	private final GameField field; // игровое поле
	private final Alphabet alphabet; // алфавит
	private final WordManager wordManager; // менеджер слов
	private Player currentPlayer; //текущий игрок
	private final ArrayList<Player> players = new ArrayList<>(); //список игроков
	private final GameDifficult gameDifficult; //сложность игры
	private final ArrayList<GameStateListener> listeners = new ArrayList<>();//слушатели состояния игры

	/** Конструктор класса
	 * @param field игровое поле
	 * @param player1Name имя первого игрока
	 * @param player2Name имя второго игрока
	 * @throws IllegalArgumentException если имена игроков совпадают
	 * */
	public Game(@NotNull GameField field, @NotNull String player1Name, @NotNull String player2Name,
				GameDifficult difficult) throws FileNotFoundException {
		this.gameDifficult = difficult;
		this.field = field;

		if(difficult == GameDifficult.HARD) {
			this.alphabet = new CustomizedAlphabet("абвгдежзийклмнопрстуфхцчшщъыьэюя");
			this.listeners.add(((CustomizedAlphabet) this.alphabet).getListener());
			this.listeners.add(((CustomizedGameField)this.field).getListener());
		}
		else
			this.alphabet = new Alphabet("абвгдежзийклмнопрстуфхцчшщъыьэюя");

		this.wordManager = new WordManager();

		if(player1Name.equals(player2Name))
			throw new IllegalArgumentException("Имена игроков одинаковы");

		Player player1 = new Player(this.wordManager, player1Name, this.alphabet, this.field);
		Player player2 = new Player(this.wordManager, player2Name, this.alphabet, this.field);


		this.currentState = GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER;
		this.players.add(player1);
		this.players.add(player2);
		this.currentPlayer = player1;

		this.field.setStartedWord(this.wordManager.generateStartWord(this.field.getWidth()));
	}

	/** Получить текущий алфавит
	 * */
	public Alphabet getAlphabet() {
		return alphabet;
	}

	/** Получить игровое поле
	 * */
	public GameField getField() {
		return field;
	}

	/** Изменить текущего игрока
	 * */
	public void changePlayer() {
		this.currentPlayerIndex = this.currentPlayerIndex == 1 ? 0 : 1;
		this.currentPlayer = this.players.get(this.currentPlayerIndex);
		this.fireTurnIsEnded();
	}

	/**
	 * Оповещение о конце хода
	 * */
	private void fireTurnIsEnded(){
		GameStateEvent event = new GameStateEvent(this);
		event.setDifficult(this.gameDifficult);
		this.listeners.forEach(elem -> elem.turnIsEnded(event));
	}


	public WordManager getWordManager() {
		return wordManager;
	}

	/** Получить имя победителя
	 * @return имя победителя, null - если победитель не определен, "Ничья" - если ничья
	 * */
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

	/** Получить текущего игрока
	 * */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/** Получить текущее состояние
	 * */
	public GameState getCurrentState() {
		return this.currentState;
	}

	/** Перейти к следующему состоянию игры
	 * */
	public void updateCurrentState() {
		this.currentState = switch (this.currentState){
			case PLAYER_SELECT_CELL_FOR_INSERT_LETTER -> GameState.PLAYER_INSERTING_LETTER;
			case PLAYER_INSERTING_LETTER -> GameState.PLAYER_SELECTING_CHARS;
			case PLAYER_SELECTING_CHARS ->  GameState.PLAYER_SUBMITTED_TURN;
			case PLAYER_SUBMITTED_TURN -> GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER;
		};
	}

	/** Откатится к предыдущему состоянию игры
	 * */
	public void revertState() {
		this.currentState = GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER;
		this.field.revertField();
	}

	/** Закончить игру
	 * */
	public void endGame() {
		this.wordManager.clearAll();
		this.field.clearAll();

		if(this.gameDifficult == GameDifficult.HARD)
			((CustomizedAlphabet) this.alphabet).clearBlockedChars();
	}

	/** Получить всех игроков, задействованных в игре
	 * */
	public List<Player> getPlayers() {
		return new ArrayList<>(players);
	}
}
