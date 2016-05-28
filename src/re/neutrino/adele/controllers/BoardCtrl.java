package re.neutrino.adele.controllers;

import re.neutrino.adele.models.BoardModel;
import re.neutrino.adele.views.BoardView;
import re.neutrino.adele.views.Circle;

import javax.swing.*;
import java.awt.*;

/**
 * Controller for BoardView
 */
public class BoardCtrl {

    private final BoardView boardView;
    private final BoardModel boardModel;

    BoardCtrl() {
        boardView = new BoardView(this);
        boardModel = new BoardModel();
        boardModel.addFieldChangedEventListener(boardView);
    }
    public void handleClick(Circle[] circles, Point point) {
        for (int i = 0; i < 36; i++) {
           if (circles[i].contains(point)) {
               boardModel.placeBall(i);
           }
        }
    }

    void attachToFrame(JFrame rootFrame) {
        rootFrame.add(boardView.getPanel());
    }
}
