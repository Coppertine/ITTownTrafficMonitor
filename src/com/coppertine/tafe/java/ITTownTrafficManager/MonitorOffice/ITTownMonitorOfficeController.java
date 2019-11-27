/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java.ITTownTrafficManager.MonitorOffice;

import com.coppertine.tafe.java.BinaryTree;
import com.coppertine.tafe.java.DLNode;
import com.coppertine.tafe.java.DList;
import com.coppertine.tafe.java.FileIO;
import com.coppertine.tafe.java.Hash;
import com.coppertine.tafe.java.ITTownTrafficManager.BinaryTree.BinaryTreeView;
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import com.coppertine.tafe.java.ITTownTrafficManager.Location;
import com.coppertine.tafe.java.ITTownTrafficManager.Settings;
import com.coppertine.tafe.java.ITTownTrafficManager.Traffic;
import com.coppertine.tafe.java.sorting.SortingTools;
import com.coppertine.tafe.java.sorting.SortingType;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author nick_
 */
public class ITTownMonitorOfficeController implements Initializable {

    /**
     * The window's X position.
     */
    private double windowX;
    /**
     * The window's Y position.
     */
    private double windowY;

    /**
     * Injectable Objects used into the FXML.
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
     * Close Icon.
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
     * Server Options.
     */
    @FXML
    private MenuItem mItemServerOptions;

    /**
     * Server toggle menu item.
     */
    @FXML
    private MenuItem mItemServerToggle;

    /**
     * The server configuration.
     */
    private ConnectionConfig serverConfig;

    /**
     * Import CSV file menu item.
     */
    @FXML
    private MenuItem importMenuItem;
    /**
     * Export to CSV file menu item.
     */
    @FXML
    private MenuItem exportMenu;
    /**
     * Status check button, used for checking the status of all locations.
     */
    @FXML
    private Button btnStatusCheck;

    /* Table */
    /**
     * Table view.
     */
    @FXML
    private TableView<Traffic> tblView;
    /**
     * Traffic time column in the table view.
     */
    @FXML
    private TableColumn<Traffic, LocalDateTime> tblTrafficTime;
    /**
     * Traffic location column in the table view.
     */
    @FXML
    private TableColumn<Traffic, String> tblTrafficLocation;
    /**
     * Traffic average number of vehicles per lane column in the table view.
     */
    @FXML
    private TableColumn<Traffic, Integer> tblTrafficAverageVeh;
    /**
     * Table average velocity column in the table view.
     */
    @FXML
    private TableColumn<Traffic, Integer> tblTrafficAverageVel;
    /**
     * The info message text area.
     */
    @FXML
    private Text txtMesssages;
    /**
     * The binary tree text area.
     */
    @FXML
    private TextArea txtBinaryTree;

    /**
     * The office server used for establishing a socket to clients.
     */
    private OfficeServer server;

    /**
     * Display binary tree and text representation button.
     */
    @FXML
    private Button btnPreOrderDisplay;

    /**
     * Save binary tree hash map into TXT file button.
     */
    @FXML
    private Button btnPreOrderSave;

    /**
     * Display binary tree and text representation button.
     */
    @FXML
    private Button btnInOrderDisplay;

    /**
     * Save binary tree hash map into TXT file button.
     */
    @FXML
    private Button btnInOrderSave;

    /**
     * Display binary tree and text representation button.
     */
    @FXML
    private Button btnPostOrderDisplay;

    /**
     * Save binary tree hash map into TXT file button.
     */
    @FXML
    private Button btnPostOrderSave;

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
     * Grabs the current window position when mouse button is pressed.
     *
     * @param event The mouse information in the form of an event.
     */
    @FXML
    public final void press(final MouseEvent event) {
        windowX = event.getSceneX();
        windowY = event.getSceneY();
    }

