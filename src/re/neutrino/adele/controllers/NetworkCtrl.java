package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.models.Move;

import java.io.IOException;
import java.net.SocketException;

/**
 * Manages network connection
 */
public class NetworkCtrl {
    private Connection conn;

    public void connect(String address) throws IOException {
        if(conn != null)
            disconnect();
        conn = new ClientConnection(address, GameConstant.PORT);
    }

    public void listen() throws IOException {
        if(conn != null)
            disconnect();
        conn = new ServerConnection(GameConstant.PORT);
    }

    public byte[] read() throws IOException {
        checkConn();
        return conn.read();
    }

    public void submitMove(Move move) throws IOException {
        checkConn();
        byte[] msg = new byte[4];
        msg[0] = GameConstant.MESSAGE_MOVE;
        msg[1] = move.getBallNumber();
        msg[2] = move.getSquareNumber();
        msg[3] = move.getDirection();
        conn.send(msg);
    }

    public void reportLoss() throws IOException {
        checkConn();
        byte[] msg = new byte[1];
        msg[0] = GameConstant.MESSAGE_LOSS;
        conn.send(msg);
    }

    private void disconnect() {
        if (conn == null)
            return;
    }

    private void checkConn() throws SocketException {
        if(conn == null)
            throw new SocketException("There is no connection");
    }
}
