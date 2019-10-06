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
import com.coppertine.tafe.java.ITTownTrafficManager.ClientStation.ClientThread;
import com.coppertine.tafe.java.ITTownTrafficManager
            .ClientStation.TrafficClient;
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Coppertine
 */
public class OfficeServer implements Runnable {
    /**
     * Exited boolean to prevent thread exceptions when shutting down a server.
     */
    private volatile boolean exited = false;
    private ArrayList<ClientThread> clients = new ArrayList<>();
    private ConnectionConfig config;
    private ServerSocket server;
    
    public OfficeServer() {
        super();
    }
    
    /**
     * Attempts to stop the Office Server.
     */
    public final void stop() {
        exited = true;
    }
    
    public void addThread(Socket socket) {
        Debug.log("Client Accepted: " + socket);
        ClientThread client = new ClientThread(this, socket);
        clients.add(client);
        try {
            client.open();
            client.start();
        } catch (IOException e) {
            Debug.log(e.toString());
        }
    }

    @Override
    public void run() {
        while (!exited) {
            try {
                addThread(server.accept());
            } catch (IOException e) {
                
            } 
        }
        Debug.log("Server is stopped.");
    }
}