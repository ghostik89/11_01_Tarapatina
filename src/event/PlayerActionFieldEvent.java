package event;
import java.awt.*;
import java.util.EventObject;

public class PlayerActionFieldEvent extends EventObject {
    private char letter;
    private Point point;

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public PlayerActionFieldEvent(Object source) {
        super(source);
    }
}
