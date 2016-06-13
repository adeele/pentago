package re.neutrino.adele.controllers;

import re.neutrino.adele.views.MenuView;

import javax.swing.*;

/**
 * Class MenuCtrl
 * controller for MenuView class
 */
public class MenuCtrl
{
    private final MenuView menuView;
    private final App app;

    /**
     * Creates menu view
     * @param app link to the application
     */
    MenuCtrl(App app)
    {
        this.app = app;
        menuView = new MenuView(this);
    }

    /**
     * Starts game on button click
     */
    public void buttonPlayCtrl()
    {
        app.startGame();
    }

    /**
     * Attaches panel to the view
     * @param rootFrame to add panel
     */
    void attachToFrame(JFrame rootFrame)
    {
        rootFrame.add(menuView.getPanel());
    }

    /**
     * Detaches panel to the panel
     * @param rootFrame to add panel
     */
    void detachFromFrame(JFrame rootFrame)
    {
        rootFrame.remove(menuView.getPanel());
    }

    /**
     * Exits game
     */
    public void quit()
    {
        app.exitGame();
    }

    /**
     * Starts network game on button click
     */
    public void buttonPlayOnlineCtrl()
    {
        menuView.createOnlineModeView(this);
    }

    /**
     * Joins to the existing network game
     * @param address to connect
     */
    public void joinGame(String address)
    {
        app.startGameClient(address);
    }

    /**
     * Creates network game
     */
    public void createGame()
    {
        app.startGameServer();
    }
}
