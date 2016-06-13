package re.neutrino.adele.models.network;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents generic state of the game
 */
public abstract class BaseState
{
    protected final BoardCtrl context;
    protected Ball ball;

    /**
     * Creates state
     * @param context link to the controller
     * @param ball color of the turn
     */
    protected BaseState(BoardCtrl context, Ball ball)
    {
        this.context = context;
        this.ball = ball;
    }

    /**
     * Children has to implement handle click method
     * @param circles array of the ball places
     * @param arrows array of the arrows coordinates
     * @param point clicked
     */
    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point)
    {
    }

    /**
     * Sets label of the turn
     * @return label
     */
    protected String getLabel()
    {
        return String.format(GameConstant.TURN_FORMAT, ball.name());
    }
}
