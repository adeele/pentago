package re.neutrino.adele.controllers;

import re.neutrino.adele.states.RotateMove;

/**
 * Created by adele on 6/12/16.
 */
public abstract class RotateHandler implements ReadHandler {
    public abstract void handleRotate(RotateMove move);

    @Override
    public void handleSuccess(byte[] msg) {
        handleRotate(new RotateMove(msg[1], msg[2]));
    }
}
