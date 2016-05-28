package re.neutrino.adele.models;

import re.neutrino.adele.Ball;
import re.neutrino.adele.FieldChangedEvent;
import re.neutrino.adele.FieldChangedEventListener;
import re.neutrino.adele.FieldChangedEventProvider;

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
            if(turn == Turn.WHITE) {
                balls[x][y] = Ball.WHITE;
                turn = Turn.BLACK;
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.WHITE));
                if(checkWin(x, y, Ball.WHITE))
                    System.out.println("WHITE WIN!");
            }
            else {
                balls[x][y] = Ball.BLACK;
                turn = Turn.WHITE;
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.BLACK));
                if(checkWin(x, y, Ball.BLACK))
                    System.out.println("BLACK WIN!");
            }
            return true;
        }
        return false;
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

    @Override
    public void removeFieldChangedEventListener(FieldChangedEventListener listener) {
        listeners.remove(listener);
    }

    private enum Turn {
        WHITE,
        BLACK
    }

    public BoardModel() {
        turn = Turn.WHITE;
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
}
