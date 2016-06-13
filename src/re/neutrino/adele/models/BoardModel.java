package re.neutrino.adele.models;

import re.neutrino.adele.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Physical model of board
 */
public class BoardModel implements FieldChangedEventProvider
{
    private final ArrayList<FieldChangedEventListener> listeners = new ArrayList<>();
    private final Ball[][] balls = new Ball[6][6];
    private boolean finished = false;

    /**
     * Creates balls array
     */
    public BoardModel()
    {
        for (int i = 0; i < 6; i++)
        {
            balls[i] = new Ball[6];
            for (int j = 0; j < 6; j++)
                balls[i][j] = Ball.NONE;
        }
    }

    /**
     * Places ball on specific place
     * @param i place to set the ball
     * @param color of the ball to set
     */
    public void placeBall(int i, Ball color)
    {
        int x = i % 6;
        int y = i / 6;
        placeBall(x, y, color);
    }

    /**
     * Inserts ball to the board
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param color of the ball
     *              (none if no win)
     */
    public void placeBall(int x, int y, Ball color)
    {
        balls[x][y] = color;
        notifyFieldChanged(new FieldChangedEvent(x, y, color));
        if (checkWin(x, y, color))
            finished = true;
    }

    /**
     * Sends notification about field change
     * @param e event
     */
    @Override
    public void notifyFieldChanged(FieldChangedEvent e)
    {
        for (FieldChangedEventListener listener : listeners)
        {
            listener.onFieldChanged(e);
        }
    }

    /**
     * Adds listener to notify about field change
     * @param listener of the event
     */
    @Override
    public void addFieldChangedEventListener(FieldChangedEventListener listener)
    {
        listeners.add(listener);
    }

    /**
     * Rotates the board and check if someone win
     * @param square coordinates of the center of the rotated square
     * @param way    of the rotation
     */
    public void rotate(int square, int way)
    {
        Point center = getCenterPoint(square);
        swapCorners(center.x, center.y, way);
        swapEdges(center.x, center.y, way);
        checkBoardWin();
    }

    /**
     * Returns center of the rotated square
     * @param square rotated
     * @return center point
     */
    private Point getCenterPoint(int square)
    {
        switch (square)
        {
            case 1:
                return new Point(1, 1);
            case 2:
                return new Point(1, 4);
            case 3:
                return new Point(4, 4);
            default:
                return new Point(4, 1);
        }
    }

    /**
     * Checks all board if someone win
     * @return ball of the ball wins
     */
    private boolean checkBoardWin()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if (balls[i][j] == Ball.NONE)
                    continue;
                if (checkWin(i, j, balls[i][j]))
                {
                    return finished = true;
                }
            }
        }
        return false;
    }

    /**
     * Swaps edge fields of the square rotated
     * @param x   x-coordinate
     * @param y   y-coordinate
     * @param way to rotate
     */
    private void swapEdges(int x, int y, int way)
    {
        Ball tmp = balls[x - 1][y];
        swapAndNotify(x - 1, y, balls[x][y - way]);
        swapAndNotify(x, y - way, balls[x + 1][y]);
        swapAndNotify(x + 1, y, balls[x][y + way]);
        swapAndNotify(x, y + way, tmp);
    }

    /**
     * Swaps corner fields of the square rotated
     * @param x   x-coordinate
     * @param y   y-coordinate
     * @param way to rotate
     */
    private void swapCorners(int x, int y, int way)
    {
        Ball tmp = balls[x - 1][y - 1];
        swapAndNotify(x - 1, y - 1, balls[x + way][y - way]);
        swapAndNotify(x + way, y - way, balls[x + 1][y + 1]);
        swapAndNotify(x + 1, y + 1, balls[x - way][y + way]);
        swapAndNotify(x - way, y + way, tmp);
    }

    /**
     * Notifies about field changed after swapping fields
     * @param x    x-coordinate
     * @param y    y-coordinate
     * @param ball ball
     */
    private void swapAndNotify(int x, int y, Ball ball)
    {
        balls[x][y] = ball;
        notifyFieldChanged(new FieldChangedEvent(x, y, balls[x][y]));
    }

    /**
     * Checks win on the specific place
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param color of the ball
     * @return if wins
     */
    private boolean checkWin(int x, int y, Ball color)
    {
        return (checkHorizontal(x, y, color) || checkVertical(x, y, color) || checkOblique(x, y, color));
    }

    /**
     * Checks oblique win on the specific place
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param color of the ball
     * @return if wins
     */
    private boolean checkOblique(int x, int y, Ball color)
    {
        return countBalls(x, y, 1, 1, color) + countBalls(x, y, -1, -1, color) > 3 ||
                countBalls(x, y, 1, -1, color) + countBalls(x, y, -1, 1, color) > 3;
    }

    /**
     * Count balls in line
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param dx    which way from x
     * @param dy    which way from y
     * @param color of the ball
     * @return number of balls in line
     */
    private int countBalls(int x, int y, int dx, int dy, Ball color)
    {
        x += dx;
        y += dy;
        int counter = 0;
        while (inBounds(x, y))
        {
            if (balls[x][y] != color)
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
    private boolean inBounds(int x, int y)
    {
        return x >= 0 && y >= 0 && y < 6 && x < 6;
    }

    /**
     * Checks vertical win on the specific place
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param color of the ball
     * @return if wins
     */
    private boolean checkVertical(int x, int y, Ball color)
    {
        return countBalls(x, y, 1, 0, color) + countBalls(x, y, -1, 0, color) > 3;
    }

    /**
     * Checks horizontal win on the specific place
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param color of the ball
     * @return if wins
     */
    private boolean checkHorizontal(int x, int y, Ball color)
    {
        return countBalls(x, y, 0, 1, color) + countBalls(x, y, 0, -1, color) > 3;
    }

    /**
     * Checks out if the game is finished
     * @return is game finished
     */
    public boolean isFinished()
    {
        return finished;
    }

    /**
     * Checks out if the place to set the ball is free
     * @param i place to set the ball
     * @return is the place free
     */
    public boolean canPlaceBall(int i)
    {
        int x = i % 6;
        int y = i / 6;
        return balls[x][y] == Ball.NONE;
    }
}
