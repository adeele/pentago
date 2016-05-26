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
        if(balls[x][y] == Ball.NONE) {
            if(turn == Turn.WHITE) {
                balls[x][y] = Ball.WHITE;
                turn = Turn.BLACK;
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.WHITE));
            }
            else {
                balls[x][y] = Ball.BLACK;
                turn = Turn.WHITE;
                notifyFieldChanged(new FieldChangedEvent(x, y, Ball.BLACK));
            }
            //checkWin();
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

    void checkWin() {
        for(int i = 0; i < 6; i++) {
            int counter = 0;
            for(int j = 0; j < 5; j++) {
                if (balls[i][j] != Ball.NONE && balls[i][j] == balls[i][j+1])
                    counter++;
            }
            if(counter == 5)
                System.out.println("WIN");
        }
        for(int i = 0; i < 5; i++){
            int counter = 0;
            for(int j = 0; j < 6; j++) {
                if (balls[j][i] != Ball.NONE && balls[j][i] == balls[j][i+1])
                    counter++;
            }
            if(counter == 5)
                System.out.println("WIN");
        }
    }
}