    /**
     * Closes the program once the X icon is clicked.
     *
     * @param event The mouse information in the form of an event.
     */
    @FXML
    public final void programClose(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    @Override
    public final void initialize(final URL url, final ResourceBundle rb) {
        setupTable();
        serverConfig = new ConnectionConfig();
    }

    /**
     * Displays the binary tree as a new GUI frame.
     *
     * @param event The mouse information in the form of an event.
     */
    @FXML
    public final void displayBinaryTree(final MouseEvent event) {
        BinaryTreeView tree = new BinaryTreeView();

        tree.include(tree.getRootNode());
        tree.run();
    }

//<editor-fold defaultstate="collapsed" desc="Sorting Buttons">
    /**
     * Sorts the Table by location using the Bubble sort Method.
     *
     * @param event The button information in the form of an event.
     */
    @FXML
    public final void sortByLocation(final ActionEvent event) {
        ArrayList<Traffic> tableList = new ArrayList<>(tblView.getItems());
        ArrayList<Traffic> sortedList;
        sortedList = new SortingTools().sortAlgorithm(
                SortingType.BubbleSort, tableList);
        tblView.getItems().clear();

        sortedList.stream()
                .filter(
                        (traffic) -> (traffic instanceof Traffic))
                .forEachOrdered((traffic) -> {
                    tblView.getItems().add((Traffic) traffic);
                });
        DList<Integer> doublelyLinkedList
                = new DList<>(tblView.getItems().get(0).getLocation());
        tblView.getItems().subList(1, tblView.getItems().size())
                .forEach((traffic) -> {
                    doublelyLinkedList.getHead().appendNode(
                            new DLNode(traffic.getLocation()));
                });
        txtLinkedList.setText(doublelyLinkedList.toString());
    }

    /**
     * Sorts the table by the amount of vehicles using the Insertion method.
     *
     * @param event The button information in the form of an event.
     */
    @FXML
    public final void sortByVehicle(final ActionEvent event) {
        ArrayList<Traffic> tableList = new ArrayList<>(tblView.getItems());
        ArrayList<Traffic> sortedList;
        sortedList = new SortingTools().sortAlgorithm(
                SortingType.Insertion, tableList);
        tblView.getItems().clear();

        sortedList.stream()
                .filter(
                        (traffic) -> (traffic instanceof Traffic))
                .forEachOrdered((traffic) -> {
                    tblView.getItems().add((Traffic) traffic);
                });
        DList<Integer> doublelyLinkedList
                = new DList<>(tblView.getItems().get(0).getLocation());
        tblView.getItems().subList(1, tblView.getItems().size())
                .forEach((traffic) -> {
                    doublelyLinkedList.getHead().appendNode(
                            new DLNode(traffic.getTotalVehicle()));
                });
        txtLinkedList.setText(doublelyLinkedList.toString());

    }

    /**
     * Sorts the table by the average velocity using the Quick Sort method.
     *
     * @param event The button information in the form of an event.
     */
    @FXML
    public final void sortByVelocity(final ActionEvent event) {
        ArrayList<Traffic> tableList = new ArrayList<>(tblView.getItems());
        ArrayList<Traffic> sortedList;
        sortedList = new SortingTools().sortAlgorithm(
                SortingType.QuickSort, tableList);
        tblView.getItems().clear();

        sortedList.stream()
                .filter(
                        (traffic) -> (traffic instanceof Traffic))
                .forEachOrdered((traffic) -> {
                    tblView.getItems().add((Traffic) traffic);
                });
        DList<Integer> doublelyLinkedList
                = new DList<>(tblView.getItems().get(0).getLocation());
        tblView.getItems().subList(1, tblView.getItems().size())
                .forEach((traffic) -> {
                    doublelyLinkedList.getHead().appendNode(
                            new DLNode(traffic.getAverageVelocity()));
                });
        txtLinkedList.setText(doublelyLinkedList.toString());

    }
//</editor-fold>

    /**
     * Initalises the server using the Connection Configuration.
     */
    @FXML
    public final void startServer() {
        server = new OfficeServer(serverConfig, this);
        Thread thread = new Thread(server, "thread");
        thread.start();
    }

    /**
     * Stops the instance of the server thread.
     */
    @FXML
    public final void stopServer() {
        server.stop();
    }

//<editor-fold defaultstate="collasped" desc="Getters and Setters">
    /**
     * Returns the Text input component of the linked list.
     *
     * @return The TextArea for the linked list display.
     */
    public final TextArea getTxtLinkedList() {
        return txtLinkedList;
    }

    /**
     * Sets the text input component of the linked list.
     *
     * @param txtLinkedListInput The TextArea for the linked list display.
     */
    public final void setTxtLinkedList(final TextArea txtLinkedListInput) {
        this.txtLinkedList = txtLinkedListInput;
    }

    /**
     * Returns the menu bar instance.
     *
     * @return The top bar of the screen.
     */
    public final MenuBar getWindowMenuBar() {
        return windowMenuBar;
    }

    /**
     * Sets the menu bar instance.
     *
     * @param windowMenuBarInput The top bar of the screen.
     */
    public final void setWindowMenuBar(final MenuBar windowMenuBarInput) {
        this.windowMenuBar = windowMenuBarInput;
    }

    /**
     * Returns the File menu found on the MenuBar.
     *
     * @return The File menu.
     */
    public final Menu getFileMenu() {
        return fileMenu;
    }

    /**
     * Sets the file menu instance.
     *
     * @param fileMenuInput The Menu item for the file section of the menu bar.
     */
    public final void setFileMenu(final Menu fileMenuInput) {
        this.fileMenu = fileMenuInput;
    }

    /**
     * Returns the Edit menu found on the MenuBar.
     *
     * @return The Edit menu.
     */
    public final Menu getEditMenu() {
        return editMenu;
    }

    /**
     * Sets the Edit menu instance.
     *
     * @param editMenuInput The Menu item for the Edit section of the menu bar.
     */
    public final void setEditMenu(final Menu editMenuInput) {
        this.editMenu = editMenuInput;
    }

    /**
     * Returns the Help menu found on the MenuBar.
     *
     * @return The Help Menu.
     */
    public final Menu getHelpMenu() {
        return helpMenu;
    }

    /**
     * Sets the Help menu instance.
     *
     * @param helpMenuInput The Menu item for the Help section of the menu bar.
     */
    public final void setHelpMenu(final Menu helpMenuInput) {
        this.helpMenu = helpMenuInput;
    }

    /**
     * The Font Awesome Icon for the minimize button.
     *
     * @return The minimize button.
     */
    public final FontAwesomeIconView getMinimiseIcon() {
        return minimiseIcon;
    }

    /**
     * Sets the Font Awesome Icon for the minimize button.
     *
     * @param minimiseIconInput The minimize button.
     */
    public final void setMinimiseIcon(
            final FontAwesomeIconView minimiseIconInput) {
        this.minimiseIcon = minimiseIconInput;
    }

    /**
     * Returns the Font Awesome resize icon button.
     *
     * @return The resize button.
     */
    public final FontAwesomeIconView getResizeIcon() {
        return resizeIcon;
    }

    /**
     * Sets the Font Awesome resize icon button.
     *
     * @param resizeIconInput The resize button.
     */
    public final void setResizeIcon(final FontAwesomeIconView resizeIconInput) {
        this.resizeIcon = resizeIconInput;
    }

    /**
     * Returns the Font Awesome Close icon button.
     *
     * @return The close button.
     */
    public final FontAwesomeIconView getCloseIcon() {
        return closeIcon;
    }

    /**
     * Sets the Font Awesome Close icon button.
     *
     * @param closeIconInput The close button.
     */
    public final void setCloseIcon(final FontAwesomeIconView closeIconInput) {
        this.closeIcon = closeIconInput;
    }

    /**
     * Gets the server start Item SubMenu.
     *
     * @return The server start menu item.
     */
    public final MenuItem getmItemStartServer() {
        return mItemStartServer;
    }

    /**
     * Sets the Start Server Menu Item instance.
     *
     * @param mItemStartServerInput The server start menu item.
     */
    public final void setmItemStartServer(
            final MenuItem mItemStartServerInput) {
        this.mItemStartServer = mItemStartServerInput;
    }

    /**
     * Gets the Server Options item SubMenu.
     *
     * @return The server options menu item.
     */
    public final MenuItem getmItemServerOptions() {
        return mItemServerOptions;
    }

    /**
     * Sets the Server Options Menu Item instance.
     *
     * @param mItemServerOptionsInput The server options menu item.
     */
    public final void setmItemServerOptions(
            final MenuItem mItemServerOptionsInput) {
        this.mItemServerOptions = mItemServerOptionsInput;
    }

//</editor-fold>
    /**
     * Initializes the Table Columns.
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

    /**
     * The action performed from a click on a Menu Item instance.
     *
     * @param actionEvent The action event for the menu item.
     */
    @FXML
    public final void performAction(final ActionEvent actionEvent) {
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
            importTraffic(actionEvent, true);
        }
        if (target.getId().equals("exportMenu")) {
            exportTraffic(actionEvent);
        }
    }

