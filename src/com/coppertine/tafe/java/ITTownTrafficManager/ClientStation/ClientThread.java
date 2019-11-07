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
import java.net.Socket;

/**
 *
 * @author Coppertine
 */
public class ClientThread extends Thread {

    private TrafficClient client;

    private final int clientID;
    private final int clientPort;

    public volatile boolean stopped = false;

    public ClientThread(TrafficClient aThis, Socket socketInput, int client) {
        super();

        this.clientID = client;
        this.clientPort = 0;
    }

    @Override
    public void run() {
        while (!stopped) { // Why? just, why?
            try {
                //client.handle(streamIn.readUTF());
            } catch (Exception e) {
                Debug.log(e.getMessage());
            }
        }
    }

    public TrafficClient getClient() {
        return client;
    }

    public int getClientID() {
        return clientID;
    }

    public int getClientPort() {
        return clientPort;
    }

    public static int getMIN_PRIORITY() {
        return MIN_PRIORITY;
    }

    public static int getNORM_PRIORITY() {
        return NORM_PRIORITY;
    }

    public static int getMAX_PRIORITY() {
        return MAX_PRIORITY;
    }

}
