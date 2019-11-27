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
    /**
     * The list of client threads for the server.
     */
    private ArrayList<OfficeThread> clients = new ArrayList<>();
    /**
     * The server configuration.
     */
    private ConnectionConfig config;
    /**
     * The server socket, initially null to check if the server is running.
     */
    private ServerSocket server = null;
    /**
     * The FXML Controller.
     */
    private ITTownMonitorOfficeController controller;

    /**
     * The Constructor of the Office Server.
     *
     * @param inputConfig The server configuration.
     * @param controllerInput The FXML controller.
     */
    public OfficeServer(
            final ConnectionConfig inputConfig,
            final ITTownMonitorOfficeController controllerInput) {
        try {
            System.out.println(
                    "Starting server at: " + inputConfig.getHostPort());
            this.config = inputConfig;
            this.controller = controllerInput;
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
     * Creates a new Client thread from the socket that connects through.
     *
     * @param socket The client socket that connects to the server.
     */
    public final void addThread(final Socket socket) {
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

    @Override
    public final void run() {
        while (!exited) {
            try {
                addThread(server.accept());
            } catch (IOException e) {

            }
        }
        Debug.log("Server is stopped.");
    }

    /**
     * Handles the message coming from the client thread.
     * @param Id The location ID of the client.
     * @param input The message from the client.
     */
    public final synchronized void handle(final int Id, final String input) {
        switch (input) {
            case "exit":
                findClient(Id).send("exit");
                remove(Id);
                break;
            case "status ready":
                controller.printToMessageScreen("Client "
                        + Id + "Ready");
                break;
            default:
                System.out.println(Id + ": " + input);
                clients.forEach((client) -> {
                    client.send(Id + ": " + input);
                });
                handleCommands(Id, input);
                break;
        }
    }

    /**
     * Parses the traffic information to the controller.
     * @param ID The location number of the client.
     * @param input  The string input in CSV format starting with
     * {@code Traffic: }
     */
    private void handleCommands(final int ID, final String input) {
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
     * Attempts to remove the client thread from the server.
     * @param clientID The client location id.
     */
    public final void remove(final int clientID) {
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
