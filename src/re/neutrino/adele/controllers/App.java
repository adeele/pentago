package re.neutrino.adele.controllers;

import re.neutrino.adele.views.BoardView;
import re.neutrino.adele.views.MenuView;

/**
 * Starts app
 */
public class App {
    public App() {
        MenuCtrl menuCtrl = new MenuCtrl(this);
    }

    void startGame() {
        BoardView boardView = new BoardView();
    }
}