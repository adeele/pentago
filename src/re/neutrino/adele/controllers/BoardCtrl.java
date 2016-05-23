package re.neutrino.adele.controllers;

import re.neutrino.adele.views.BoardView;
import re.neutrino.adele.views.Circle;

import java.awt.*;

/**
 * Controller for BoardView
 */
public class BoardCtrl {
    public void handleClick(BoardView caller, Circle[] circles, Point point) {
        for (Circle c : circles) {
           if (c.contains(point)) {
               c.setWhite();
               caller.repaint();
           }
        }
    }
}
