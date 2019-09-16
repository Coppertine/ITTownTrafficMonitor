/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice;


import com.coppertine.tafe.java.ITTownTrafficManager.BinaryTree.BinaryTreeView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
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
    
    /**
     * Interactable and changable Objects used into the FXML.
     */

    /** Linked List Text Area. */
    @FXML
    private TextArea txtLinkedList; // Linked List Text Area

    /** Window Menubar. */
    @FXML
    private MenuBar windowMenuBar;
    /** Menu objects. */
    @FXML
    private Menu fileMenu;  /** File Menu. */
    @FXML
    private Menu editMenu;  /** Edit Menu. */
    @FXML
    private Menu helpMenu;  /** Help Menu. */

    /** FontAwesomeIcons. */
    /** Minimise Icon. */
    @FXML
    private FontAwesomeIconView minimiseIcon;
    /** Resize Icon. */
    @FXML
    private FontAwesomeIconView resizeIcon;
    @FXML
    private FontAwesomeIconView closeIcon;

    /** Server Buttons. */
    /** Start Server. */
    @FXML
    private MenuItem mItemStartServer;

    /** Server Options */
    @FXML
    private MenuItem mItemServerOptions;

    /** Table */
    @FXML
    private TableColumn<?, ?> tblHeadder;
    @FXML
    private TableColumn<?, ?> tblTrafficTime;
    @FXML
    private TableColumn<?, ?> tblTrafficLocation;
    @FXML
    private TableColumn<?, ?> tblTrafficAverageVeh;
    @FXML
    private TableColumn<?, ?> tblTrafficAverageVel;
    
    
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
        System.exit(0);
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
    
    @FXML
    public void sortByVehicle(MouseEvent event) {
        
    }
    
    @FXML
    public void sortByVelocity(MouseEvent event) {
        
    }

    //<editor-fold defaultstate="collasped" desc="Getters and Setters">
    public TextArea getTxtLinkedList() {
        return txtLinkedList;
    }

    public void setTxtLinkedList(TextArea txtLinkedList) {
        this.txtLinkedList = txtLinkedList;
    }

    public MenuBar getWindowMenuBar() {
        return windowMenuBar;
    }

    public void setWindowMenuBar(MenuBar windowMenuBar) {
        this.windowMenuBar = windowMenuBar;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(Menu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public Menu getEditMenu() {
        return editMenu;
    }

    public void setEditMenu(Menu editMenu) {
        this.editMenu = editMenu;
    }

    public Menu getHelpMenu() {
        return helpMenu;
    }

    public void setHelpMenu(Menu helpMenu) {
        this.helpMenu = helpMenu;
    }

    public FontAwesomeIconView getMinimiseIcon() {
        return minimiseIcon;
    }

    public void setMinimiseIcon(FontAwesomeIconView minimiseIcon) {
        this.minimiseIcon = minimiseIcon;
    }

    public FontAwesomeIconView getResizeIcon() {
        return resizeIcon;
    }

    public void setResizeIcon(FontAwesomeIconView resizeIcon) {
        this.resizeIcon = resizeIcon;
    }

    public FontAwesomeIconView getCloseIcon() {
        return closeIcon;
    }

    public void setCloseIcon(FontAwesomeIconView closeIcon) {
        this.closeIcon = closeIcon;
    }

    public MenuItem getmItemStartServer() {
        return mItemStartServer;
    }

    public void setmItemStartServer(MenuItem mItemStartServer) {
        this.mItemStartServer = mItemStartServer;
    }

    public MenuItem getmItemServerOptions() {
        return mItemServerOptions;
    }

    public void setmItemServerOptions(MenuItem mItemServerOptions) {
        this.mItemServerOptions = mItemServerOptions;
    }

    public TableColumn<?, ?> getTableHeader() {
        return tblHeadder;
    }

    public void setTableHeader(TableColumn<?, ?> TableHeader) {
        this.tblHeadder = TableHeader;
    }

    public TableColumn<?, ?> getTrafficTime() {
        return tblTrafficTime;
    }

    public void setTrafficTime(TableColumn<?, ?> TrafficTime) {
        this.tblTrafficTime = TrafficTime;
    }

    public TableColumn<?, ?> getTrafficLocation() {
        return tblTrafficLocation;
    }

    public void setTrafficLocation(TableColumn<?, ?> TrafficLocation) {
        this.tblTrafficLocation = TrafficLocation;
    }

    public TableColumn<?, ?> getTrafficAverageVeh() {
        return tblTrafficAverageVeh;
    }

    public void setTrafficAverageVeh(TableColumn<?, ?> TrafficAverageVeh) {
        this.tblTrafficAverageVeh = TrafficAverageVeh;
    }

    public TableColumn<?, ?> getTrafficAverageVel() {
        return tblTrafficAverageVel;
    }

    public void setTrafficAverageVel(TableColumn<?, ?> TrafficAverageVel) {
        this.tblTrafficAverageVel = TrafficAverageVel;
    }
//</editor-fold>

}
