package re.neutrino.adele;

import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Created by adele on 6/7/16.
 */
public abstract class RoundState {
    protected final BoardCtrl context;
    protected Ball ball;

    public RoundState(BoardCtrl context, Ball ball) {
        this.context = context;
        this.ball = ball;
    }
    public abstract RoundState handleClick(Circle[] circles, Rectangle[] arrows, Point point);
}
