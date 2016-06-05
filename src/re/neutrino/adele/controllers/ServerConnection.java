package re.neutrino.adele.controllers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Network server
 */
class ServerConnection extends Connection {

    private final ServerSocket serverSocket;

    ServerConnection(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        out = new DataOutputStream(socket.getOutputStream());
        in = new BufferedInputStream(socket.getInputStream());
    }

    @Override
    public void disconnect() throws IOException {
        super.disconnect();
        serverSocket.close();
    }
}
