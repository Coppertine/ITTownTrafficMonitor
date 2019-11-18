/*
 * The MIT License
 *
 * Copyright 2019 Coppertine.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.coppertine.tafe.java.ITTownTrafficManager.ClientStation;

import com.coppertine.tafe.java.Debug;
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Coppertine
 */
public class TrafficClient implements Runnable {

    private int clientID;
    private ConnectionConfig config = new ConnectionConfig();
    private DataInputStream streamIn;
    private DataOutputStream streamOut;
    private Socket socket;

    /**
     *
     * @param inputConfig
     */
    public void run(final ConnectionConfig inputConfig) {
        try {
            config = inputConfig;
            config.setSocket(
                    new Socket(config.getHostURL(), config.getHostPort()));
            socket = config.getSocket();
            System.out.println("Connected: " + config.getSocket());
            open();
        } catch (UnknownHostException uhe) {
            Debug.log("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            Debug.log("Unexpected exception: " + ioe.getMessage());
        }

    }

    @Override
    public final void run() {
        config = new ConnectionConfig();
        run(config);
    }

    /**
     * 
     * @param msg 
     */
    public final void handle(String msg) {
        if (msg.startsWith("id: ")) {
            System.out.println(msg);
            clientID = Integer.parseInt(msg.substring("id: ".length() + 1));
        }
        if (msg.startsWith("exit")) {

        }
        if (msg.startsWith("status")) {
            send("status ready");
        }
    }

    /**
     *
     * @param msg
     */
    public void send(String msg) {
        try {
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException e) {
            Debug.log(e.toString());
        }
    }

    /**
     * Opens the thread with streams loaded.
     *
     * @throws IOException if DataInputStreams can not be created.
     */
    public void open() throws IOException {
        streamIn = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));
    }

    void remove(int clientID) {
    }

    /**
     *
     * @return
     */
    public int getClientID() {
        return clientID;
    }

    /**
     *
     * @param clientID
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

}
