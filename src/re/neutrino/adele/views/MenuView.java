package re.neutrino.adele.views;

import re.neutrino.adele.controllers.MenuCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class MenuView:
 * creates menu layout
 */
public class MenuView {
    private JFrame frame =  new JFrame(Constant.PENTAGO);
    private JPanel panel = new JPanel();
    private JButton buttonPlay = new JButton(Constant.PLAY);
    private JButton buttonPlayOnline = new JButton(Constant.PLAY_ONLINE);
    private JButton buttonQuit = new JButton(Constant.QUIT);
    private JLabel labelPentago = new JLabel(Constant.PENTAGO);
    private MenuCtrl menuCtrl;

    public MenuView(MenuCtrl menuCtrl) {
        frame.setBounds(500, 150, 800, 800);
        frame.add(panel);
        frame.setVisible(true);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Constant.BG_COLOR);
        panel.add(labelPentago);
        panel.add(Box.createRigidArea(new Dimension(80, 80)));
        panel.add(buttonPlay);
        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(buttonPlayOnline);
        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(buttonQuit);

        labelPentago.setBackground(Constant.BG_DARK_COLOR);
        labelPentago.setOpaque(true);
        labelPentago.setForeground(Constant.FG_COLOR);
        labelPentago.setFont(Constant.DEFAULT_FONT_BIG);
        labelPentago.setBorder(BorderFactory.createEmptyBorder(50, 293, 50, 293));
        labelPentago.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPlay.setBackground(Constant.BG_LIGHT_COLOR);
        buttonPlay.setBorder(BorderFactory.createEmptyBorder(30, 160, 30, 160));
        buttonPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPlay.setForeground(Constant.FG_COLOR);
        buttonPlay.setFont(Constant.DEFAULT_FONT_SMALL);
        buttonPlay.addActionListener(e -> menuCtrl.buttonPlayCtrl());

        buttonPlayOnline.setBackground(Constant.BG_LIGHT_COLOR);
        buttonPlayOnline.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        buttonPlayOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPlayOnline.setForeground(Constant.FG_COLOR);
        buttonPlayOnline.setFont(Constant.DEFAULT_FONT_SMALL);

        buttonQuit.setBackground(Constant.BG_LIGHT_COLOR);
        buttonQuit.setBorder(BorderFactory.createEmptyBorder(30, 160, 30, 160));
        buttonQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonQuit.setForeground(Constant.FG_COLOR);
        buttonQuit.setFont(Constant.DEFAULT_FONT_SMALL);
        buttonQuit.addActionListener(e -> disposeFrame());
    }

    private void disposeFrame() {
        frame.dispose();
    }
}