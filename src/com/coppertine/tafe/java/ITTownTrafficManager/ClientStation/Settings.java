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
package com.coppertine.tafe.java.ITTownTrafficManager.ClientStation;

import com.coppertine.tafe.java.Declarator;
import com.coppertine.tafe.java.Vector2;
import com.coppertine.tafe.java.ITTownTrafficManager.Connection.ConnectionConfig;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
/**
 *
 * @author Coppertine
 */
public class Settings {

    /**
     *
     */
    private JSpinner spinPortNumber;
    /** File Path Text Field. **/
    private JTextField txtAddress;
    /**
     *
     * @param inputConfig input configuration.
     * @return <code>Config</code> from settings screen.
     */
    public final ConnectionConfig open(final ConnectionConfig inputConfig) {
        // Set up pannel //

        final int portMax = 65535;
        final int portMin = 1;
        
        GridLayout layout = new GridLayout(0, 2);
        JPanel panel = new JPanel(layout);
        
         // //
         panel.add(new JLabel("Address"));
        txtAddress = new JTextField(inputConfig.getHostURL());
        panel.add(txtAddress);
        
        spinPortNumber = generateSpinner(panel, new Declarator(
                new SpinnerNumberModel(
                        portMin, //Initial Input
                        0,
                        portMax,
                        1 //Step
                ), "Port Number", new Vector2(0, 0), 0, 0));

        final int result = JOptionPane.showConfirmDialog(
                        null, panel, "Settings", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return new ConnectionConfig(
                    txtAddress.getText(),
                    (int) spinPortNumber.getValue());
        } else {
            return inputConfig;     
        }
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