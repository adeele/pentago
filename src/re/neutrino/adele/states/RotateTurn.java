package re.neutrino.adele.states;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents rotation state
 */
class RotateTurn extends BaseState
{
    /**
     * State which rotates the board
     * @param context link to the controller
     * @param ball color of the player
     */
    RotateTurn(BoardCtrl context, Ball ball)
    {
        super(context, ball);
        context.setArrowsVisible(true);
    }

    /**
     * Handle click
     */
    @Override
    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point)
    {
        for (int i = 0; i < 8; i++)
        {
            if (arrows[i].contains(point))
            {
                if (context.isOnline())
                    context.submitRotate(new RotateMove((byte) context.witchSquare(i), (byte) context.witchWay(i)));
                if (context.rotateAndCheck(i))
                {
                    context.setNextTurn(new EndOfGameState(context, ball));
                    return;
                }
                if (context.isOnline())
                {
                    context.setNextTurn(new NetworkBallTurn(context, ball.getOpposite()));
                    return;
                }
                context.setNextTurn(new BallTurn(context, ball.getOpposite()));
                return;
            }
        }
    }
}
