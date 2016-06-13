package re.neutrino.adele.controllers.states;

import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.models.network.BallMove;
import re.neutrino.adele.models.network.BaseState;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents ball placing state of the game
 */
public class BallTurn extends BaseState
{
    /**
     * Creates turn on which the ball is placing
     * @param context link to the controller
     * @param ball color of the player
     */
    public BallTurn(BoardCtrl context, Ball ball)
    {
        super(context, ball);
        context.setArrowsVisible(false);
        context.setLabel(super.getLabel());
    }

    /**
     * Handle click
     * @param circles array of the ball places
     * @param arrows array of the arrows coordinates
     * @param point clicked
     */
    @Override
    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point)
    {
        for (int i = 0; i < 36; i++)
        {
            if (circles[i].contains(point))
            {
                if (context.canPlaceBall(i))
                {
                    if (context.isOnline())
                        context.submitBall(new BallMove(i % 6, i / 6));
                    if (context.placeBallAndCheck(i, ball))
                    {
                        context.setNextTurn(new EndOfGameState(context, ball));
                        return;
                    }
                    context.setNextTurn(new RotateTurn(context, ball));
                    return;
                }
            }
        }
    }
}