/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java.ITTownTrafficManager;

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
public class VersionSelector extends Application {

    /**
     * {@inheritDoc. }
     *
     * @param stage
     * @throws java.lang.Exception
     */
    @Override
    public final void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VersionSelector.fxml"));
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
