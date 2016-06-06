package re.neutrino.adele.views;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.controllers.MenuCtrl;
import re.neutrino.adele.controllers.NetworkCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Class MenuView:
 * creates menu layout
 */
public class MenuView {
    private JPanel panel = new JPanel();
    private JButton buttonPlay = new JButton(GameConstant.PLAY);
    private JButton buttonPlayOnline = new JButton(GameConstant.PLAY_ONLINE);
    private JButton buttonQuit = new JButton(GameConstant.QUIT);
    private JLabel labelPentago = new JLabel(GameConstant.PENTAGO);

    /**
     * Constructor draws all application buttons
     * @param menuCtrl reference to the controller
     */
    public MenuView(MenuCtrl menuCtrl) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(GameConstant.BG_COLOR);
        panel.add(labelPentago);
        panel.add(Box.createRigidArea(new Dimension(80, 80)));
        panel.add(buttonPlay);
        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(buttonPlayOnline);
        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(buttonQuit);

        labelPentago.setBackground(GameConstant.BG_DARK_COLOR);
        labelPentago.setOpaque(true);
        labelPentago.setForeground(GameConstant.FG_COLOR);
        labelPentago.setFont(GameConstant.DEFAULT_FONT_BIG);
        labelPentago.setBorder(BorderFactory.createEmptyBorder(50, 293, 50, 293));
        labelPentago.setAlignmentX(Component.CENTER_ALIGNMENT);

        displayButton(buttonPlay, 30, 160);
        buttonPlay.addActionListener(e -> menuCtrl.buttonPlayCtrl());

        displayButton(buttonPlayOnline, 30, 100);
        buttonPlayOnline.addActionListener(e -> menuCtrl.buttonPlayOnlineCtrl());

        displayButton(buttonQuit, 30, 160);
        buttonQuit.addActionListener(e -> menuCtrl.quit());
    }

    /**
     * Displays button in proper place on panel
     * @param button to display
     * @param x x-coord
     * @param y y-coord
     */
    private void displayButton(JButton button, int x, int y) {
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
        buttonPlay.setText(GameConstant.CREATE_GAME);
        buttonPlay.setBorder(BorderFactory.createEmptyBorder(30, 85, 30, 85));
        buttonPlay.removeNotify();
        //buttonPlay.addActionListener(e -> menuCtrl.createGame());
        buttonPlayOnline.setText(GameConstant.JOIN_GAME);
        buttonPlayOnline.setBorder(BorderFactory.createEmptyBorder(30, 110, 30, 110));
        buttonPlayOnline.removeNotify();
        //buttonPlayOnline.addActionListener(e -> menuCtrl.joinGame());

        panel.repaint();
    }
}