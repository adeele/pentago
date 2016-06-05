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

        buttonPlay.setBackground(GameConstant.BG_LIGHT_COLOR);
        buttonPlay.setBorder(BorderFactory.createEmptyBorder(30, 160, 30, 160));
        buttonPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPlay.setForeground(GameConstant.FG_COLOR);
        buttonPlay.setFont(GameConstant.DEFAULT_FONT_SMALL);
        buttonPlay.addActionListener(e -> menuCtrl.buttonPlayCtrl());

        buttonPlayOnline.setBackground(GameConstant.BG_LIGHT_COLOR);
        buttonPlayOnline.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        buttonPlayOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPlayOnline.setForeground(GameConstant.FG_COLOR);
        buttonPlayOnline.setFont(GameConstant.DEFAULT_FONT_SMALL);
        buttonPlayOnline.addActionListener(e -> menuCtrl.buttonPlayOnlineCtrl());

        buttonQuit.setBackground(GameConstant.BG_LIGHT_COLOR);
        buttonQuit.setBorder(BorderFactory.createEmptyBorder(30, 160, 30, 160));
        buttonQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonQuit.setForeground(GameConstant.FG_COLOR);
        buttonQuit.setFont(GameConstant.DEFAULT_FONT_SMALL);
        buttonQuit.addActionListener(e -> menuCtrl.quit());
    }

    public JPanel getPanel() {
        return panel;
    }

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