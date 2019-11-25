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
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import com.coppertine.tafe.java.ITTownTrafficManager.Traffic;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Coppertine
 */
public class OfficeServer implements Runnable {

    /**
     * Exited boolean to prevent thread exceptions when shutting down a server.
     */
    private volatile boolean exited = false;
    private ArrayList<OfficeThread> clients = new ArrayList<>();
    private ConnectionConfig config;
    private ServerSocket server = null;
    private ITTownMonitorOfficeController controller;

    /**
     *
     * @param inputConfig
     * @param _controller
     */
    public OfficeServer(ConnectionConfig inputConfig, ITTownMonitorOfficeController _controller) {
        try {
            System.out.println("Starting server at: " + inputConfig.getHostPort());
            this.config = inputConfig;
            this.controller = _controller;
            server = new ServerSocket(config.getHostPort());
        } catch (IOException e) {
            Debug.log(e.toString());

        }
    }

    /**
     * Attempts to stop the Office Server.
     */
    public final void stop() {

        exited = true;
    }

    /**
     *
     * @param socket
     */
    public void addThread(Socket socket) {
        Debug.log("Client Accepted: " + socket);
        OfficeThread client
                = new OfficeThread(this, socket, clients.size() + 1);
        clients.add(client);
        try {
            client.open();
            client.start();
            client.send("id: " + clients.indexOf(client));
        } catch (IOException e) {
            Debug.log(e.toString());
        }
    }

    /**
     * {@inheritDoc }.
     */
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

    /**
     *
     * @param ID
     * @param input
     */
    public synchronized void handle(int ID, String input) {
        switch (input) {
            case "exit":
                findClient(ID).send("exit");
                remove(ID);
                break;
            case "status ready":
                controller.printToMessageScreen("Client "
                        + findClient(ID).getClientID() + "Ready");
                break;
            default:
                System.out.println(ID + ": " + input);
                clients.forEach((client) -> {
                    client.send(ID + ": " + input);
                });
                handleCommands(ID, input);
                break;
        }
    }

    private void handleCommands(int ID, String input) {
        try {

            if (input.startsWith("Traffic: ")) {

                Traffic importTraffic
                        = new Traffic(input.substring("Traffic: ".length()));
                controller.trafficImport(importTraffic);
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the client with the same port as the ID.
     *
     * @param clientID Integer of the Port Number of the client to find.
     * @return The OfficeThread of the Client, if present.
     * @throws NoSuchElementException When no element is found of the same ID.
     */
    public final OfficeThread findClient(final int clientID)
            throws NoSuchElementException {
        return clients.stream()
                .filter(c -> c.getClientPort() == clientID)
                .findFirst()
                .get();
    }

    /**
     *
     * @param clientID
     */
    public void remove(int clientID) {
        OfficeThread toRemove;
        try {
            toRemove = findClient(clientID);
            clients.remove(toRemove);
        } catch (NoSuchElementException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Sends a status check to all clients of the server.
     */
    public final void statusCheck() {
        clients.forEach((thread) -> {
            thread.send("status check");
        });
    }
}
