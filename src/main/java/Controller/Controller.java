/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author needf
 */
public class Controller implements ActionListener {

    private MainFrame frame;

    public Controller(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Open file":
                openFiles();
                break;

            case "Save file":

                break;

            case "New Invoice":

                break;

            case "Delete Invoice":

                break;

            case "New Item":

                break;

            case "Delete Item":

                break;

            default:
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
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }

    }
}
