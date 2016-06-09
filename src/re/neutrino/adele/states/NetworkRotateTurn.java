package re.neutrino.adele.states;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;

/**
 * Represents network state of game while there is a rotation turn
 * Created by adele on 6/7/16.
 */
class NetworkRotateTurn extends BaseState {
    NetworkRotateTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
        RotateMove move = context.readRotation();
        context.rotateAndCheck(move.getI(), ball);
        context.setNextTurn(new BallTurn(context,ball.getOpposite()));
    }
}
