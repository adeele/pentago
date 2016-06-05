package re.neutrino.adele.controllers;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 * Network connection super class
 */
abstract class Connection {
    DataOutputStream out;
    BufferedInputStream in;
    Socket socket;

    public void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    void send(byte[] msg) throws IOException {
        out.write(msg);
    }

    byte[] read() throws IOException {
        byte[] msg = new byte[in.available()];
        in.read(msg);
        System.out.println(Arrays.toString(msg));
        return msg;
    }
}
