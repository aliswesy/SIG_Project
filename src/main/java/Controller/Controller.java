/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InvocieHeaderTable;
import Model.InvocieLineTable;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import View.HeaderDialog;
import View.LineDialog;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author needf
 */
public class Controller implements ActionListener, ListSelectionListener {

    private MainFrame frame;
    private HeaderDialog headerDialog;
    private LineDialog lineDialog;

    public Controller(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Open file" ->
                openFiles();

            case "Save file" ->
                saveFiles();

            case "New Invoice" ->
                createNewInvoice();

            case "Delete Invoice" ->
                deleteInvocie();

            case "New Item" ->
                createNewLine();

            case "Delete Item" ->
                deleteLine();

            case "invoiceOK" ->
                addNewInvoice();

            case "invoiceCancel" ->
                cancelInvoice();

            case "lineOK" ->
                addNewLine();

            case "lineCancel" ->
                cancelLine();

            default ->
                throw new AssertionError();
        }

    }

    //Use this to load 2 files (header and lines)...
    public void openFiles() {
        JFileChooser fc = new JFileChooser();

        try {
            //Open 1st file from pc...
            int res = fc.showOpenDialog(frame);
            if (res == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headLines = Files.readAllLines(headerPath);

                //Read lines from Header file...
                ArrayList<InvoiceHeader> invoices = new ArrayList<>();
                for (int i = 0; i < headLines.size(); i++) {
                    String get = headLines.get(i);
                    String[] headUnits = get.split(",");
                    int invoiceNumber = Integer.parseInt(headUnits[0]);
                    String invoiceDate = headUnits[1];
                    String custName = headUnits[2];

                    InvoiceHeader invoice = new InvoiceHeader(invoiceNumber, invoiceDate, custName);
                    invoices.add(invoice);
                }
                //End Read lines from header...
                //End open 1st file from pc...

                //Open 2nd fie from pc...
                int res2 = fc.showOpenDialog(frame);
                if (res2 == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lines = Files.readAllLines(linePath);

                    //Read lines from incoive lines...
                    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
                    for (String line : lines) {
                        String[] lineUnits = line.split(",");
                        int lineNumber = Integer.parseInt(lineUnits[0]);
                        String itemName = lineUnits[1];
                        float itemPrice = Float.parseFloat(lineUnits[2]);
                        int count = Integer.parseInt(lineUnits[3]);

                        InvoiceLine invoiceLine = new InvoiceLine(lineNumber, itemName, itemPrice, count);
                        invoiceLines.add(invoiceLine);
                    }
                    //End Read lines from invocieines...
                    //End open 2st file from pc...

                    //Assign each order line to it's invoice header...
                    for (InvoiceHeader invoice : invoices) {
                        ArrayList<InvoiceLine> match = new ArrayList<>();
                        for (InvoiceLine invoiceLine : invoiceLines) {
                            if (invoice.getInvoiceNumber() == invoiceLine.getInvoiceNum()) {
                                match.add(invoiceLine);
                            }
                        }
                        invoice.setInvLine(match);
                    }
                }
                //send the invoice headers to the main frame...
                frame.setInvoices(invoices);
                InvocieHeaderTable headerTable = new InvocieHeaderTable(invoices);
                frame.setHeaderTable(headerTable);
                frame.getTblInvoiceHeader().setModel(headerTable);
                frame.getHeaderTable().fireTableDataChanged();

            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }

    }

    //User this to save 2 files (header and lines)...
    public void saveFiles() {
        ArrayList<InvoiceHeader> invoices = frame.getInvoices();
        String heads = "";
        String lines = "";

        for (InvoiceHeader invoice : invoices) {
            String invoiceCSV = invoice.getCSV();
            heads += invoiceCSV;

            for (InvoiceLine invoiceLine : invoice.getInvLine()) {
                String lineCSV = invoiceLine.getCSV();
                lines += lineCSV;
            }
        }

        //open save dialog...
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);

            //Save Header file...
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(heads);
                hfw.flush();
                hfw.close();

                //Save Line file...
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();

                }
            }
        } catch (Exception e) {

        }

    }

    /**
     * Invoice Header Methods.....
     */
    //Creating new invoice dialog...
    public void createNewInvoice() {
        headerDialog = new HeaderDialog(frame);
        headerDialog.setVisible(true);
    }

    //Close invoice dialog...
    public void cancelInvoice() {
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null;
    }

    //Add new invocie to invoice header table...
    public void addNewInvoice() {
        String date = headerDialog.getFldInvoiceDate().getText();
        String customer = headerDialog.getFldCustName().getText();
        int num = frame.getInvoiceNum() + 1;

        InvoiceHeader newInv = new InvoiceHeader(num, date, customer);
        frame.getInvoices().add(newInv);
        frame.getHeaderTable().fireTableDataChanged();
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null;
    }

    //Deleting selected invoice header...
    public void deleteInvocie() {
        int selecetedRow = frame.getTblInvoiceHeader().getSelectedRow();

        if (selecetedRow != -1) {
            frame.getInvoices().remove(selecetedRow);
            frame.getHeaderTable().fireTableDataChanged();

        }
    }

    /**
     * Invoice Line Methods...
     */
    //Creating new invoice line dialog...
    public void createNewLine() {
        lineDialog = new LineDialog(frame);
        lineDialog.setVisible(true);
    }

    //close invoice line dialog...
    public void cancelLine() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    //Add new invoice line to invoice line table...
    public void addNewLine() {
        String item = lineDialog.getFldItemName().getText();
        float itemPrice = Float.parseFloat(lineDialog.getFldItemPrice().getText());
        int count = Integer.parseInt(lineDialog.getFldItemCount().getText());
        int num = frame.getInvoiceNum();

        int selectedInvoice = frame.getTblInvoiceHeader().getSelectedRow();
        if (selectedInvoice != -1) {
            InvoiceHeader invoice = frame.getInvoices().get(selectedInvoice);
            InvoiceLine newline = new InvoiceLine(num, item, itemPrice, count);
            invoice.getInvLine().add(newline);
            InvocieLineTable invLineTable = (InvocieLineTable) frame.getTblInvoiceLines().getModel();
            invLineTable.fireTableDataChanged();
            frame.getHeaderTable().fireTableDataChanged();
        }

        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    //Deleting selected line from invoice...
    public void deleteLine() {
        int selecetedRow = frame.getTblInvoiceLines().getSelectedRow();

        if (selecetedRow != -1) {
            InvocieLineTable invoiceLineTable = (InvocieLineTable) frame.getTblInvoiceLines().getModel();
            invoiceLineTable.getLines().remove(selecetedRow);
            invoiceLineTable.fireTableDataChanged();
            frame.getHeaderTable().fireTableDataChanged();
        }
    }

    /**
     * On change for selected row
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getTblInvoiceHeader().getSelectedRow();

        if (selectedIndex != -1) {
            InvoiceHeader selectedInvoice = frame.getInvoices().get(selectedIndex);

            //Set the lables values...
            frame.getLbInvoiceNum().setText("" + selectedInvoice.getInvoiceNumber());
            frame.getLbInvoiceDate().setText(selectedInvoice.getInvoiceDate());
            frame.getLbCustName().setText(selectedInvoice.getCustomerName());
            frame.getLbInvocieTotal().setText("" + selectedInvoice.getTotalInvocie());

            //Set values for the invocie line table...
            InvocieLineTable invocieLine = new InvocieLineTable(selectedInvoice.getInvLine());
            frame.getTblInvoiceLines().setModel(invocieLine);
            invocieLine.fireTableDataChanged();
        }
    }
}
