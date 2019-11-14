/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice;

import com.coppertine.tafe.java.FileIO;
import com.coppertine.tafe.java.ITTownTrafficManager.BinaryTree.BinaryTreeView;
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import com.coppertine.tafe.java.ITTownTrafficManager.Location;
import com.coppertine.tafe.java.ITTownTrafficManager.Settings;
import com.coppertine.tafe.java.ITTownTrafficManager.Traffic;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author nick_
 */
public class ITTownMonitorOfficeController implements Initializable {

    double windowX;
    double windowY;

    /**
     * Interactable and changable Objects used into the FXML.
     */
    /**
     * Linked List Text Area.
     */
    @FXML
    private TextArea txtLinkedList;

    /**
     * Window Menubar.
     */
    @FXML
    private MenuBar windowMenuBar;
    /**
     * Menu objects.
     */
    /**
     * File Menu.
     */
    @FXML
    private Menu fileMenu;

    /**
     * Edit Menu.
     */
    @FXML
    private Menu editMenu;

    /**
     * Import Menu.
     */
    /**
     * Help Menu.
     */
    @FXML
    private Menu helpMenu;

    /**
     * FontAwesomeIcons.
     */
    /**
     * minimize Icon.
     */
    @FXML
    private FontAwesomeIconView minimiseIcon;
    /**
     * Resize Icon.
     */
    @FXML
    private FontAwesomeIconView resizeIcon;
    /**
     * Close Icon
     */
    @FXML
    private FontAwesomeIconView closeIcon;

    /**
     * Server Buttons.
     */
    /**
     * Start Server.
     */
    @FXML
    private MenuItem mItemStartServer;

    /**
     * Server Options
     */
    @FXML
    private MenuItem mItemServerOptions;

    @FXML
    private MenuItem mItemServerToggle;

    private ConnectionConfig serverConfig;

    @FXML
    private MenuItem importMenuItem;

    @FXML
    private MenuItem exportMenu;

    /* Table */
    /**
     * Table view.
     */
    @FXML
    private TableView<Traffic> tblView;
    @FXML
    private TableColumn<Traffic, LocalDateTime> tblTrafficTime;
    @FXML
    private TableColumn<Traffic, String> tblTrafficLocation;
    @FXML
    private TableColumn<Traffic, Integer> tblTrafficAverageVeh;
    @FXML
    private TableColumn<Traffic, Integer> tblTrafficAverageVel;

    private OfficeServer server;

    /**
     * Drags window on mouse held.
     *
     * @param event MouseEvent.
     */
    @FXML
    final void dragWindow(final MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - windowX);
        stage.setY(event.getScreenY() - windowY);
    }

    /**
     *
     * @param event
     */
    @FXML
    void press(MouseEvent event) {
        windowX = event.getSceneX();
        windowY = event.getSceneY();
    }

    @FXML
    public final void programClose(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        serverConfig = new ConnectionConfig();
    }

    /**
     * Sorts the Table by location using the Bubble sort Method.
     *
     * @param event
     */
    @FXML
    public void sortByLocation(MouseEvent event) {
        //new SortingTools().sortAlgorithm(SortingType.BubbleSort, );
    }

    @FXML
    public void displayBinaryTree(MouseEvent event) {
        BinaryTreeView tree = new BinaryTreeView();

//        tree.addNode(12);
//        tree.addNode(2);
//        tree.addNode(13);
//        tree.addNode(52);
//        tree.addNode(33);
//        tree.addNode(23);
//        tree.addNode(4);
//        tree.addNode(27);
//        tree.addNode(44);
//        tree.addNode(3);
//        tree.addNode(1);
        tree.include(tree.getRootNode());
        tree.run();
    }

    @FXML
    public void sortByVehicle(MouseEvent event) {

    }

    @FXML
    public void sortByVelocity(MouseEvent event) {

    }

    @FXML
    public void startServer() {
        server = new OfficeServer(serverConfig);
        Thread thread = new Thread(server, "thread");
        thread.start();
    }

    @FXML
    public void stopServer() {
        server.stop();
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

//</editor-fold>
    /**
     * Instantiates the Table Columns.
     */
    private void setupTable() {
        tblTrafficLocation.setCellValueFactory(cellData
                -> cellData.getValue().locationProperty().asString());
        tblTrafficTime.setCellValueFactory(cellData
                -> cellData.getValue().timeProperty());
        tblTrafficAverageVeh.setCellValueFactory(cellData
                -> cellData.getValue().averagePerLaneProperty().asObject());
        tblTrafficAverageVel.setCellValueFactory(cellData
                -> cellData.getValue().averageVelocityProperty().asObject());

        ObservableList<Traffic> list = tblView.getItems();
        list.add(new Traffic(
                LocalDateTime.now(),
                new Location(2, "Test"),
                1,
                1,
                1,
                1)
        );
        tblView.setItems(list);
    }

    @FXML
    public void performAction(ActionEvent actionEvent) {
        MenuItem target = (MenuItem) actionEvent.getSource();

        if (target.getId().equals("mItemServerOptions")) {
            editServer();
        }
        if (target.getId().equals("mItemServerToggle")) {
            System.out.println("Server Started");
            toggleServer();
        }
        if (target.getId().equals("importMenuItem")) {
            System.out.println("Button Pressed");
            importTraffic(actionEvent);
        }
//        if (target.getId().equals("exportMenu")) {
//            exportTraffic();
//        }
    }

    /**
     *
     */
    private void editServer() {
        serverConfig = new Settings().open(serverConfig);

    }

    /**
     * Toggles the Server on or off depending if the server exists.
     */
    private void toggleServer() {
        if (server == null) {
            startServer();
        } else {
            stopServer();
            server = null;
        }
    }

    /**
     *
     * @param event
     */
    private void importTraffic(ActionEvent event) {
        System.out.println("Importing File");
        String filePath = openFileDialog(event);
        tblView.getItems().clear();
        try {
            for (Traffic traffic
                    : new Traffic()
                            .parseTrafficLines(
                                    FileIO.readFile(filePath))) {
                tblView.getItems().add(traffic);
            }

            for (Object obj : tblView.getItems().toArray()) {
                System.out.println(obj.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(
                    ITTownMonitorOfficeController.class.getName()
            ).log(Level.SEVERE, null, ex);
        }
    }

    private void exportTraffic(ActionEvent event) {
        String exportFilePath = saveFileDialog(event);

    }

    private String openFileDialog(ActionEvent event) {
        FileChooser fc = new FileChooser();
        System.out.println("Open Dialog");
        fc.setTitle("Open CSV File");
        fc.getExtensionFilters().add(
                new ExtensionFilter(
                        "Comma Seperated Values", "*.csv"));

        File file = fc.showOpenDialog(
                ((MenuItem) event.getSource())
                        .getParentPopup()
                        .getScene()
                        .getWindow());
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }

    private String saveFileDialog(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save CSV File");
        fc.getExtensionFilters().add(
                new ExtensionFilter(
                        "Comma Seperated Values", "*.csv"));
        File file = fc.showSaveDialog(((MenuItem) event.getSource())
                .getParentPopup().getScene()
                .getWindow());
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }
}
