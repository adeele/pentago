package re.neutrino.adele.views;

import javax.swing.*;
import java.awt.*;

/**
 * Class GameView
 * creates game view layout
 */
public class GameView {
    private JFrame frame = new JFrame(Constant.PENTAGO);
    private PanelDraw panel = new PanelDraw();
    private JLabel labelTurn = new JLabel("TURN: WHITE");

    public GameView() {
        frame.setBounds(500, 150, 800, 800);
        frame.add(panel);
        frame.setVisible(true);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelTurn);

        labelTurn.setBackground(Constant.BG_DARK_COLOR);
        labelTurn.setOpaque(true);
        labelTurn.setForeground(Constant.FG_COLOR);
        labelTurn.setFont(Constant.DEFAULT_FONT_BIG);
        labelTurn.setBorder(BorderFactory.createEmptyBorder(40, 228, 40, 228));
        labelTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
