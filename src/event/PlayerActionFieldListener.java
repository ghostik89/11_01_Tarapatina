package event;

import java.util.EventListener;

public interface PlayerActionFieldListener extends EventListener {
    void playerClickToField(PlayerActionFieldEvent e);

    void playerClickOnAlphabet(PlayerActionFieldEvent e);
}
