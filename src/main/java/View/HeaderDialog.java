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
public class HeaderDialog extends JDialog {
    private JTextField fldCustName;
    private JTextField fldInvoiceDate;
    private JLabel lblCustName;
    private JLabel lblInvoiceDate;
    private JButton okBtn;
    private JButton cancelBtn;

    public HeaderDialog(MainFrame frame) {
        lblCustName = new JLabel("Customer Name:");
        fldCustName = new JTextField(20);
        lblInvoiceDate = new JLabel("Invoice Date:");
        fldInvoiceDate = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("invoiceOK");
        cancelBtn.setActionCommand("invoiceCancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(lblInvoiceDate);
        add(fldInvoiceDate);
        add(lblCustName);
        add(fldCustName);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getFldCustName() {
        return fldCustName;
    }

    public JTextField getFldInvoiceDate() {
        return fldInvoiceDate;
    }
    
}
