/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java.ITTownTrafficManager.ClientStation;

import com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author nick_
 */
public class TrafficStation extends Application {

    /**
     * {@inheritDoc }.
     */
    @Override
    public final void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("TrafficStation.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static final void main(final String[] args) {
        launch(args);
    }

}
