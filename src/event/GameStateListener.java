package event;

import java.util.EventListener;

public interface GameStateListener extends EventListener {
    void turnIsEnded(GameStateEvent e);
}
