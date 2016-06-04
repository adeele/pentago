package re.neutrino.adele;

/**
 * Interface for fieldChanged event provider
 */
public interface FieldChangedEventProvider {
    /**
     * notifies all listeners about the change
     * @param e event
     */
    void notifyFieldChanged(FieldChangedEvent e);

    /**
     * adds new listener to the list of the listeners
     * @param listener of the event
     */
    void addFieldChangedEventListener(FieldChangedEventListener listener);
}
