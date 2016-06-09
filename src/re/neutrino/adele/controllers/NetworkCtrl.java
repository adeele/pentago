package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.models.Move;
import re.neutrino.adele.states.ByteRepresentable;

import java.io.IOException;
import java.net.SocketException;

/**
 * Manages network connection
 */
public class NetworkCtrl {
    private Connection conn;
    private boolean server;

    void connect(String address) throws IOException {
        if(conn != null)
            disconnect();
        conn = new ClientConnection(address, GameConstant.PORT);
        server = false;
    }

    void listen() throws IOException {
        if(conn != null)
            disconnect();
        conn = new ServerConnection(GameConstant.PORT);
        server = true;
    }

    byte[] read() throws IOException, InterruptedException {
        checkConn();
        return conn.read();
    }

    public boolean isServer() {
        return server;
    }

    void submitMove(ByteRepresentable move) throws IOException {
        checkConn();
        byte[] msg = move.toBytes();
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
