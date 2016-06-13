package re.neutrino.adele.controllers;

import re.neutrino.adele.controllers.network.NetworkCtrl;
import re.neutrino.adele.controllers.network.ReadHandler;
import re.neutrino.adele.models.Ball;
import re.neutrino.adele.controllers.states.*;
import re.neutrino.adele.models.BoardModel;
import re.neutrino.adele.models.network.BallMove;
import re.neutrino.adele.models.network.BaseState;
import re.neutrino.adele.models.network.ByteRepresentable;
import re.neutrino.adele.models.network.RotateMove;
import re.neutrino.adele.views.BoardView;
import re.neutrino.adele.views.Circle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Controller for BoardView
 */
public class BoardCtrl
{

    private final BoardView boardView;
    private final BoardModel boardModel;
    private App app;
    private BaseState roundState;
    private NetworkCtrl networkCtrl;

    /**
     * Creates local game
     * @param app link with the application
     */
    BoardCtrl(App app)
    {
        boardView = new BoardView(this);
        boardModel = new BoardModel();
        boardModel.addFieldChangedEventListener(boardView);
        this.app = app;
        roundState = new BallTurn(this, Ball.WHITE);
    }

    /**
     * Creates network game
     *
     * @param app         lik to the application
     * @param networkCtrl that manages network connection
     * @param server      is server or client
     * @param address     on witch connection was created
     */
    BoardCtrl(App app, NetworkCtrl networkCtrl, boolean server, String address)
    {
        boardView = new BoardView(this);
        boardModel = new BoardModel();
        boardModel.addFieldChangedEventListener(boardView);
        this.app = app;
        this.networkCtrl = networkCtrl;
        if (server)
        {
            try
            {
                networkCtrl.listen();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            roundState = new BallTurn(this, Ball.WHITE);
        } else
        {
            try
            {
                networkCtrl.connect(address);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            roundState = new NetworkBallTurn(this, Ball.WHITE);
        }
    }

    /**
     * Informs is this a network game
     * @return is game online
     */
    public boolean isOnline()
    {
        return networkCtrl != null;
    }

    /**
     * Sends information to the round about the click event
     * @param circles array of ball places coordinates
     * @param arrows  array of arrows coordinates
     * @param point   clicked
     */
    public void handleClick(Circle[] circles, Rectangle[] arrows, Point point)
    {
        synchronized (this)
        {
            roundState.handleClick(circles, arrows, point);
        }
    }

    /**
     * Attaches panel to the application frame
     * @param rootFrame application main frame
     */
    void attachToFrame(JFrame rootFrame)
    {
        rootFrame.add(boardView.getPanel());
    }

    /**
     * Wraps arrow clicked into square number
     * @param i which arrow clicked
     * @return number of square to rotate
     */
    public int witchSquare(int i)
    {
        return i / 2 + 1;
    }

    /**
     * Wraps arrow clicked into specific direction to rotate
     * @param i which arrow clicked
     * @return way to rotate
     */
    public int witchWay(int i)
    {
        if (i % 2 == 0)
            return -1;
        return 1;
    }

    /**
     * Exits game application
     */
    public void quit()
    {
        app.exitGame();
    }

    /**
     * Opens game application menu
     */
    public void openMenu()
    {
        app.exitGame();
        app = new App();
    }

    /**
     * Places ball on proper place and checks the win
     * @param i place to set the ball
     * @param color of the ball
     * @return is game finished
     */
    public boolean placeBallAndCheck(int i, Ball color)
    {
        boardModel.placeBall(i, color);
        return boardModel.isFinished();
    }

    /**
     * Sets arrows visibility
     * @param arrowsVisible is visible
     */
    public void setArrowsVisible(boolean arrowsVisible)
    {
        boardView.setArrowsVisible(arrowsVisible);
    }

    /**
     * Rotates proper square and checks the win
     * @param i square rotated
     * @return is game finished
     */
    public boolean rotateAndCheck(int i)
    {
        boardModel.rotate(witchSquare(i), witchWay(i));
        return boardModel.isFinished();
    }

    /**
     * Stets new label
     * @param label to set
     */
    public void setLabel(String label)
    {
        boardView.setLabel(label);
    }

    /**
     * Checks if can place ball
     * @param i place to set the ball
     * @return can place ball
     */
    public boolean canPlaceBall(int i)
    {
        return boardModel.canPlaceBall(i);
    }

    /**
     * Sends the move to the network controller
     * @param move to send
     */
    private void submitMove(ByteRepresentable move)
    {
        try
        {
            networkCtrl.submitMove(move);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Submits ball move
     */
    public void submitBall(BallMove ballMove)
    {
        submitMove(ballMove);
    }

    /**
     * Submits rotate move
     * @param rotateMove to submit
     */
    public void submitRotate(RotateMove rotateMove)
    {
        submitMove(rotateMove);
    }

    /**
     * Sets next turn of the game
     * @param turn to submit
     */
    public void setNextTurn(BaseState turn)
    {
        synchronized (this)
        {
            this.roundState = turn;
        }
    }

    /**
     * Reads moves asynchronously
     * @param handler which will handle read
     */
    public void readAsync(ReadHandler handler)
    {
        networkCtrl.readAsync(handler);
    }

    /**
     * Informs about the ond of the game
     */
    public void endOfGame()
    {
        boardView.endOfGame();
    }
}
