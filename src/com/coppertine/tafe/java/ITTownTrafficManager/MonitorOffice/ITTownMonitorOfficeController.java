/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice;

import com.coppertine.tafe.java.ITTownTrafficManager.BinaryTree.BinaryTreeView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author nick_
 */
public class ITTownMonitorOfficeController implements Initializable {    
    double x;
    double y;
    public TextArea txtLinkedList;
    /**
     * Drags window on mouse held.
     * @param event MouseEvent.
     */
    @FXML
    final void dragWindow(final MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    /**
     *
     * @param event
     */
    @FXML
    void press(MouseEvent event)
    {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    @FXML
    void programClose(MouseEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void sortByLocation(MouseEvent event) {
        
    }
    
    @FXML
    public void displayBinaryTree(MouseEvent event) {
        BinaryTreeView tree = new BinaryTreeView();
        
        tree.addNode(12);
        tree.addNode(2);
        tree.addNode(13);
        tree.addNode(52);
        tree.addNode(33);
        tree.addNode(23);
        tree.addNode(4);
        tree.addNode(27);
        tree.addNode(44);
        tree.addNode(3);
        tree.addNode(1);
        
        tree.include(tree.getRootNode());
        tree.run();
    }
}
