package re.neutrino.adele.controllers;

import re.neutrino.adele.states.BallMove;

/**
 * Created by adele on 6/12/16.
 */
public abstract class BallHandler implements ReadHandler {
    public abstract void handleBall(BallMove move);

    @Override
    public void handleSuccess(byte[] msg) {
        handleBall(new BallMove(msg[1], msg[2]));
    }
}
