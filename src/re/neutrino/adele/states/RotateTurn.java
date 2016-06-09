package re.neutrino.adele.states;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents rotation state
 */
public class RotateTurn extends BaseState {
    public RotateTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
        context.setArrowsVisible(true);
    }

    @Override
    public BaseState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        for (int i = 0; i < 8; i++) {
            if (arrows[i].contains(point)) {
                if(context.rotateAndCheck(i, ball))
                    return new EndOfGameState(context, ball);
                if(context.isOnline())
                    return new NetworkBallTurn(context, ball.getOpposite());
                return new BallTurn(context, ball.getOpposite());
            }
        }
        return this;
    }
}
