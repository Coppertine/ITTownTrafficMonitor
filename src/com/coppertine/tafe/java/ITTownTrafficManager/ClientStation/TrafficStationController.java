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
import com.coppertine.tafe.java.ITTownTrafficManager.Location;
import com.coppertine.tafe.java.ITTownTrafficManager.Traffic;
import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
     * Text field for time.
     */
    @FXML
    private TextField txtTime;

    /**
     * Text field for number of lanes.
     */
    @FXML
    private TextField txtLanes;

    /**
     * Text field for number of vehicles.
     */
    @FXML
    private TextField txtVehicles;

    /**
     * Text field for number of average vehicles per lane.
     */
    @FXML
    private TextField txtAverageVeh;

    /**
     * Text field for average velocity of traffic.
     */
    @FXML
    private TextField txtAverageVel;

    /**
     * Submission button to server.
     */
    @FXML
    private Button btnSubmit;

    /**
     * Client to server connection configuration.
     */
    private ConnectionConfig config;
    /**
     * The traffic client thread.
     */
    private final TrafficClient client = new TrafficClient();

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
     * Attempts to stop the server connection.
     */
    public final void stopServer() {
        client.send("exit");
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
     * Closes the program once the X icon is clicked.
     *
     * @param event The mouse information in the form of an event.
     */
    @FXML
    public final void closeWindow(final MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    /**
     * The action performed from a click on a Menu Item instance.
     *
     * @param actionEvent The action event for the menu item.
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

    }

    /**
     * Opens the settings dialog to edit the configuration.
     */
    @FXML
    public final void editServer() {
        config = new Settings().open(config);
    }

    /**
     * Toggles the Server on or off depending if the server exists.
     */
    private void toggleServer() {
        if (client == null) {
            startServer();
        } else {
            stopServer();
        }
    }

    /**
     * Sends all information to the server from the text fields.
     */
    @FXML
    public final void sendInformation() {
        System.out.println("Sending Traffic Information to Server.");
        Traffic sendTraffic = new Traffic(
                LocalDateTime.parse(txtTime.getText()),
                new Location(
                        client.getClientID(),
                        String.valueOf(client.getClientID())),
                Integer.parseInt(txtLanes.getText()),
                Integer.parseInt(txtVehicles.getText()),
                Integer.parseInt(txtAverageVeh.getText()),
                Integer.parseInt(txtAverageVel.getText())
        );

        String traficString = sendTraffic.toString();
        client.send("Traffic: " + traficString);
        System.out.println("Traffic: " + traficString);
    }

}
