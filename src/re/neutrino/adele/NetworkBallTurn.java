package re.neutrino.adele;

import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Created by adele on 6/7/16.
 */
public class NetworkBallTurn extends RoundState {
    public NetworkBallTurn(BoardCtrl context, Ball ball) {
        super(context, ball);
    }

    @Override
    public RoundState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        return null;
    }
}