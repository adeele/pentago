package re.neutrino.adele.controllers;

import re.neutrino.adele.views.BoardView;
import re.neutrino.adele.views.GameView;
import re.neutrino.adele.views.MenuView;

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
}
