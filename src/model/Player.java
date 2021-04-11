package model;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Player {

	private final String name;

	private final GameField gameField;

	private final WordManager wordManager;

	private final Alphabet alphabet;

	//todo ограничение на имя
	//done
	public Player(@NotNull WordManager wordManager,@NotNull String name,
				  @NotNull Alphabet alphabet,@NotNull GameField gameField) {
		this.wordManager = wordManager;
		if(name.isEmpty() || name.isBlank())
			throw new IllegalArgumentException();

		this.name = name;
		this.alphabet = alphabet;
		this.gameField = gameField;
	}

	public int calcScore() {
		return wordManager
				.getAllSolvedWordsByPlayer(this)
				.stream().sequential().map(String::length).reduce(0, Integer::sum);
	}

	public String getName() {
		return this.name;
	}

	public void selectCellForInsertLetter(Point point){
		//todo create update cell by point - done
		this.gameField.selectCellForInsertLetterByPoint(point);
	}

	public void insertLetterIntoCell(char letter) {
		if(this.alphabet.letterHasInAlphabet(letter))
			this.gameField.setCharIntoCellAtTurn(letter);
		else
			throw new IllegalArgumentException();
	}

	public void selectCell(Point point) {
		// todo select cell by point in select - done
		this.gameField.selectCellByPoint(point);
	}

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
