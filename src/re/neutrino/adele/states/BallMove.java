package re.neutrino.adele.states;

import re.neutrino.adele.GameConstant;

/**
 * Ball placing move
 * Created by adele on 6/8/16.
 */
public class BallMove implements ByteRepresentable {
    private int x;
    private int y;

    public int getI() {
        return x * 6 + y;
    }

    public BallMove(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public byte[] toBytes() {
        byte[] msg = new byte[3];
        msg[0] = GameConstant.MESSAGE_BALL_MOVE;
        msg[1] = (byte)x;
        msg[2] = (byte)y;
        return msg;
    }
}
