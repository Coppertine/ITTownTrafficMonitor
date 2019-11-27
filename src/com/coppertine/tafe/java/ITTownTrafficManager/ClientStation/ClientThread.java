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
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Coppertine
 */
public class ClientThread extends Thread {

    private TrafficClient client;

    private final int clientID;
    private final int clientPort;

    /**
     *
     */
    public volatile boolean stopped = false;

    /**
     * The constructor for the Client Thread.
     *
     * @param aThis The traffic client instance.
     * @param socketInput The socket for the client.
     * @param clientInput The client location id.
     */
    public ClientThread(final TrafficClient aThis, final Socket socketInput,
            final int clientInput) {
        super();

        this.clientID = clientInput;
        this.clientPort = 0;
        this.client = aThis;

        this.start();
    }

    @Override
    public final void run() {
        System.out.println("Start Running");
        while (!stopped) { // Why? just, why?
            try {
                System.out.println("I am Repeating!!");
                client.handle(client.getStreamIn().readUTF());
//                System.out.println(client.getStreamIn().readUTF());

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Returns the Traffic Client
     * @return The traffic client instance.
     */
    public TrafficClient getClient() {
        return client;
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

}
