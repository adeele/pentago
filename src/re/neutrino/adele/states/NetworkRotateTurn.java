package re.neutrino.adele.states;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents network state of game while there is a rotation turn
 * Created by adele on 6/7/16.
 */
public class NetworkRotateTurn extends BaseState {
    public NetworkRotateTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
    }

    @Override
    public BaseState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        return null;
    }

}
