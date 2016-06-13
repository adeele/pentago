package re.neutrino.adele.controllers;

/**
 * Handler of the reading
 */
interface ReadHandler
{
    /**
     * Has to handle success
     */
    void handleSuccess(byte[] msg);

    /**
     * Has to handle error
     * @param e exception thrown
     */
    void handleError(Exception e);
}
