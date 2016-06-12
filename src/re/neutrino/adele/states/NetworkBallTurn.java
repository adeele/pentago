package re.neutrino.adele.states;

import re.neutrino.adele.controllers.BallHandler;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;

/**
 * Represents network state while there is a ball turn
 */
public class NetworkBallTurn extends BaseState {
    public NetworkBallTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
        context.setArrowsVisible(false);
        context.setLabel(getLabel());
        context.readAsync(new BallHandler() {
            @Override
            public void handleBall(BallMove move) {
                context.placeBallAndCheck(move.getI(), ball);
                context.setNextTurn(new NetworkRotateTurn(context, ball));
            }

            @Override
            public void handleError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}