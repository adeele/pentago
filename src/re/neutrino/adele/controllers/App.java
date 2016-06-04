package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.views.BoardView;

import javax.swing.*;

/**
 * Starts app
 */
public class App {
    private final JFrame rootFrame = new JFrame(GameConstant.PENTAGO);
    private final MenuCtrl menuCtrl;

    /**
     * App constructor
     */
    public App() {
        menuCtrl = new MenuCtrl(this);
        menuCtrl.attachToFrame(rootFrame);
        rootFrame.setBounds(500, 150, 800, 800);
        rootFrame.setVisible(true);
    }

    /**
     * Starts new game
     */
    void startGame() {
        menuCtrl.detachFromFrame(rootFrame);
        BoardCtrl boardCtrl = new BoardCtrl();
        boardCtrl.attachToFrame(rootFrame);
        rootFrame.setVisible(true);
    }

    /**
     * Exits game
     */
    void exitGame() {
        rootFrame.dispose();
    }
}