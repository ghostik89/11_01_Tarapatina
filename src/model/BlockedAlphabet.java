package model;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BlockedAlphabet extends Alphabet{
    private final Random random = new Random();
    private String blockedChars = "";
    /**
     * Конструктор класса
     *
     * @param alphabet алфавит, который будет использоваться в игре
     * @throws IllegalArgumentException если алфавит состоит из букв разного алфавита или пуст
     */
    public BlockedAlphabet(@NotNull String alphabet) {
        super(alphabet);
    }

    public void blockLetter(){
        int blockIndex = random.nextInt(this.getCurrentAlphabet().length() + 1);
        if(this.blockedChars.contains(String.valueOf(this.getCurrentAlphabet().charAt(blockIndex))))
            blockLetter();
        else
            this.blockedChars += String.valueOf(this.getCurrentAlphabet().charAt(blockIndex));
    }

    public String getBlockedChars() {
        return blockedChars;
    }
}
