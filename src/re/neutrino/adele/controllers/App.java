package re.neutrino.adele.controllers;

import re.neutrino.adele.views.MenuView;

/**
 * Class App
 * starts app
 */
public class App {
    public App() {
        MenuCtrl menuCtrl = new MenuCtrl(this);
    }
}
