package re.neutrino.adele;

import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Created by adele on 6/7/16.
 */
public class BallTurn extends RoundState {
    public BallTurn(BoardCtrl context, Ball color) {
        super(context, color);
    }

    @Override
    public RoundState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        for (int i = 0; i < 36; i++) {
            if (circles[i].contains(point)) {
                if (context.placeBallAndCheck(i, ball))
                    return new EndOfGame(context, ball);
            }
        }
        return new RotateTurn(context, ball);
    }
}