package re.neutrino.adele.views;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.controllers.MenuCtrl;

import javax.swing.*;
import java.awt.*;

/**
 * Menu screen layout and events
 */
public class MenuView {
    private JPanel panel = new JPanel();
    private JButton buttonPlay = new JButton(GameConstant.PLAY);
    private JButton buttonPlayOnline = new JButton(GameConstant.PLAY_ONLINE);
    private JButton buttonQuit = new JButton(GameConstant.QUIT);
    private JLabel labelPentago = new JLabel(GameConstant.PENTAGO);
    private JButton buttonCreateGame = new JButton(GameConstant.CREATE_GAME);
    private JButton buttonJoinGame = new JButton(GameConstant.JOIN_GAME);
    private JTextField textFieldEnterIP = new JTextField();
    private JLabel labelEnterIP = new JLabel(GameConstant.EnterIP);

    /**
     * Draws all of the application buttons
     * @param menuCtrl reference to the controller
     */
    public MenuView(MenuCtrl menuCtrl) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(GameConstant.BG_COLOR);

        initLabel(labelPentago);

        addAndDisplayButton(buttonPlay, 30, 160);
        buttonPlay.addActionListener(e -> menuCtrl.buttonPlayCtrl());

        addAndDisplayButton(buttonPlayOnline, 30, 100);
        buttonPlayOnline.addActionListener(e -> menuCtrl.buttonPlayOnlineCtrl());

        addAndDisplayButton(buttonQuit, 30, 160);
        buttonQuit.addActionListener(e -> menuCtrl.quit());
    }

    /**
     * Initializes menu label
     * @param label to set
     */
    private void initLabel(JLabel label) {
        panel.add(label);
        label.setBackground(GameConstant.BG_DARK_COLOR);
        label.setOpaque(true);
        label.setForeground(GameConstant.FG_COLOR);
        label.setFont(GameConstant.DEFAULT_FONT_BIG);
        label.setBorder(BorderFactory.createEmptyBorder(50, 293, 50, 293));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Adds and displays button in the appropriate place on panel
     * @param button to display
     * @param x x-coordinate
     * @param y y-coordinate
     */
    private void addAndDisplayButton(JButton button, int x, int y) {
        panel.add(Box.createRigidArea(new Dimension(80, 80)));
        panel.add(button);
        button.setBackground(GameConstant.BG_LIGHT_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(x, y, x, y));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(GameConstant.FG_COLOR);
        button.setFont(GameConstant.DEFAULT_FONT_SMALL);
    }

    /**
     * Provides access to panel
     * @return panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Creates online mode panel view
     * @param menuCtrl reference to the controller
     */
    public void createOnlineModeView(MenuCtrl menuCtrl) {
        panel.removeAll();
        initLabel(labelPentago);

        addAndDisplayButton(buttonCreateGame, 30, 90);
        buttonCreateGame.addActionListener(e -> menuCtrl.createGame());

        addAndDisplayButton(buttonJoinGame, 30, 110);
        buttonJoinGame.addActionListener(e -> menuCtrl.joinGame());


        addAndDisplayButton(buttonQuit, 30, 160);
        buttonQuit.addActionListener(e -> menuCtrl.quit());
    }
}