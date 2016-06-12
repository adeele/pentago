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
            setNextTurn(new BallTurn(this, Ball.WHITE));
        }
        else {
            try {
                networkCtrl.connect("localhost");
            } catch (IOException e) {
                e.printStackTrace();
            }
            setNextTurn(new NetworkBallTurn(this, Ball.WHITE));
        }
    }

    public boolean isOnline() {
        return networkCtrl != null;
    }

    public synchronized void handleClick(Circle[] circles, Rectangle[] arrows, Point point) {
        roundState.handleClick(circles, arrows, point);
    }

    void attachToFrame(JFrame rootFrame) {
        rootFrame.add(boardView.getPanel());
    }

    private void endOfGame(Ball ret) {
        boardView.endOfGame(ret);
    }

    // TODO sth
    private int witchSquare(int i) {
        return i/2 + 1;
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
        if(isOnline())
            submitBall(new BallMove(i%6, i/6), color);
        return boardModel.isFinished();
    }

    public void setArrowsVisible(boolean arrowsVisible) {
        boardView.setArrowsVisible(arrowsVisible);
    }

    public boolean rotateAndCheck(int i, Ball ball) {
        boardModel.rotate(witchSquare(i), witchWay(i));
        if(isOnline()) {
            submitRotate(new RotateMove((byte) witchSquare(i), (byte) witchWay(i)), ball);
        }
        return boardModel.isFinished();
    }

    public void setLabel(String label) {
        boardView.setLabel(label);
    }

    public boolean canPlaceBall(int i) {
         return boardModel.canPlaceBall(i);
    }

    private void submitMove(ByteRepresentable move, Ball ball) {
        try {
            networkCtrl.submitMove(move);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void submitBall(BallMove ballMove, Ball ball) {
        submitMove(ballMove, ball);
        roundState = new RotateTurn(this, ball);
    }

    private void submitRotate(RotateMove rotateMove, Ball ball) {
        submitMove(rotateMove, ball);
        roundState = new NetworkBallTurn(this, ball);
    }

    public BallMove readBall() {
        networkCtrl.readAsync(new BallHandler() {
            @Override
            public void handleBall(BallMove move) {

            }

            @Override
            public void handleError(Exception e) {

            }
        });


//        try {
//            byte[] msg = networkCtrl.read();
//            return new BallMove(msg[1], msg[2]);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public synchronized void setNextTurn(BaseState turn) {
        this.roundState = turn;
        turn.init();
    }

    public RotateMove readRotation() {
        try {
            byte[] msg = networkCtrl.read();
            return new RotateMove(msg[1], msg[2]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
