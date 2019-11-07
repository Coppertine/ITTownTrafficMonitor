/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java.ITTownTrafficManager;

import com.coppertine.tafe.java.ITTownTrafficManager.ClientStation.TrafficStation;
import com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice.ITTownTrafficMonitor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class.
 *
 * @author nick_
 */
public class VersionSelectorController implements Initializable {

    /**
     *
     */
    @FXML
    private Button btnOpenOffice;

    /**
     *
     */
    @FXML
    private Button btnOpenStation;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void openOffice() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ITTownTrafficMonitor.class.getResource("ITTownMonitorOffice.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Traffic Station");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            // Hide this current window (if this is what you want)
        } catch (IOException e) {
        }
        Stage stage = (Stage) btnOpenOffice.getScene().getWindow();
        stage.close();
    }

    /**
     * Opens the Client Station frame.
     */
    @FXML
    public void openStation() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(TrafficStation.class.getResource("TrafficStation.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Traffic Station");
            stage.setScene(new Scene(fxmlLoader.load(), 450, 450));
            stage.show();
        } catch (IOException e) {
        }

        Stage stage = (Stage) btnOpenStation.getScene().getWindow();
        stage.close();

    }

}
