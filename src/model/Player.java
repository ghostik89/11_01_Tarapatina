package model;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Player {

	private final String name; //имя игрока
	private final GameField gameField; // игровое поле
	private final WordManager wordManager; // менеджер слов
	private final Alphabet alphabet; // алфавит

	//todo ограничение на имя
	//done
	/** Коструктор класса
	 * @param alphabet алфавит
	 * @param gameField поле
	 * @param name имя игрока
	 * @param wordManager менеджер слов
	 * @throws IllegalArgumentException если имя постое
	 * */
	public Player(@NotNull WordManager wordManager,@NotNull String name,
				  @NotNull Alphabet alphabet,@NotNull GameField gameField) {
		this.wordManager = wordManager;
		if(name.isEmpty() || name.isBlank())
			throw new IllegalArgumentException();

		this.name = name;
		this.alphabet = alphabet;
		this.gameField = gameField;
	}

	/** Посчитать количество очков у игрока
	 * */
	public int calcScore() {
		return wordManager
				.getAllSolvedWordsByPlayer(this)
				.stream().sequential().map(String::length).reduce(0, Integer::sum);
	}

	/** Получить имя игрока
	 * */
	public String getName() {
		return this.name;
	}

	/** Выбрать клетку, чтобы вставить в нее букву
	 * @param point координата клетки, в которую необходимо вставить букву
	 * */
	public void selectCellForInsertLetter(Point point){
		//todo create update cell by point - done
		this.gameField.selectCellForInsertLetterByPoint(point);
	}

	/** Вставить букву в клетку
	 * @param letter буква, которая будет вставлена в клетку
	 * */
	public void insertLetterIntoCell(char letter) {
		if(this.alphabet.letterHasInAlphabet(letter))
			this.gameField.setCharIntoCellAtTurn(letter);
		else
			throw new IllegalArgumentException();
	}

	/** Выбрать клетку
	 * @param point координата клетки, в которую необходимо выбрать
	 * */
	public void selectCell(Point point) {
		// todo select cell by point in select - done
		this.gameField.selectCellByPoint(point);
	}

	/** Проверка хода игрока
	 * @return TURN_SUCCESS - если ход закончен успешно
	 * WORDMANAGER_ERROR_NOT_FOUND - слово не найдено в словаре
	 * WORDMANAGER_ERROR_IS_SOLVED - слово ранее было игре
	 * GAMEFIELD_HAS_NOT_LETTER_SET_AT_TURN - не включена клетка, в которой была поставлена бука
	 * */
	public SubmitState submitTurn() {
		SubmitState submitState = SubmitState.TURN_SUCCESS;
		String word = this.gameField.getWordSettedAtTurn();

		if(!(wordManager.hasWordInDictionary(word)))
			submitState = SubmitState.WORDMANAGER_ERROR_NOT_FOUND;
		else if(!wordManager.addToSolvedWords(this, word) || wordManager.getStartedWord().equals(word))
			submitState = SubmitState.WORDMANAGER_ERROR_IS_SOLVED;
		else if(!this.gameField.getAllSelectedCells().contains(this.gameField.getLetterSettedAtTurn()))
			submitState = SubmitState.GAMEFIELD_HAS_NOT_LETTER_SET_AT_TURN;

		return submitState;
	}

}