    /**
     * Opens the settings dialog to edit the configuration.
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
//<editor-fold defaultstate="collapsed" desc="Table Import and Export">

    /**
     * Imports the selected CSV file into the table.
     *
     * @param event The action event of the Menu item.
     * @param clearTable If the table should be cleared once import has started.
     */
    private void importTraffic(final ActionEvent event,
            final boolean clearTable) {
        System.out.println("Importing File");
        String filePath = openFileDialog(event);
        if (clearTable) {
            tblView.getItems().clear();
        }
        try {
            for (Traffic traffic
                    : new Traffic()
                            .parseTrafficLines(
                                    FileIO.readFile(filePath))) {
                tblView.getItems().add(traffic);
            }
            printToMessageScreen("Imported Files from " + filePath);
        } catch (IOException ex) {
            Logger.getLogger(
                    ITTownMonitorOfficeController.class.getName()
            ).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Exports the table information into a selected CSV file.
     *
     * @param event The action event of the menu item.
     */
    public final void exportTraffic(final ActionEvent event) {
        String exportFilePath = saveFileDialog(event);
        ArrayList<String> exportList = new ArrayList<>();
        tblView.getItems().forEach((traffic) -> {
            exportList.add(traffic.toString());
        });
        try {
            FileIO.writeFile(exportFilePath, true, exportList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Opens the file dialog for selection for CSV files.
     *
     * @param event Action event of the menu item.
     * @return The string representation of the absolute file path to the
     * specified CSV file.
     */
    private String openFileDialog(final ActionEvent event) {
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

    /**
     * Opens the save file dialog for a specific CSV file.
     *
     * @param event The action event of the Menu Item.
     * @return The string representation of the absolute file path selected for
     * saving the CSV file.
     */
    private String saveFileDialog(final ActionEvent event) {
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

    /**
     * Opens the save file dialog for a specific TXT file.
     *
     * @param event The action event of the Button.
     * @return The string representation for the absolute file path selected for
     * saving the TXT file.
     */
    private String saveFileDialogButton(final ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save TXT File");
        fc.getExtensionFilters().add(
                new ExtensionFilter(
                        "Text File Document", "*.txt"));
        File file = fc.showSaveDialog(
                ((Node) event.getTarget()).getScene().getWindow());
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }

    /**
     * Imports a single traffic report into the table.
     * @param importTraffic The Traffic instance object.
     */
    public final void trafficImport(final Traffic importTraffic) {
        tblView.getItems().add(importTraffic);
    }
//</editor-fold>

    /**
     * Collects the client status of all monitoring office clients.
     * @param event The action event of the button.
     */
    @FXML
    public final void checkClientStatus(final ActionEvent event) {
        server.statusCheck();
    }

    /**
     * Prints the specified information into the message board text area.
     * @param string The value to show to the user.
     */
    public final void printToMessageScreen(final String string) {
        txtMesssages.setText(string);
    }

    /**
     * Displays the Binary Tree GUI frame and text representation of the
     * Pre Order sort.
     * @param event The action event of the button.
     */
    @FXML
    public final void preOrderDisplay(final ActionEvent event) {
        //Grab Vechicle Number
        BinaryTree bTree = new BinaryTree();
        tblView.getItems().forEach((obj) -> {
            bTree.addNode(obj.getTotalVehicle());
        });

        BinaryTreeView view = new BinaryTreeView();
        view.include(bTree.getRootNode());
        view.run();

        txtBinaryTree.setText(bTree.getPreOrder());
    }

    /**
     * Saves the hash map data of the Pre Order sort from the binary tree into
     * a selected text file.
     * @param event The action event of a button.
     */
    @FXML
    public final void preOrderSave(final ActionEvent event) {
        try {
            BinaryTree bTree = new BinaryTree();
            tblView.getItems().forEach((obj) -> {
                bTree.addNode(obj.getTotalVehicle());
            });
            ArrayList<String> output = new ArrayList<>();
            output.add(new Hash().toHashMap(bTree.getPreOrderArrayList())
                    .toString());
            FileIO.writeFile(saveFileDialogButton(event), Boolean.TRUE, output);
        } catch (IOException e) {
            System.out.println("Error:" + e.toString());
        }
    }

    /**
     * Displays the Binary Tree GUI frame and text representation of the
     * In Order sort.
     * @param event The action event of the button.
     */
    @FXML
    public final void inOrderDisplay(final ActionEvent event) {
        //Grab Vechicle Number
        BinaryTree bTree = new BinaryTree();
        tblView.getItems().forEach((obj) -> {
            bTree.addNode(obj.getTotalVehicle());
        });

        BinaryTreeView view = new BinaryTreeView();
        view.include(bTree.getRootNode());
        view.run();

        txtBinaryTree.setText(bTree.getInOrder());

    }

    /**
     * Saves the hash map data of the In Order sort from the binary tree into
     * a selected text file.
     * @param event The action event of a button.
     */
    @FXML
    public final void inOrderSave(final ActionEvent event) {
        try {
            BinaryTree bTree = new BinaryTree();
            tblView.getItems().forEach((obj) -> {
                bTree.addNode(obj.getTotalVehicle());
            });
            ArrayList<String> output = new ArrayList<>();
            output.add(new Hash().toHashMap(bTree.getInOrderArrayList())
                    .toString());
            FileIO.writeFile(saveFileDialogButton(event), Boolean.TRUE, output);
        } catch (IOException e) {
            System.out.println("Error:" + e.toString());
        }
    }

    /**
     * Displays the Binary Tree GUI frame and text representation of the
     * Post Order sort.
     * @param event The action event of the button.
     */
    @FXML
    public final void postOrderDisplay(final ActionEvent event) {
        //Grab Vechicle Number
        BinaryTree bTree = new BinaryTree();
        tblView.getItems().forEach((obj) -> {
            bTree.addNode(obj.getTotalVehicle());
        });

        BinaryTreeView view = new BinaryTreeView();
        view.include(bTree.getRootNode());
        view.run();

        txtBinaryTree.setText(bTree.getPostOrder());

    }

    /**
     * Saves the hash map data of the Post Order sort from the binary tree into
     * a selected text file.
     * @param event The action event of a button.
     */
    @FXML
    public final void postOrderSave(final ActionEvent event) {
        try {
            BinaryTree bTree = new BinaryTree();
            tblView.getItems().forEach((obj) -> {
                bTree.addNode(obj.getTotalVehicle());
            });
            ArrayList<String> output = new ArrayList<>();
            output.add(new Hash().toHashMap(bTree.getPostOrderArrayList())
                    .toString());
            FileIO.writeFile(saveFileDialogButton(event), Boolean.TRUE, output);
        } catch (IOException e) {
            System.out.println("Error:" + e.toString());
        }
    }
}
