package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;
import re.neutrino.adele.states.ByteRepresentable;

import java.io.IOException;
import java.net.SocketException;

/**
 * Manages network connection
 */
public class NetworkCtrl
{
    private Connection conn;

    /**
     * Connects to the exiting connection
     * @param address to connect
     * @throws IOException
     */
    void connect(String address) throws IOException
    {
        if (conn != null)
            disconnect();
        conn = new ClientConnection(address, GameConstant.PORT);
    }

    /**
     * Creates new connection
     * @throws IOException
     */
    void listen() throws IOException
    {
        if (conn != null)
            disconnect();
        conn = new ServerConnection(GameConstant.PORT);
    }

    /**
     * Reads message asynchronously
     * @param handler which manages reading
     */
    void readAsync(ReadHandler handler)
    {
        new Thread(() -> {
            try
            {
                byte[] result = read();
                handler.handleSuccess(result);
            } catch (Exception e)
            {
                handler.handleError(e);
            }
        }).start();
    }

    /**
     * Read message
     * @return message read
     * @throws IOException
     * @throws InterruptedException
     */
    private byte[] read() throws IOException, InterruptedException
    {
        checkConn();
        return conn.read();
    }

    /**
     * Submits (sends) message
     * @param move to send
     * @throws IOException
     */
    void submitMove(ByteRepresentable move) throws IOException
    {
        checkConn();
        byte[] msg = move.toBytes();
        conn.send(msg);
    }

    /**
     * Disconnects from the connection
     */
    private void disconnect() throws IOException
    {
        if (conn == null)
            return;
        conn.disconnect();
    }

    /**
     * Checkout if there is (still) connection
     * @throws SocketException
     */
    private void checkConn() throws SocketException
    {
        if (conn == null)
            throw new SocketException("There is no connection");
    }
}
