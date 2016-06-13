package re.neutrino.adele.controllers;

import re.neutrino.adele.states.RotateMove;

/**
 * Handler of the rotate
 */
public abstract class RotateHandler implements ReadHandler
{
    /**
     * Has to handle rotate
     * @param move to handle
     */
    public abstract void handleRotate(RotateMove move);

    /**
     * Unwraps rotate move message and call function which rotates
     * @param msg rotate move params
     */
    @Override
    public void handleSuccess(byte[] msg)
    {
        handleRotate(new RotateMove(msg[1], msg[2]));
    }
}
