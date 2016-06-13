package re.neutrino.adele.states;

/**
 * Ability to translate oneself to bytes
 */
public interface ByteRepresentable
{
    /**
     * Has to can convert itself to bytes
     * @return message - array of bytes
     */
    byte[] toBytes();
}
