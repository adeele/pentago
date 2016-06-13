package re.neutrino.adele.states;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents end of game state
 */
class EndOfGameState extends BaseState
{
    /**
     * Creates end of game state
     * @param context link to the controller
     * @param ball color of the winner
     */
    EndOfGameState(BoardCtrl context, Ball ball)
    {
        super(context, ball);
        context.setLabel(getLabel());
        context.endOfGame();
    }

    /**
     * Sets end of game label
     * @return label
     */
    String getLabel()
    {
        return String.format(GameConstant.WIN_FORMAT, ball.name());
    }
}
