package model;

import event.GameStateEvent;
import event.GameStateListener;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CustomizedAlphabet extends Alphabet{
    private final Random random = new Random();
    private String blockedChars = "";
    private final GameStateListener listener = new GameStateObserver();
    /**
     * Конструктор класса
     *
     * @param alphabet алфавит, который будет использоваться в игре
     * @throws IllegalArgumentException если алфавит состоит из букв разного алфавита или пуст
     */
    public CustomizedAlphabet(@NotNull String alphabet) {
        super(alphabet);
    }

    protected void blockLetter(){
        int blockIndex = random.nextInt(this.getCurrentAlphabet().length() + 1);
        if(this.blockedChars.contains(String.valueOf(this.getCurrentAlphabet().charAt(blockIndex))))
            blockLetter();
        else
            this.blockedChars += String.valueOf(this.getCurrentAlphabet().charAt(blockIndex));
    }

    public String getBlockedChars() {
        return blockedChars;
    }

    public GameStateListener getListener() {
        return listener;
    }

    public void clearBlockedChars(){
        this.blockedChars = "";
    }

    private class GameStateObserver implements GameStateListener{

        @Override
        public void turnIsEnded(GameStateEvent e) {
            blockLetter();
        }
    }
}
