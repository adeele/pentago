package re.neutrino.adele.states;

import re.neutrino.adele.GameConstant;

import java.awt.*;

/**
 * Rotatation board move
 */
public class RotateMove implements ByteRepresentable {
    private final byte way;
    private final byte square;

    public RotateMove(byte square, byte way) {
        this.square = square;
        this.way = way;
    }

    @Override
    public byte[] toBytes() {
        byte[] msg = new byte[3];
        msg[0] = GameConstant.MESSAGE_ROTATE_MOVE;
        msg[1] = square;
        msg[2] = way;
        return msg;
    }

    public int getI() {
        int x = way > 0 ? 1 : 0;
        return (square - 1)*2 + x;
    }
}
