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
    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        for (int i = 0; i < 8; i++) {
            if (arrows[i].contains(point)) {
                if(context.rotateAndCheck(i, ball)) {
                    context.setNextTurn(new EndOfGameState(context, ball));
                } else if(context.isOnline()) {
                    context.setNextTurn(new NetworkBallTurn(context, ball.getOpposite()));
                } else {
                    context.setNextTurn(new BallTurn(context, ball.getOpposite()));
                }
            }
        }
    }
}
