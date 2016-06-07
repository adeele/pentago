package re.neutrino.adele;

import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Created by adele on 6/7/16.
 */
public class RotateTurn extends RoundState {
    public RotateTurn(BoardCtrl context, Ball color) {
        super(context, color);
        // context.setArrowsVisible(true);
    }

    @Override
    public RoundState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        return new BallTurn(context, getOppositeColor());
    }

    public Ball getOppositeColor() {
        // TODO move to ball enum
        if(ball == Ball.WHITE)
            return Ball.BLACK;
        return Ball.WHITE;
    }
}
