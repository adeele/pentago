package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;
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
    private App app;

    BoardCtrl(App app) {
        boardView = new BoardView(this);
        boardModel = new BoardModel();
        boardModel.addFieldChangedEventListener(boardView);
        this.app = app;
    }

    public void handleBoardClick(Circle[] circles, Point point) {
        for (int i = 0; i < 36; i++) {
           if (circles[i].contains(point)) {
               if(boardModel.placeBall(i)) {
                   endOfGame();
                   break;
               }
               boardView.setArrowsVisible(true);
           }
        }
    }

    void attachToFrame(JFrame rootFrame) {
        rootFrame.add(boardView.getPanel());
    }

    public void handleArrowClick(Rectangle[] arrows, Point point) {
        for (int i = 0; i < 8; i++) {
            if (arrows[i].contains(point)) {
                if(boardModel.rotate(witchSquare(i), witchWay(i))) {
                    endOfGame();
                    break;
                }
                boardView.setArrowsVisible(false);
                boardView.setLabel(getLabel());
            }
        }
    }

    private void endOfGame() {
        boardView.setLabelWin(checkWinner());
        boardModel.endOfGame();
        boardView.endOfGame();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO: w8 some time or press any key/mouse event
        boardView.displayButtonMenu();
        boardView.displayButtonQuit();
    }

    private String checkWinner() {
        switch (boardModel.getTurn()) {
            case BLACK_PLACE_BALL:
                return GameConstant.WINNER_BLACK;
            case BLACK_ROTATE_BOARD:
                return GameConstant.WINNER_BLACK;
            default:
                return GameConstant.WINNER_WHITE;
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

    private String getLabel() {
        switch (boardModel.getTurn()) {
            case BLACK_PLACE_BALL:
                return GameConstant.TURN_BLACK;
            default:
                return GameConstant.TURN_WHITE;
        }
    }

    public void quit() {
        app.exitGame();
    }

    public void openMenu() {
        app.exitGame();
        app = new App();
    }
}
