/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.test;

import com.skasimallaATgmail.test.Reports;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GUI
extends JPanel
implements ActionListener {
    private static final long serialVersionUID = 1;
    private static final Component frame = null;
    private JButton jButton1 = new JButton("Enter");
    private JButton jButton2 = new JButton("Add New DB");
    private JButton jButton3 = new JButton("Add New Report");
    private JButton jButton4 = new JButton("Next Step");
    private JButton jButton5 = new JButton("Settings");
    private JLabel jLabel1 = new JLabel("Report Number:");
    private JLabel jLabel2 = new JLabel("Low:");
    private JLabel jLabel3 = new JLabel("High:");
    private JLabel jLabel4 = new JLabel("Underflow:");
    private JLabel jLabel5 = new JLabel("Output:");
    private JLabel jLabel6 = new JLabel("Output:");
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTable jTable1 = new JTable();
    private JTextArea jTextArea1 = new JTextArea();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();
    private JTextField jTextField4 = new JTextField();
    private JTextField jTextField5 = new JTextField();
    Reports ac = new Reports();
    boolean encoding;
    boolean drawInterval = false;

    public GUI() {
        this.jTable1.setModel(new DefaultTableModel(new Object[0][], new String[]{"Num", "Report Name"}));
        this.jScrollPane2.setViewportView(this.jTable1);
        this.jTextArea1.setColumns(20);
        this.jTextArea1.setRows(5);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jScrollPane2, -2, 145, -2).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField4, -2, 1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextField5).addGroup(layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField3, -1, 128, 32767)).addComponent(this.jButton4, -1, -1, 32767).addComponent(this.jButton3, -1, -1, 32767).addComponent(this.jButton2, -1, -1, 32767).addComponent(this.jButton5, -1, -1, 32767)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jScrollPane2, -2, 180, -2).addGroup(layout.createSequentialGroup().addComponent(this.jButton2).addGap(18, 18, 18).addComponent(this.jButton3).addGap(18, 18, 18).addComponent(this.jButton5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton4))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jTextField2, -2, -1, -2).addComponent(this.jLabel3).addComponent(this.jTextField3, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.jTextField4, -2, -1, -2).addComponent(this.jTextField5, -2, -1, -2).addComponent(this.jLabel6)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGap(7, 7, 7).addContainerGap()));
        this.jTextArea1.setLineWrap(true);
        this.jTextArea1.setWrapStyleWord(true);
        this.jButton2.setEnabled(true);
        this.jButton3.setEnabled(true);
        this.jButton4.setEnabled(false);
        this.jButton5.setEnabled(true);
        this.jTextField2.setEditable(false);
        this.jTextField3.setEditable(false);
        this.jTextField4.setEditable(false);
        this.jTextField5.setEditable(false);
        this.jTable1.setEnabled(false);
        this.jTextArea1.setEditable(false);
        this.jButton1.addActionListener(this);
        this.jButton2.addActionListener(this);
        this.jButton3.addActionListener(this);
        this.jButton4.addActionListener(this);
        this.jButton5.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jButton1) {
            this.ac.enterMessage(this.jTextField1.getText(), this.jTextArea1);
            this.updateTable();
            if (this.jTextField1.getText().length() != 0) {
                this.jButton1.setEnabled(false);
                this.jButton2.setEnabled(true);
                this.jButton5.setEnabled(true);
                this.jTextField1.setEditable(false);
            } else {
                this.jButton1.setEnabled(true);
                JOptionPane.showMessageDialog(frame, "Please INPUT Something..");
            }
        } else if (e.getSource() == this.jButton2) {
            this.ac.encode(this.jTextArea1);
            this.ac.customProbabilities(this.jTable1, this.jTextArea1);
            this.updateTable();
            this.jTextField2.setText(String.valueOf(this.ac.bitString(this.ac.low)) + " (" + Integer.toString(this.ac.low) + ")");
            this.jTextField3.setText(String.valueOf(this.ac.bitString(this.ac.high)) + " (" + Integer.toString(this.ac.high) + ")");
            this.jTextField4.setText(Integer.toString(this.ac.underflow_bits));
            this.jTextField5.setText(this.ac.outputString());
            this.encoding = true;
            this.drawInterval = true;
            this.jButton2.setEnabled(false);
            this.jButton4.setEnabled(true);
            this.jTable1.setEnabled(false);
        } else if (e.getSource() == this.jButton3) {
            this.ac.decode(this.jTextArea1);
            this.jTextField1.setText(this.ac.outputString());
            this.jTextField2.setText(String.valueOf(this.ac.bitString(this.ac.low)) + " (" + Integer.toString(this.ac.low) + ")");
            this.jTextField3.setText(String.valueOf(this.ac.bitString(this.ac.high)) + " (" + Integer.toString(this.ac.high) + ")");
            this.jTextField4.setText(Integer.toString(this.ac.underflow_bits));
            this.jTextField5.setText(this.ac.decoded_msg);
            this.encoding = false;
            this.jButton3.setEnabled(false);
            this.jButton4.setEnabled(true);
        } else if (e.getSource() == this.jButton4) {
            if (this.encoding) {
                this.ac.encodeStep(this.jTextArea1);
                this.jTextField5.setText(this.ac.outputString());
            } else {
                this.ac.decodeStep(this.jTextArea1);
                this.jTextField5.setText(this.ac.decoded_msg);
            }
            this.jTextField2.setText(String.valueOf(this.ac.bitString(this.ac.low)) + " (" + Integer.toString(this.ac.low) + ")");
            this.jTextField3.setText(String.valueOf(this.ac.bitString(this.ac.high)) + " (" + Integer.toString(this.ac.high) + ")");
            this.jTextField4.setText(Integer.toString(this.ac.underflow_bits));
            if (this.ac.step >= this.ac.message.length()) {
                if (this.encoding) {
                    this.jButton3.setEnabled(true);
                    this.jButton4.setEnabled(false);
                } else {
                    this.jButton4.setEnabled(false);
                }
            }
        } else if (e.getSource() == this.jButton5) {
            this.jTextField1.setText("");
            this.jTextField2.setText("");
            this.jTextField3.setText("");
            this.jTextField4.setText("");
            this.jTextField5.setText("");
            this.jTable1.setModel(new DefaultTableModel(new Object[0][], new String[]{"Num", "Report Name"}));
            this.jButton1.setEnabled(true);
            this.jButton2.setEnabled(false);
            this.jButton3.setEnabled(false);
            this.jButton4.setEnabled(false);
            this.jButton5.setEnabled(false);
            this.jTextField1.setEditable(true);
            this.jTable1.setEnabled(false);
            this.drawInterval = false;
        }
    }

    void updateTable() {
        Object[][] cells = new Object[3][2];
        cells[1][0] = "1";
        cells[1][1] = "Report 1";
        cells[1][0] = "2";
        cells[1][1] = "Report 2";
        cells[1][0] = "3";
        cells[1][1] = "Report 3";
        this.jTable1.setModel(new DefaultTableModel(cells, new String[]{"Num", "Report Name"}));
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple DB Utility- Samuel Kasimalla");
        frame.setDefaultCloseOperation(3);
        GUI newContentPane = new GUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){

            public void run() {
                GUI.createAndShowGUI();
            }
        });
    }

}

