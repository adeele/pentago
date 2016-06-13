package re.neutrino.adele.controllers.network;

/**
 * Handler of the reading
 */
public interface ReadHandler
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
