package re.neutrino.adele;

import re.neutrino.adele.models.Ball;

/**
 * Event generated after field changes
 */
public class FieldChangedEvent {
    private final int x;
    private final int y;
    private final Ball ball;

    /**
     * Creates an event, which is placing ball on the specific place
     * @param x x-coordinate
     * @param y y-coordinate
     * @param ball color
     */
    public FieldChangedEvent(int x, int y, Ball ball) {
        this.x = x;
        this.y = y;
        this.ball = ball;
    }

    /**
     * Gives the x-coordinate of the changed field
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gives the y-coordinate of the changed field
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gives the color of the ball placed
     * @return ball color
     */
    public Ball getBall() {
        return ball;
    }
}
