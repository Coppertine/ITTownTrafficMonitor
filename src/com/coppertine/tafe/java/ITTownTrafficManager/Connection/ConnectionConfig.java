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
package com.coppertine.tafe.java.ITTownTrafficManager.Connection;

import java.net.Socket;

/**
 *
 * @author Coppertine
 */
public class ConnectionConfig {
    /**
     * Connection host URL.
     */
    private String hostURL;
    /**
     * Connection host port.
     */
    private int hostPort;
    /**
     * Connection Socket.
     */
    private Socket socket;

    /**
     * Returns the host URL for the current connection.
     * @return Host URL without port numbers.
     */
    public final String getHostURL() {
        return hostURL;
    }

    public ConnectionConfig(String hostURL, int hostPort) {
        this.hostURL = hostURL;
        this.hostPort = hostPort;
    }

    
    /**
     * Sets the host URL for the current connection.
     * @param URL String of the connection's host URL.
     */
    public final void setHostURL(final String URL) {
        this.hostURL = URL;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
}
