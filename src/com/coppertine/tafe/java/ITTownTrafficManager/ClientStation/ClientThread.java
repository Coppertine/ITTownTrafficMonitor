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
import com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice.OfficeServer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Coppertine
 */
public class ClientThread extends Thread {
    private OfficeServer server;
    private Socket socket;
    private final int clientID;
    private final int clientPort;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;
    public volatile boolean stopped = false;
    
    public ClientThread(OfficeServer aThis, Socket socketInput, int client) {
        super();
        this.socket = socketInput;
        this.clientPort = socket.getPort();
        this.clientID = client;
    }
    
    public void run() {
        while(stopped) { // Why? just, why?
            try {
                server.handle(clientID, streamIn.readUTF());
            } catch {
                
            }
        }
    }
    
    public void send(String msg) {
        try {
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException e) {
            Debug.log(e.toString());
            server.remove(clientID);
            this.interrupt();
        }
    }

    public void open() throws IOException {
        streamIn = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));
    }

    public OfficeServer getServer() {
        return server;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getClientID() {
        return clientID;
    }

    public int getClientPort() {
        return clientPort;
    }

    public DataInputStream getStreamIn() {
        return streamIn;
    }

    public DataOutputStream getStreamOut() {
        return streamOut;
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
