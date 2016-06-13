package re.neutrino.adele.controllers;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Network client
 */
class ClientConnection extends Connection
{
    /**
     * Creates connection of the client side
     * @param address of the connection
     * @param port of the connection
     * @throws IOException
     */
    ClientConnection(String address, int port) throws IOException
    {
        socket = new Socket(address, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new BufferedInputStream(socket.getInputStream());
    }
}
