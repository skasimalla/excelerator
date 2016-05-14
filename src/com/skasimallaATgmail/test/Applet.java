/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.test;

import com.skasimallaATgmail.test.GUI;
import java.awt.Container;
import java.io.PrintStream;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class Applet
extends JApplet {
    private static final long serialVersionUID = 1;

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable(){

                public void run() {
                    Applet.this.createGUI();
                }
            });
        }
        catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    private void createGUI() {
        GUI newContentPane = new GUI();
        newContentPane.setOpaque(true);
        this.setContentPane(newContentPane);
    }

}

