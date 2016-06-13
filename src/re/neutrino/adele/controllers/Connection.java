package re.neutrino.adele.controllers;

import re.neutrino.adele.GameConstant;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;

/**
 * Network connection super class
 */
abstract class Connection
{
    DataOutputStream out;
    BufferedInputStream in;
    Socket socket;

    /**
     * Disconnects
     * @throws IOException
     */
    public void disconnect() throws IOException
    {
        in.close();
        out.close();
        socket.close();
    }

    /**
     * Sends the message
     * @param msg to send
     * @throws IOException
     */
    void send(byte[] msg) throws IOException
    {
        out.write(msg);
    }

    /**
     * Reads the message
     * @return message
     * @throws IOException
     * @throws InterruptedException
     */
    byte[] read() throws IOException, InterruptedException
    {
        byte[] msg = new byte[3];
        int i;
        for (i = 0; i < GameConstant.TIMEOUT && in.read(msg) <= 0; i++)
            Thread.sleep(1000);
        if (i == GameConstant.TIMEOUT)
            throw new SocketTimeoutException();
        System.out.println(Arrays.toString(msg));
        return msg;
    }
}
