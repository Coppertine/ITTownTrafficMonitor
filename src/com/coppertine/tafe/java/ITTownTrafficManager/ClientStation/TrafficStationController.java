/*
 * The MIT License
 *
 * Copyright 2019 nick_.
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

import com.coppertine.tafe.java.ITTownTrafficManager.Settings;
import com.coppertine.tafe.java.Debug;
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class.
 *
 * @author nick_
 */
public class TrafficStationController implements Initializable {

    /**
     * Window Position X.
     */
    private double x = 0;
    /**
     * Window Position Y.
     */
    private double y = 0;

    /**
     * Default value for server port numbers.
     */
    private static final int DEFAULT_SERVER_PORT = 4444;

    /**
     *
     */
    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtLanes;

    @FXML
    private TextField txtVehicles;

    @FXML
    private TextField txtAverageVeh;

    @FXML
    private TextField txtAverageVel;

    private ConnectionConfig config;
    private TrafficClient client = new TrafficClient();

    /**
     * {@inheritDoc }.
     */
    @Override
    public final void initialize(final URL url, final ResourceBundle rb) {
        config = new ConnectionConfig("localhost", DEFAULT_SERVER_PORT);
    }

    /**
     * Attempts to connect to the specified server from the
     * {@link ConnectionConfig}.
     */
    @FXML
    public final void startServer() {
        Debug.log("Establishing connection.");
        client.run(config);
    }

    /**
     *
     */
    public final void stopServer() {
        //client.sendMessage("exit");
    }

    /**
     * Drags the Window to Cursor position.
     *
     * @param event The current Mouse Event when holding down on menubar.
     */
    @FXML
    final void dragWindow(final MouseEvent event) {
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    /**
     * Gets cursor position on mouse press.
     *
     * @param event The current Mouse Event when holding down on menubar.
     */
    @FXML
    final void press(final MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     *
     * @param event
     */
    @FXML
    public final void closeWindow(final MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    /**
     *
     *
     */
    @FXML
    public final void performAction(final ActionEvent actionEvent) {
        MenuItem target = (MenuItem) actionEvent.getSource();

        if (target.getId().equals("settingsMenuItem")) {
            editServer();
        }
        if (target.getId().equals("startMenuItem")) {
            toggleServer();
        }
        if (target.getId().equals("sendTrafficInformationBtn")) {
            sendInformation();
        }
    }

    /**
     *
     */
    @FXML
    public final void editServer() {
        config = new Settings().open(config);
    }

    private void toggleServer() {
        if (client == null) {
            startServer();
        } else {
            stopServer();
        }
    }

    private void sendInformation() {

    }

}
