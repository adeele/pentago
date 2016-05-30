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
    public void handleBoardClick(Circle[] circles, Point point) {
        for (int i = 0; i < 36; i++) {
           if (circles[i].contains(point)) {
               boardModel.placeBall(i);
           }
        }
    }

    void attachToFrame(JFrame rootFrame) {
        rootFrame.add(boardView.getPanel());
    }

    public void handleArrowClick(Rectangle[] arrows, Point point) {
        for (int i = 0; i < 8; i++) {
            if (arrows[i].contains(point)) {
                boardModel.rotate(witchSquare(i), witchWay(i));
            }
        }
    }

    private Point witchSquare(int i) {
        if(i == 0 || i == 1)
            return new Point(1, 1);
        if(i == 2 || i == 3)
            return new Point(1, 4);
        if(i == 4 || i == 5)
            return new Point(4, 4);
        return new Point(4, 1);
    }

    private int witchWay(int i) {
        if(i % 2 == 0)
            return -1;
        return 1;
    }
}
