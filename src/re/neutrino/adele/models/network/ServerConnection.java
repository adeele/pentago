package re.neutrino.adele.models.network;

import java.io.*;
import java.net.ServerSocket;

/**
 * Network server
 */
public class ServerConnection extends Connection
{
    private final ServerSocket serverSocket;

    /**
     * Creates server connection
     * @param port on with set connection
     * @throws IOException
     */
    public ServerConnection(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        out = new DataOutputStream(socket.getOutputStream());
        in = new BufferedInputStream(socket.getInputStream());
    }

    /**
     * Disconnects
     * @throws IOException
     */
    @Override
    public void disconnect() throws IOException
    {
        super.disconnect();
        serverSocket.close();
    }
}
