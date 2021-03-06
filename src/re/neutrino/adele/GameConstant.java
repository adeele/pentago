package re.neutrino.adele;

import java.awt.*;

/**
 * Keeps game constants such as colors, strings and fonts
 */
public class GameConstant
{
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
    public static final int PORT = 2778;
    public static final int TIMEOUT = 100;
    public static final String CREATE_GAME = "Create Game";
    public static final String JOIN_GAME = "Join Game";
    public static final String WIN_FORMAT = "WIN: %s";
    public static final String TURN_FORMAT = "TURN: %s";
    public static final byte MESSAGE_BALL_MOVE = 1;
    public static final byte MESSAGE_ROTATE_MOVE = 2;
    public static final boolean SERVER = true;
    public static final boolean CLIENT = false;
    public static final String EnterIP = "Enter server IP";
    public static final String ADDRESS = "127.0.0.1";
    public static final String ENTER_ADDRESS = "Enter server IP address";
}
