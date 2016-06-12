package re.neutrino.adele.controllers;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.states.*;
import re.neutrino.adele.models.BoardModel;
import re.neutrino.adele.views.BoardView;
import re.neutrino.adele.views.Circle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Controller for BoardView
 */
public class BoardCtrl {

    private final BoardView boardView;
    private final BoardModel boardModel;
    private App app;
    private BaseState roundState;
    private NetworkCtrl networkCtrl;

    BoardCtrl(App app) {
        boardView = new BoardView(this);
        boardModel = new BoardModel();
        boardModel.addFieldChangedEventListener(boardView);
        this.app = app;
        roundState = new BallTurn(this, Ball.WHITE);
    }

    BoardCtrl(App app, NetworkCtrl networkCtrl, boolean server) {
        boardView = new BoardView(this);
        boardModel = new BoardModel();
        boardModel.addFieldChangedEventListener(boardView);
        this.app = app;
        this.networkCtrl = networkCtrl;
        if(server) {
            try {
                networkCtrl.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
            roundState = new BallTurn(this, Ball.WHITE);
        }
        else {
            try {
                networkCtrl.connect("localhost");
            } catch (IOException e) {
                e.printStackTrace();
            }
            roundState = new NetworkBallTurn(this, Ball.WHITE);
        }
    }

    public boolean isOnline() {
        return networkCtrl != null;
    }

    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        synchronized (this) {
            roundState.handleClick(circles, arrows, point);
        }
    }

    void attachToFrame(JFrame rootFrame) {
        rootFrame.add(boardView.getPanel());
    }

    // TODO sth
    public int witchSquare(int i) {
        return i/2 + 1;
    }

    // TODO sth
    public int witchWay(int i) {
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

    private void submitMove(ByteRepresentable move) {
        try {
            networkCtrl.submitMove(move);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitBall(BallMove ballMove) {
        submitMove(ballMove);
    }

    public void submitRotate(RotateMove rotateMove) {
        submitMove(rotateMove);
    }

    public void setNextTurn(BaseState turn) {
        synchronized (this) {
            this.roundState = turn;
        }
    }

    public void readAsync(ReadHandler handler) {
        networkCtrl.readAsync(handler);
    }
}
