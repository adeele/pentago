package re.neutrino.adele;

/**
 * Interface for the provider of field changed event
 */
public interface FieldChangedEventProvider
{
    /**
     * Notifies all listeners about the field change
     * @param e event
     */
    void notifyFieldChanged(FieldChangedEvent e);

    /**
     * Adds new listener to the list of the listeners
     * @param listener of the event
     */
    void addFieldChangedEventListener(FieldChangedEventListener listener);
}
