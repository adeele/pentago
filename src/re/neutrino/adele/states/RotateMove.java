package re.neutrino.adele.states;

import re.neutrino.adele.GameConstant;

import java.awt.*;

/**
 * Rotation board move
 */
public class RotateMove implements ByteRepresentable
{
    private final byte way;
    private final byte square;

    /**
     * Creates move that rotates square
     * @param square rotated
     * @param way direction to rotate
     */
    public RotateMove(byte square, byte way)
    {
        this.square = square;
        this.way = way;
    }

    /**
     * Convert rotation to bytes
     * @return message - array of bytes
     */
    @Override
    public byte[] toBytes()
    {
        byte[] msg = new byte[3];
        msg[0] = GameConstant.MESSAGE_ROTATE_MOVE;
        msg[1] = square;
        msg[2] = way;
        return msg;
    }

    /**
     * Gets the linear coordinate to the rotate move
     * @return which array was clicked
     */
    public int getI()
    {
        int x = way > 0 ? 1 : 0;
        return (square - 1) * 2 + x;
    }
}
