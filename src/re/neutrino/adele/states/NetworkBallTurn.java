package re.neutrino.adele.states;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents network state while there is a ball turn
 */
public class NetworkBallTurn extends BaseState {
    public NetworkBallTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
    }

    @Override
    public BaseState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        return null;
    }
}