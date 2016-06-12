package re.neutrino.adele.states;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents generic state of the game
 */
public abstract class BaseState {
    final BoardCtrl context;
    protected Ball ball;

    BaseState(BoardCtrl context, Ball ball) {
        this.context = context;
        this.ball = ball;
    }
    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
    }

    String getLabel() {
        return String.format(GameConstant.TURN_FORMAT, ball.name());
    }
}
