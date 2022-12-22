/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class LineDialog extends JDialog{
    private JTextField fldItemName;
    private JTextField fldItemCount;
    private JTextField fldItemPrice;
    private JLabel lblItemName;
    private JLabel lblItemCount;
    private JLabel lblItemPrice;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public LineDialog(MainFrame frame) {
        fldItemName = new JTextField(20);
        lblItemName = new JLabel("Item Name");
        
        fldItemCount = new JTextField(20);
        lblItemCount = new JLabel("Item Count");
        
        fldItemPrice = new JTextField(20);
        lblItemPrice = new JLabel("Item Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("lineOK");
        cancelBtn.setActionCommand("lineCancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(lblItemName);
        add(fldItemName);
        add(lblItemCount);
        add(fldItemCount);
        add(lblItemPrice);
        add(fldItemPrice);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getFldItemName() {
        return fldItemName;
    }

    public JTextField getFldItemCount() {
        return fldItemCount;
    }

    public JTextField getFldItemPrice() {
        return fldItemPrice;
    }
}
