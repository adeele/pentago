package re.neutrino.adele.models;

/**
 * Ball ball enum
 */
public enum Ball
{
    NONE,
    WHITE,
    BLACK;

    /**
     * Returns opposite color of the ball
     * @return opposite color
     */
    public Ball getOpposite()
    {
        switch (this)
        {
            case WHITE:
                return BLACK;
            case BLACK:
                return WHITE;
            default:
                return NONE;
        }
    }
}
