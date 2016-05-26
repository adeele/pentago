package re.neutrino.adele.models;

/**
 * Phisical model of board
 */
public class BoardModel {
    public boolean placeBall(int i) {
        if(balls[i%6][i/6] == Ball.NONE) {
            balls[i%6][i/6] = Ball.WHITE;
            return true;
        }
        return false;
    }

    private enum Ball {
        NONE,
        WHITE,
        BLACK
    }
    private final Ball[][] balls = new Ball[6][6];
    public BoardModel() {
        for (int i = 0; i < 6; i++) {
            balls[i] = new Ball[6];
            for(int j = 0; j < 6; j++)
                balls[i][j] = Ball.NONE;
        }
    }
}
