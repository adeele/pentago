package re.neutrino.adele;

/**
 * FieldChanged event object
 */
public class FieldChangedEvent {
    private final int x;
    private final int y;
    private final Ball ball;

    /**
     * Creates an event, which is placing ball on coords (x, y)
     * @param x coord
     * @param y coord
     * @param ball ball
     */
    public FieldChangedEvent(int x, int y, Ball ball) {
        this.x = x;
        this.y = y;
        this.ball = ball;
    }

    /**
     * Gives the x-cord of the field
     * @return x-coord
     */
    public int getX() {
        return x;
    }

    /**
     * Gives the y-coord of the field
     * @return y-coord
     */
    public int getY() {
        return y;
    }

    /**
     * Gives the ball of the ball placed
     * @return ball ball
     */
    public Ball getBall() {
        return ball;
    }
}
