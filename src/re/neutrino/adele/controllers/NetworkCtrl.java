package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.states.ByteRepresentable;

import java.io.IOException;
import java.net.SocketException;

/**
 * Manages network connection
 */
public class NetworkCtrl {
    private Connection conn;

    void connect(String address) throws IOException {
        if(conn != null)
            disconnect();
        conn = new ClientConnection(address, GameConstant.PORT);
    }

    void listen() throws IOException {
        if(conn != null)
            disconnect();
        conn = new ServerConnection(GameConstant.PORT);
    }

    public void readAsync(ReadHandler handler) {
        new Thread(() -> {
            try {
                byte[] result = read();
                handler.handleSuccess(result);
            } catch (Exception e) {
                handler.handleError(e);
            }
        }).start();
    }

    private
    byte[] read() throws IOException, InterruptedException {
        checkConn();
        return conn.read();
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
