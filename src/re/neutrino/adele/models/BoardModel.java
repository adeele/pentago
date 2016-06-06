package re.neutrino.adele.models;

import re.neutrino.adele.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Physical model of board
 */
public class BoardModel implements FieldChangedEventProvider {
    private final ArrayList<FieldChangedEventListener> listeners = new ArrayList<>();
    private Turn turn;
    private final Ball[][] balls = new Ball[6][6];
    public Ball placeBall(int i) {
        int x = i % 6;
        int y = i / 6;
        return placeBall(x, y);
    }

    /**
     * Constructor
     */
    public BoardModel() {
        turn = Turn.WHITE_PLACE_BALL;
        for (int i = 0; i < 6; i++) {
            balls[i] = new Ball[6];
            for(int j = 0; j < 6; j++)
                balls[i][j] = Ball.NONE;
        }
    }

    /**
     * Inserts ball to the board
     * @param x x-coord
     * @param y y-coord
     * @return color of the winner ball
     * (none if no win)
     */
    public Ball placeBall(int x, int y) {
        if(balls[x][y] == Ball.NONE) {
            if(turn == Turn.WHITE_PLACE_BALL) {
                balls[x][y] = Ball.WHITE;
                turn = nextTurn(turn);
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.WHITE));
                if(checkWin(x, y, Ball.WHITE))
                    return Ball.WHITE;
            }
            if(turn == Turn.BLACK_PLACE_BALL) {
                balls[x][y] = Ball.BLACK;
                turn = nextTurn(turn);
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.BLACK));
                if(checkWin(x, y, Ball.BLACK))
                    return Ball.BLACK;
            }
        }
        return Ball.NONE;
    }

    /**
     * Changes turn
     * @param turn active turn
     * @return next turn
     */
    private Turn nextTurn(Turn turn) {
        switch(turn) {
            case WHITE_PLACE_BALL:
                return Turn.WHITE_ROTATE_BOARD;
            case WHITE_ROTATE_BOARD:
                return Turn.BLACK_PLACE_BALL;
            case BLACK_PLACE_BALL:
                return Turn.BLACK_ROTATE_BOARD;
            default:
                return Turn.WHITE_PLACE_BALL;
        }
    }

    /**
     * Sends notification about field change
     * @param e event
     */
    @Override
    public void notifyFieldChanged(FieldChangedEvent e) {
        for (FieldChangedEventListener listener : listeners) {
            listener.onFieldChanged(e);
        }
    }

    /**
     * Adds listener to notify about field change
     * @param listener of the event
     */
    @Override
    public void addFieldChangedEventListener(FieldChangedEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Rotates the board and check if someone win
     * @param center coordinates of the center of the rotated square
     * @param way of the rotation
     * @return color of the ball wins
     * (none if no win)
     */
    public Ball rotate(Point center, int way) {
        if(turn == Turn.WHITE_ROTATE_BOARD || turn == Turn.BLACK_ROTATE_BOARD) {
            swapCorners(center.x, center.y, way);
            swapEdges(center.x, center.y, way);
            Ball ret = checkBoardWin();
            if(ret == Ball.WHITE)
                return Ball.WHITE;
            if(ret == Ball.BLACK)
                return Ball.BLACK;
            turn = nextTurn(turn);
        }
        return Ball.NONE;
    }

    /**
     * Checks all board if someone win
     * @return color of the ball wins
     */
    public Ball checkBoardWin() {
        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(balls[i][j] == Ball.NONE)
                    continue;
                if (checkWin(i, j, balls[i][j]))
                    return balls[i][j];
            }
        }
        return Ball.NONE;
    }

    /**
     * Swaps edge fields of the square rotated
     * @param x x-coordinate
     * @param y y-coordinate
     * @param way to rotate
     */
    private void swapEdges(int x, int y, int way) {
        Ball tmp = balls[x - 1][y];
        swapAndNotify(x - 1, y, balls[x][y - way]);
        swapAndNotify(x, y - way, balls[x + 1][y]);
        swapAndNotify(x + 1, y, balls[x][y + way]);
        swapAndNotify(x, y + way, tmp);
    }

    /**
     * Swaps corner fields of the square rotated
     * @param x x-coordinate
     * @param y y-coordinate
     * @param way to rotate
     */
    private void swapCorners(int x, int y, int way) {
        Ball tmp = balls[x - 1][y - 1];
        swapAndNotify(x - 1, y - 1, balls[x + way][y - way]);
        swapAndNotify(x + way, y - way, balls[x + 1][y + 1]);
        swapAndNotify(x + 1, y + 1, balls[x - way][y + way]);
        swapAndNotify(x - way, y + way, tmp);
    }

    /**
     * Notifies about field changed after swapping fields
     * @param x x-coordinate
     * @param y y-coordinate
     * @param ball color
     */
    private void swapAndNotify(int x, int y, Ball ball) {
        balls[x][y] = ball;
        notifyFieldChanged(new FieldChangedEvent(x, y, balls[x][y]));
    }

    /**
     * Checks win on the specific place
     * @param x x-coordinate
     * @param y y-coordinate
     * @param color of the ball
     * @return if wins
     */
    public boolean checkWin(int x, int y, Ball color) {
        return (checkHorizontal(x, y, color) || checkVertical(x, y, color) || checkOblique(x, y, color));
    }

    /**
     * Checks oblique win on the specific place
     * @param x x-coordinate
     * @param y y-coordinate
     * @param color of the ball
     * @return if wins
     */
    private boolean checkOblique(int x, int y, Ball color) {
        return countBalls(x, y, 1, 1, color) + countBalls(x, y, -1, -1, color) > 3 ||
                countBalls(x, y, 1, -1, color) + countBalls(x, y, -1, 1, color) > 3;
    }

    /**
     * Count balls in line
     * @param x x-coordinate
     * @param y y-coordinate
     * @param dx which way from x
     * @param dy which way from y
     * @param color of the ball
     * @return number of balls in line
     */
    private int countBalls(int x, int y, int dx, int dy, Ball color) {
        x += dx;
        y += dy;
        int counter = 0;
        while(inBounds(x, y)) {
            if(balls[x][y] != color)
                break;
            counter++;
            x += dx;
            y += dy;
        }
        return counter;
    }

    /**
     * Watches out if is still in approptiate range
     * @param x x-coordinate
     * @param y y-coordinate
     * @return if in range
     */
    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && y < 6 && x < 6;
    }

    /**
     * Checks vertical win on the specific place
     * @param x x-coordinate
     * @param y y-coordinate
     * @param color of the ball
     * @return if wins
     */
    private boolean checkVertical(int x, int y, Ball color) {
        return countBalls(x, y, 1, 0, color) + countBalls(x, y, -1, 0, color) > 3;
    }

    /**
     * Checks horizontal win on the specific place
     * @param x x-coordinate
     * @param y y-coordinate
     * @param color of the ball
     * @return if wins
     */
    private boolean checkHorizontal(int x, int y, Ball color) {
        return countBalls(x, y, 0, 1, color) + countBalls(x, y, 0, -1, color) > 3;
    }

    /**
     * Provides access to the turn
     * @return turn
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * Sets turn on end-of-game mode
     */
    public void endOfGame() {
        turn = Turn.END_OF_GAME;
    }
}
