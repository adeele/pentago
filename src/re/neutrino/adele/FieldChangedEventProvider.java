package re.neutrino.adele;

/**
 * Interface for fieldChanged event provider
 */
public interface FieldChangedEventProvider {
    void notifyFieldChanged(FieldChangedEvent e);
    void addFieldChangedEventListener(FieldChangedEventListener listener);
    void removeFieldChangedEventListener(FieldChangedEventListener listener);
}
