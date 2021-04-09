package model;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Alphabet {

	private String container;

	/**Конструктор класса
	 * @param alphabet алфавит, который будет использоваться в игре
	 *                 todo - ограничиение: алфавит - это один язык и только символы - done
	 * */
	public Alphabet(@NotNull String alphabet) {
		if(alphabet.matches("^[a-zA-Z]+$|^[а-яА-Я]+$"))
			this.container = alphabet;
		else
			throw new IllegalArgumentException();
	}

	/**Геттер алфавита
	 * @return текущий алфавит
	 * */
	public String getCurrentAlphabet() {
		return this.container;
	}

	/**Сеттер текущего алфавита
	 * @param alphabet новый алфавит
	 *                 todo добавить проверку на ограничение как в конструк. - done
	 * */
	public void setCurrentAlphabet(@NotNull String alphabet) {
		if(alphabet.matches("^[a-zA-Z]+$|^[а-яА-Я]+$"))
			this.container = alphabet;
		else
			throw new IllegalArgumentException();
	}

	/**Проверка на наличие символа в алфавите
	 * @param letter символ, который нужно проверить на наличие
	 * @return находится ли он внутри алфавита
	 * */
	public boolean letterHasInAlphabet(char letter){
		return this.container.toLowerCase(Locale.ROOT).indexOf(letter) != -1;
	}
}
