package re.neutrino.adele.states;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;

/**
 * Represents network state while there is a ball turn
 */
public class NetworkBallTurn extends BaseState {
    public NetworkBallTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
        BallMove move = context.readBall();
        context.placeBallAndCheck(move.getI(), ball);
        context.setNextTurn(new NetworkRotateTurn(context, ball));
    }
}