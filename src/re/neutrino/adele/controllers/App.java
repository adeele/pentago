package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.controllers.network.NetworkCtrl;

import javax.swing.*;

/**
 * Starts app
 */
public class App
{
    private final JFrame rootFrame = new JFrame(GameConstant.PENTAGO);
    private final MenuCtrl menuCtrl;
    private NetworkCtrl networkCtrl;

    /**
     * Creates menu controller which manages menu
     */
    public App()
    {
        menuCtrl = new MenuCtrl(this);
        menuCtrl.attachToFrame(rootFrame);
        rootFrame.setBounds(500, 150, 800, 800);
        rootFrame.setVisible(true);
    }

    /**
     * Starts new game
     */
    void startGame()
    {
        menuCtrl.detachFromFrame(rootFrame);
        BoardCtrl boardCtrl = new BoardCtrl(this);
        boardCtrl.attachToFrame(rootFrame);
        rootFrame.setVisible(true);
    }

    /**
     * Starts network server game
     */
    void startGameServer()
    {
        menuCtrl.detachFromFrame(rootFrame);
        networkCtrl = new NetworkCtrl();
        BoardCtrl boardCtrl = new BoardCtrl(this, networkCtrl, GameConstant.SERVER, null);
        boardCtrl.attachToFrame(rootFrame);
        rootFrame.setVisible(true);
    }

    /**
     * Starts network client game with given host IP address
     * @param address of the server
     */
    void startGameClient(String address)
    {
        menuCtrl.detachFromFrame(rootFrame);
        networkCtrl = new NetworkCtrl();
        BoardCtrl boardCtrl = new BoardCtrl(this, networkCtrl, GameConstant.CLIENT, address);
        boardCtrl.attachToFrame(rootFrame);
        rootFrame.setVisible(true);
    }

    /**
     * Exits game
     */
    void exitGame()
    {
        rootFrame.dispose();
    }
}