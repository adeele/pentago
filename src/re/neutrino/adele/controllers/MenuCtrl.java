package re.neutrino.adele.controllers;

import re.neutrino.adele.views.MenuView;

import javax.swing.*;

/**
 * Class MenuCtrl
 * controller for MenuView class
 */
public class MenuCtrl {

    private final MenuView menuView;
    private final App app;

    MenuCtrl(App app) {
        this.app = app;
        menuView = new MenuView(this);
    }
    public void buttonPlayCtrl(){
        app.startGame();
    }

    void attachToFrame(JFrame rootFrame) {
        rootFrame.add(menuView.getPanel());
    }

    void detachFromFrame(JFrame rootFrame) {
        rootFrame.remove(menuView.getPanel());
    }

    public void quit() {
        app.exitGame();
    }
}
