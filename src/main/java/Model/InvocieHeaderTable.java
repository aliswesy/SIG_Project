/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author needf
 */
public class InvocieHeaderTable extends AbstractTableModel {

    private ArrayList<InvoiceHeader> invoices;
    private String[] columnHead = {"No.", "Date", "Customer", "Total"};

    public InvocieHeaderTable(ArrayList<InvoiceHeader> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columnHead.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnHead[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoice = invoices.get(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return invoice.getInvoiceNumber();
            }
                
            case 1 -> {
                return invoice.getInvoiceDate();
            }
                
            case 2 -> {
                return invoice.getCustomerName();
            }
                
            case 3 -> {
                return invoice.getTotalInvocie();
            }
                
            default -> throw new AssertionError();
        }
    }

}
