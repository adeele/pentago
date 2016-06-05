package re.neutrino.adele;

import java.awt.*;

/**
 * Keeps game constants such as colors and strings
 */
public class GameConstant {
    public static final Color FG_COLOR = new Color(16, 32, 38);
    public static final Color BG_COLOR = new Color(240, 243, 217);
    public static final Color BG_LIGHT_COLOR = new Color(218, 199, 158);
    public static final Color BG_DARK_COLOR = new Color(166, 135, 102);
    public static final Color BOREDER_COLOR = new Color(88, 54, 46);
    public static final String PENTAGO = "Pentago";
    public static final String PLAY = "Play";
    public static final String PLAY_ONLINE = "Play Online";
    public static final String QUIT = "Quit";
    public static final Font DEFAULT_FONT_BIG = new Font("Default", Font.PLAIN, 50);
    public static final Font DEFAULT_FONT_SMALL = new Font("Default", Font.PLAIN, 32);
    public static final String TURN_WHITE = "TURN: WHITE";
    public static final String TURN_BLACK = "TURN: BLACK";
    public static final String WINNER_WHITE = "WIN: WHITE!";
    public static final String WINNER_BLACK = "WIN: BLACK!";
    public static final int PORT = 2778;
    public static final byte MESSAGE_MOVE = 1;
    public static final byte MESSAGE_LOSS = 2;
}
