package re.neutrino.adele.controllers;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.states.BallTurn;
import re.neutrino.adele.GameConstant;
import re.neutrino.adele.states.BaseState;
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
    private BaseState roundState;

    BoardCtrl(App app) {
        boardView = new BoardView(this);
        boardModel = new BoardModel();
        boardModel.addFieldChangedEventListener(boardView);
        this.app = app;
        roundState = new BallTurn(this, Ball.WHITE);
    }

    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        roundState = roundState.handleClick(circles, arrows, point);
    }

    void attachToFrame(JFrame rootFrame) {
        rootFrame.add(boardView.getPanel());
    }

    private void endOfGame(Ball ret) {
        boardModel.endOfGame();
        boardView.endOfGame(ret);
    }

    public String checkWinner() {
        switch (boardModel.getTurn()) {
            case BLACK_PLACE_BALL:
                return GameConstant.WINNER_BLACK;
            case BLACK_ROTATE_BOARD:
                return GameConstant.WINNER_BLACK;
            default:
                return GameConstant.WINNER_WHITE;
        }
    }

    // TODO sth
    private Point witchSquare(int i) {
        if(i == 0 || i == 1)
            return new Point(1, 1);
        if(i == 2 || i == 3)
            return new Point(1, 4);
        if(i == 4 || i == 5)
            return new Point(4, 4);
        return new Point(4, 1);
    }

    // TODO sth
    private int witchWay(int i) {
        if(i % 2 == 0)
            return -1;
        return 1;
    }

    public void quit() {
        app.exitGame();
    }

    public void openMenu() {
        app.exitGame();
        app = new App();
    }

    public boolean placeBallAndCheck(int i, Ball color) {
        boardModel.placeBall(i, color);
        return boardModel.isFinished();
    }

    public void setArrowsVisible(boolean arrowsVisible) {
        boardView.setArrowsVisible(arrowsVisible);
    }

    public boolean rotateAndCheck(int i) {
        boardModel.rotate(witchSquare(i), witchWay(i));
        return boardModel.isFinished();
    }

    public void setLabel(String label) {
        boardView.setLabel(label);
    }

    public boolean canPlaceBall(int i) {
         return boardModel.canPlaceBall(i);
    }
}
