package re.neutrino.adele.controllers.states;

import re.neutrino.adele.controllers.network.BallHandler;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.models.network.BallMove;
import re.neutrino.adele.models.network.BaseState;

/**
 * Represents network state while there is a ball turn
 */
public class NetworkBallTurn extends BaseState
{
    /**
     * Creates network turn which waits for message about ball placed
     * @param context link to the controller
     * @param ball color of the waited ball
     */
    public NetworkBallTurn(BoardCtrl context, Ball ball)
    {
        super(context, ball);
        context.setArrowsVisible(false);
        context.setLabel(getLabel());
        context.readAsync(new BallHandler()
        {
            @Override
            public void handleBall(BallMove move)
            {
                if (context.placeBallAndCheck(move.getI(), ball))
                {
                    context.setNextTurn(new EndOfGameState(context, ball));
                    return;
                }
                context.setNextTurn(new NetworkRotateTurn(context, ball));
            }

            @Override
            public void handleError(Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}