package model;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Alphabet {

	private String container;

	public Alphabet(@NotNull String alphabet) {
		this.container = alphabet;
	}

	public String getCurrentAlphabet() {
		return this.container;
	}

	public void setCurrentAlphabet(String alphabet) {
		this.container = alphabet;
	}

	public boolean letterHasInAlphabet(char letter){
		return this.container.toLowerCase(Locale.ROOT).indexOf(letter) != -1;
	}
}
