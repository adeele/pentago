package re.neutrino.adele.controllers;

import re.neutrino.adele.views.GameView;
import re.neutrino.adele.views.MenuView;

/**
 * Class MenuCtrl
 * controller for MenuView class
 */
public class MenuCtrl {
    MenuCtrl(App app) {
        new MenuView(this);
    }
    public void buttonPlayCtrl(){
        new GameView();
    }
}
