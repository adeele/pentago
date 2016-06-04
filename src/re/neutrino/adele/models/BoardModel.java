package re.neutrino.adele.models;

import re.neutrino.adele.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Phisical model of board
 */
public class BoardModel implements FieldChangedEventProvider {
    private final ArrayList<FieldChangedEventListener> listeners = new ArrayList<>();
    private Turn turn;

    private final Ball[][] balls = new Ball[6][6];
    public boolean placeBall(int i) {
        int x = i % 6;
        int y = i / 6;
        return placeBall(x, y);
    }

    public boolean placeBall(int x, int y) {
        if(balls[x][y] == Ball.NONE) {
            if(turn == Turn.WHITE_PLACE_BALL) {
                balls[x][y] = Ball.WHITE;
                turn = nextTurn(turn);
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.WHITE));
                if(checkWin(x, y, Ball.WHITE))
                    return true;
            }
            if(turn == Turn.BLACK_PLACE_BALL) {
                balls[x][y] = Ball.BLACK;
                turn = nextTurn(turn);
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.BLACK));
                if(checkWin(x, y, Ball.BLACK))
                    return true;
            }
        }
        return false;
    }

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

    @Override
    public void notifyFieldChanged(FieldChangedEvent e) {
        for (FieldChangedEventListener listener : listeners) {
            listener.onFieldChanged(e);
        }
    }

    @Override
    public void addFieldChangedEventListener(FieldChangedEventListener listener) {
        listeners.add(listener);
    }

    public boolean rotate(Point center, int way) {
        if(turn == Turn.WHITE_ROTATE_BOARD || turn == Turn.BLACK_ROTATE_BOARD) {
            swapCorners(center.x, center.y, way);
            swapEdges(center.x, center.y, way);
            if(checkBoardWin())
                return true;
            turn = nextTurn(turn);
        }
        return false;
    }

    public boolean checkBoardWin() {
        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(balls[i][j] == Ball.NONE)
                    continue;
                if (checkWin(i, j, balls[i][j]))
                    return true;
            }
        }
        return false;
    }

    private void swapEdges(int x, int y, int way) {
        Ball tmp = balls[x - 1][y];
        swapAndNotify(x - 1, y, balls[x][y - way]);
        swapAndNotify(x, y - way, balls[x + 1][y]);
        swapAndNotify(x + 1, y, balls[x][y + way]);
        swapAndNotify(x, y + way, tmp);
    }

    private void swapCorners(int x, int y, int way) {
        Ball tmp = balls[x - 1][y - 1];
        swapAndNotify(x - 1, y - 1, balls[x + way][y - way]);
        swapAndNotify(x + way, y - way, balls[x + 1][y + 1]);
        swapAndNotify(x + 1, y + 1, balls[x - way][y + way]);
        swapAndNotify(x - way, y + way, tmp);
    }

    private void swapAndNotify(int x, int y, Ball ball) {
        balls[x][y] = ball;
        notifyFieldChanged(new FieldChangedEvent(x, y, balls[x][y]));
    }

    public BoardModel() {
        turn = Turn.WHITE_PLACE_BALL;
        for (int i = 0; i < 6; i++) {
            balls[i] = new Ball[6];
            for(int j = 0; j < 6; j++)
                balls[i][j] = Ball.NONE;
        }
    }

    public boolean checkWin(int x, int y, Ball color) {
        return (checkHorizontal(x, y, color) || checkVertical(x, y, color) || checkOblique(x, y, color));
    }

    private boolean checkOblique(int x, int y, Ball color) {
        return countBalls(x, y, 1, 1, color) + countBalls(x, y, -1, -1, color) > 3 ||
                countBalls(x, y, 1, -1, color) + countBalls(x, y, -1, 1, color) > 3;
    }

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

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && y < 6 && x < 6;
    }

    private boolean checkVertical(int x, int y, Ball color) {
        return countBalls(x, y, 1, 0, color) + countBalls(x, y, -1, 0, color) > 3;
    }

    private boolean checkHorizontal(int x, int y, Ball color) {
        return countBalls(x, y, 0, 1, color) + countBalls(x, y, 0, -1, color) > 3;
    }

    public Turn getTurn() {
        return turn;
    }

    public void endOfGame() {
        turn = Turn.END_OF_GAME;
    }
}
