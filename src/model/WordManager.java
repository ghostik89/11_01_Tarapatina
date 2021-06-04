package model;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.stream.Collectors;

public class WordManager {

	private final ArrayList<String> dictionary = new ArrayList<>(); // словарь
	private final HashMap<String, Player> allSolvedWords = new HashMap<>(); // все вписанные слова игроков
	private String startedWord = ""; // стартовое слово

	/** Конструктор класса
	 * @throws FileNotFoundException если файл со словарем не найден в текущей директории
	 * */
	public WordManager() throws FileNotFoundException {
		File myDict = new File("./dictionaries/russian_nouns.txt");
		Scanner myReader = new Scanner(myDict);
		while(myReader.hasNextLine()){
			dictionary.add(myReader.nextLine());
		}
		myReader.close();
	}

	/** Проверка на наличие слова в словаре
	 * @param word слово, которое мы хотим проверить
	 * */
	public boolean hasWordInDictionary(@NotNull String word) {
		return dictionary.contains(word);
	}

	/** Сгенерировать стартовое слово
	 * @param length длина стартового слова
	 * @return сгенерированное стартовое слово
	 * */
	public String generateStartWord(int length) {
		ArrayList<String> allFoundedWords = this.dictionary.stream()
				.filter(word -> word.length() == length).collect(Collectors.toCollection(ArrayList::new));

		int random = new Random().nextInt(allFoundedWords.size());
		this.startedWord = allFoundedWords.get(random);
		return allFoundedWords.get(random);
	}

	/** Добавить слово в словарь
	 * @param word слово, которое мы хотим добавить в словарь
	 * */
	public void addWordToDictionary(@NotNull String word) {
		this.dictionary.add(word);
	}

	/** Добавить слово в общий пул найденных слов
	 * @param word слово, которое мы хотим добавить
	 * @param player игрок, который нашел слово
	 * @return успешность добавление слова(true, false)
	 * */
	public boolean addToSolvedWords(@NotNull Player player,@NotNull String word) {
		boolean canAdd = this.allSolvedWords.get(word) == null;
		if(canAdd)
			this.allSolvedWords.put(word, player);

		return canAdd;
	}

	/** Очистить весь пул решенных слов
	 * */
	public void clearAll(){
		this.allSolvedWords.clear();
	}

	/** Получить текущий словарь
	 * */
	public List<String> getDict() {return new ArrayList<>(this.dictionary);}

	/** Получить словарь всех слов, найденных игроков
	 * */
	public List<String> getAllSolvedWordsByPlayer(@NotNull Player player) {
		return this.allSolvedWords.entrySet().stream()
				.filter(stringPlayerEntry -> stringPlayerEntry.getValue().equals(player))
				.map(Map.Entry::getKey)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/** Получить стартовое слово
	 * */
	public String getStartedWord() {
		return startedWord;
	}

	//test only!!
	public void setStartedWord(@NotNull String word){
		this.startedWord = word;
	}
}
