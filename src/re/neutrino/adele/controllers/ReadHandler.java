package re.neutrino.adele.controllers;

/**
 * Created by adele on 6/12/16.
 */
interface ReadHandler {
    void handleSuccess(byte[] msg);
    void handleError(Exception e);
}
