package model;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.stream.Collectors;

public class WordManager {

	private final ArrayList<String> dictionary = new ArrayList<>();

	private final HashMap<String, Player> allSolvedWords = new HashMap<>();

	public WordManager() throws FileNotFoundException {
		File myDict = new File("./dictionaries/russian_nouns.txt");
		Scanner myReader = new Scanner(myDict);
		while(myReader.hasNextLine()){
			dictionary.add(myReader.nextLine());
		}
		myReader.close();
	}

	public boolean hasWordInDictionary(@NotNull String word) {
		return dictionary.contains(word);
	}

	public String generateStartWord(int length) {
		ArrayList<String> allFoundedWords = this.dictionary.stream()
				.filter(word -> word.length() == length).collect(Collectors.toCollection(ArrayList::new));

		int random = new Random().nextInt(allFoundedWords.size());
		return allFoundedWords.get(random);
	}

	public void addWordToDictionary(@NotNull String word) {
		this.dictionary.add(word);
	}

	public boolean addToSolvedWords(@NotNull Player player,@NotNull String word) {
		boolean canAdd = this.allSolvedWords.get(word) == null;
		if(canAdd)
			this.allSolvedWords.put(word, player);

		return canAdd;
	}

	public void clearAll(){
		this.allSolvedWords.clear();
	}

	public List<String> getAllSolvedWordsByPlayer(@NotNull Player player) {
		return this.allSolvedWords.entrySet().stream()
				.filter(stringPlayerEntry -> stringPlayerEntry.getValue().equals(player))
				.map(Map.Entry::getKey)
				.collect(Collectors.toCollection(ArrayList::new));
	}
}
