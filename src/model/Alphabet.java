package model;

public class Alphabet {

	private String container;

	public Alphabet(String alphabet) {
		this.container = alphabet;
	}

	public String getCurrentAlphabet() {
		return this.container;
	}

	public void setCurrentAlphabet(String alphabet) {
		this.container = alphabet;
	}

	public boolean letterHasInAlphabet(char letter){return this.container.indexOf(letter) != -1;}
}
