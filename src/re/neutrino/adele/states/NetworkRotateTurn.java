package re.neutrino.adele.states;

import re.neutrino.adele.controllers.RotateHandler;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;

/**
 * Represents network state of game while there is a rotation turn
 * Created by adele on 6/7/16.
 */
class NetworkRotateTurn extends BaseState {
    NetworkRotateTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
        context.readAsync(new RotateHandler() {
            @Override
            public void handleError(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void handleRotate(RotateMove move) {
                context.rotateAndCheck(move.getI());
                context.setNextTurn(new BallTurn(context,ball.getOpposite()));
            }
        });
    }
}
