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
    private ClientThread thread;

    /**
     *
     * When an object implementing interface <code>Runnable</code> is used to
     * create a thread, starting the thread causes the object's <code>run</code>
     * method to be called in that separately executing thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may take
     * any action whatsoever.
     *
     * @see java.lang.Thread#run()
     * @param inputConfig The configuration of the server connection.
     */
    public final void run(final ConnectionConfig inputConfig) {
        try {
            config = inputConfig;

            config.setSocket(
                    new Socket(config.getHostURL(), config.getHostPort()));
            socket = config.getSocket();
            System.out.println("Connected: " + config.getSocket());
            open();
            thread = new ClientThread(this, socket, socket.getPort());
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
     * Handles the message coming from the server thread.
     *
     * @param msg The message from the server.
     */
    public final void handle(final String msg) {
        if (msg.startsWith("id: ")) {
            clientID = Integer.parseInt(msg.substring("id: ".length()));
        } else if (msg.startsWith("exit")) {
            thread.stopped = true;
        } else if (msg.startsWith("status")) {
            System.out.println("Status found");
            send("status ready");
        } else {
            System.out.println(msg);
        }
    }

    /**
     * Sends a specific message to the thread in question.
     *
     * @param msg The string representation of the message from the client
     */
    public final void send(final String msg) {
        try {
            System.out.println("Sending: " + msg);
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
    public final void open() throws IOException {
        streamIn = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));
    }

    /**
     *
     * Attempts to remove the client from the server.
     *
     * @param clientID The Location Id.
     * @deprecated Unimplemented
     */
    void remove(final int clientID) {
    }

    /**
     * Returns the integer representation of the location Id.
     *
     * @return The client location Id.
     */
    public final int getClientID() {
        return clientID;
    }

    /**
     * Sets the client location Id.
     *
     * @param clientIDInput The client location Id.
     */
    public final void setClientID(final int clientIDInput) {
        this.clientID = clientIDInput;
    }

    /**
     * Returns the client connection configuration object.
     *
     * @return The client configuration instance.
     */
    public final ConnectionConfig getConfig() {
        return config;
    }

    /**
     * Sets the client connection configuration object.
     *
     * @param configInput The client configuration instance.
     */
    public final void setConfig(final ConnectionConfig configInput) {
        this.config = configInput;
    }

    /**
     * Returns the input stream of the server.
     *
     * @return The data input stream.
     */
    public final DataInputStream getStreamIn() {
        return streamIn;
    }

    /**
     * Sets the input stream of the server.
     *
     * @param streamInInput The data input stream.
     */
    public final void setStreamIn(final DataInputStream streamInInput) {
        this.streamIn = streamInInput;
    }

    /**
     * Returns the output stream of the server.
     *
     * @return The data output stream.
     */
    public final DataOutputStream getStreamOut() {
        return streamOut;
    }

    /**
     * Sets the output stream object.
     *
     * @param streamOutInput The data output stream.
     */
    public final void setStreamOut(final DataOutputStream streamOutInput) {
        this.streamOut = streamOutInput;
    }

}
