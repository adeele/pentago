package re.neutrino.adele;

/**
 * Interface for fieldChanged event listener
 */
public interface FieldChangedEventListener {
    /**
     * reaction for the event
     * @param e event
     */
    void onFieldChanged(FieldChangedEvent e);
}
