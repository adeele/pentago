package re.neutrino.adele;

/**
 * Interface for the listener of field changed event
 */
public interface FieldChangedEventListener
{
    /**
     * Reaction for the event
     * @param e event
     */
    void onFieldChanged(FieldChangedEvent e);
}
