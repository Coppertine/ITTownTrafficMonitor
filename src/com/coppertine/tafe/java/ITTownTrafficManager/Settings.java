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
package com.coppertine.tafe.java.ITTownTrafficManager;

import com.coppertine.tafe.java.Declarator;
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

/**
 *
 * @author Coppertine
 */
public class Settings {

    /**
     *
     */
    /**
     *
     * @param parent
     * @param inputConfig input configuration.
     * @return <code>Config</code> from settings screen.
     */
    public final ConnectionConfig open(final ConnectionConfig inputConfig) {
        // Set up pannel //

        final int PORT_MAX = 65535;
        final int PORT_MIN = 1;

        // Create Dialog
        Dialog<Pair<String, String>> dialog;
        dialog = new Dialog<>();

        dialog.setTitle("Settings");
        dialog.setHeaderText(null);

        //Button Types
        ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        //Gridpane time
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20, 150, 10, 10));

        TextField address = new TextField();
        address.setPromptText("Address");

        TextField portNumber = new TextField();

        //Checks if there is a pattern which includes commas
        Pattern pattern = Pattern.compile("\\d*|\\d+\\,\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        portNumber.setTextFormatter(formatter);
        portNumber.setPromptText("Port Number");

        portNumber.textProperty().addListener(new ChangeListener<String>() {
            /**
             * {@inheritDoc }
             */
            @Override
            public void changed(
                    final ObservableValue<? extends String> observable,
                    final String oldValue, final String newValue) {
                if (Integer.parseInt(portNumber.getText()) > PORT_MAX) {
                    portNumber.setText(oldValue);
                }
                if (Integer.parseInt(portNumber.getText()) < PORT_MIN) {
                    portNumber.setText(oldValue);
                }
            }
        });
        address.setText(inputConfig.getHostURL());
        portNumber.setText(String.valueOf(inputConfig.getHostPort()));

        pane.add(new Label("Address:"), 0, 0);
        pane.add(address, 1, 0);
        pane.add(new Label("Port Number:"), 0, 1);
        pane.add(portNumber, 1, 1);

        dialog.getDialogPane().setContent(pane);
        Platform.runLater(() -> address.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Pair<>(address.getText(),
                        portNumber.getText().replaceAll("/[\\,]/", ""));
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        ConnectionConfig config = inputConfig;
        if (result.isPresent()) {
            config = new ConnectionConfig(
                    result.get().getKey(),
                    Integer.parseInt(
                            result.get().getValue()));
        }
        return config;
    }

    /**
     *
     * @param panel <code>JPanel</code> to place the spinner object to.
     * @param object The <code>Declarator</code> object to pass through.
     * @return JSpinner
     */
    public final JSpinner generateSpinner(
            final JPanel panel, final Declarator object) {
        JLabel lbl = new JLabel(object.getName());
        JSpinner spinner = new JSpinner((SpinnerModel) object.getObj());
        panel.add(lbl);
        panel.add(spinner);
        return spinner;
    }
}
