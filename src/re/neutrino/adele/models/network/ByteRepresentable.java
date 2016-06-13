package re.neutrino.adele.models.network;

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
