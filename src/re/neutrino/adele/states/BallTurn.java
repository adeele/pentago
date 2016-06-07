package re.neutrino.adele.states;

import re.neutrino.adele.*;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents ball placing state of the game
 */
public class BallTurn extends BaseState {

    public BallTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
        context.setArrowsVisible(false);
        context.setLabel(getLabel());
    }

    private String getLabel() {
        return String.format(GameConstant.TURN_FORMAT, ball.name());
    }

    @Override
    public BaseState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        for (int i = 0; i < 36; i++) {
            if (circles[i].contains(point)) {
                if(context.canPlaceBall(i)) {
                    if (context.placeBallAndCheck(i, ball))
                        return new EndOfGameState(context, ball);
                    return new RotateTurn(context, ball);
                }
            }
        }
        return this;
    }
}