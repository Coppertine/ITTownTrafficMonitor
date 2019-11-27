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
package com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice;

import com.coppertine.tafe.java.Debug;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;
import static java.lang.Thread.NORM_PRIORITY;
import java.net.Socket;

/**
 *
 * @author Coppertine
 */
public class OfficeThread extends Thread {

    /**
     * The server instance.
     */
    private final OfficeServer client;
    /**
     * The server socket to send and receive information.
     */
    private final Socket socket;
    /**
     * The client location ID.
     */
    private final int clientID;
    /**
     * The port number of the client.
     */
    private final int clientPort;
    /**
     * The incoming stream of information from the clients.
     */
    private DataInputStream streamIn;
    /**
     * The outgoing stream of information to the client.
     */
    private DataOutputStream streamOut;

    /**
     * A volatile boolean to prevent exceptions from thread closure.
     */
    public volatile boolean stopped = false;

    /**
     * The constructor of the Office Thread.
     *
     * @param aThis The office Server instance.
     * @param socketInput The socket for the server.
     * @param clientIdInput The client location ID.
     */
    public OfficeThread(final OfficeServer aThis, final Socket socketInput,
            final int clientIdInput) {
        super();
        this.client = aThis;
        this.socket = socketInput;
        this.clientPort = socket.getPort();
        this.clientID = clientIdInput;
    }

    @Override
    public final void run() {
        while (!stopped) { // Why? just, why?
            try {
                client.handle(clientID, streamIn.readUTF());
                System.out.println(streamIn.readUTF());
            } catch (IOException e) {
                Debug.log(e.getMessage());
            }
        }
    }

    /**
     * Sends a specific message to the thread in question.
     *
     * @param msg The string representation of the message from the client
     */
    public final void send(final String msg) {
        try {
            System.out.println("Sending " + msg);
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException e) {
            Debug.log(e.toString());
            client.remove(clientID);
            this.interrupt();
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
     * Gets the server instance.
     *
     * @return The Office Server instance.
     */
    public final OfficeServer getServer() {
        return client;
    }

    /**
     * Returns the socket of the client thread.
     *
     * @return The client socket instance.
     */
    public final Socket getSocket() {
        return socket;
    }

    /**
     * Returns the client location.
     *
     * @return The integer representation of the location id.
     */
    public final int getClientID() {
        return clientID;
    }

    /**
     * Returns the client port number.
     *
     * @return The integer representation of the port number.
     */
    public final int getClientPort() {
        return clientPort;
    }

    /**
     * Returns the input stream of the client.
     *
     * @return The data input stream.
     */
    public final DataInputStream getStreamIn() {
        return streamIn;
    }

}
