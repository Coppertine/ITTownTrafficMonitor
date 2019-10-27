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
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Coppertine
 */
public class TrafficClient implements Runnable {
    
    private int clientID;
    private ConnectionConfig config;

    public void run(final ConnectionConfig inputConfig) {
        try {
            config.setSocket(
                    new Socket(config.getHostURL(), config.getHostPort()));
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

    public void handle(String msg) {
        if (msg.startsWith("id: ")) {
            clientID = Integer.parseInt(msg.substring("id: ".length() + 1));
        }
        if (msg.startsWith("exit")) {
            
        }
        if(msg.startsWith("status")) {
            sendMessage("status ready");
        }
    }
    
    private void sendMessage(String msg) {
        
    }

    void remove(int clientID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
