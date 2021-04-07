package model;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Alphabet {

	private String container;

	/**Конструктор класса
	 * @param alphabet алфавит, который будет использоваться в игре
	 * */
	public Alphabet(@NotNull String alphabet) {
		this.container = alphabet;
	}

	/**Геттер алфавита
	 * @return текущий алфавит
	 * */
	public String getCurrentAlphabet() {
		return this.container;
	}

	/**Сеттер текущего алфавита
	 * @param alphabet новый алфавит
	 * */
	public void setCurrentAlphabet(@NotNull String alphabet) {
		this.container = alphabet;
	}

	/**Проверка на наличие символа в алфавите
	 * @param letter символ, который нужно проверить на наличие
	 * @return находится ли он внутри алфавита
	 * */
	public boolean letterHasInAlphabet(char letter){
		return this.container.toLowerCase(Locale.ROOT).indexOf(letter) != -1;
	}
}
