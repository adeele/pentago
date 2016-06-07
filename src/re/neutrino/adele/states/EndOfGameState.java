package re.neutrino.adele.states;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.controllers.BoardCtrl;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Represents end of game state
 */
class EndOfGameState extends BaseState {

    EndOfGameState(BoardCtrl context, Ball ball) {
        super(context, ball);
        context.setLabel(getLabel());
    }

    @Override
    public BaseState handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        return this;
    }

    private String getLabel() {
        return String.format(GameConstant.WIN_FORMAT, ball.name());
    }
}
