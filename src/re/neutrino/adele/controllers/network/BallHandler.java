package re.neutrino.adele.controllers.network;

import re.neutrino.adele.models.network.BallMove;

/**
 * Handler that provides asynchronous handling ball placing
 */
public abstract class BallHandler implements ReadHandler
{
    /**
     * Must define what to do with the ball move
     * @param move ball placed
     */
    public abstract void handleBall(BallMove move);

    /**
     * Unwrap message from array of bytes
     * @param msg ball placed
     */
    @Override
    public void handleSuccess(byte[] msg)
    {
        handleBall(new BallMove(msg[1], msg[2]));
    }
}
