package re.neutrino.adele.models;

/**
 * Ball ball enum
 */
// TODO Rename to Player
public enum Ball {
    NONE,
    WHITE,
    BLACK;


    public Ball getOpposite() {
        switch (this) {
            case WHITE:
                return BLACK;
            case BLACK:
                return WHITE;
            default:
                return NONE;
        }
    }
}
