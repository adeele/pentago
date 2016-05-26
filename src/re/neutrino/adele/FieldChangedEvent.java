package re.neutrino.adele;

/**
 * FieldChanged event object
 */
public class FieldChangedEvent {
    private final int x;
    private final int y;
    private final Ball ball;

    public FieldChangedEvent(int x, int y, Ball ball) {
        this.x = x;
        this.y = y;
        this.ball = ball;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ball getBall() {
        return ball;
    }
}
