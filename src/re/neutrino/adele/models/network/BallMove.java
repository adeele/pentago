package re.neutrino.adele.models.network;

import re.neutrino.adele.GameConstant;

/**
 * Ball placing move
 */
public class BallMove implements ByteRepresentable
{
    private int x;
    private int y;

    /**
     * Returns linear access to the ball
     * @return i-coordinate
     */
    public int getI()
    {
        return x + y * 6;
    }

    /**
     * Creates ball move on the specific place
     * @param x-coordinate
     * @param y-coordinate
     */
    public BallMove(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Converts coordinates to the byte array
     * @return message (array of bytes)
     */
    @Override
    public byte[] toBytes()
    {
        byte[] msg = new byte[3];
        msg[0] = GameConstant.MESSAGE_BALL_MOVE;
        msg[1] = (byte) x;
        msg[2] = (byte) y;
        return msg;
    }
}
