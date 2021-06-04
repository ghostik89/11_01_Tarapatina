package event;

import model.GameDifficult;
import model.GameState;

import java.util.EventObject;

public class GameStateEvent extends EventObject {
    private GameState state;
    private GameDifficult difficult;

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameDifficult getDifficult() {
        return difficult;
    }

    public void setDifficult(GameDifficult difficult) {
        this.difficult = difficult;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameStateEvent(Object source) {
        super(source);
    }
}
