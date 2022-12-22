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
public class InvocieLineTable extends AbstractTableModel{
    private ArrayList<InvoiceLine> lines;
    private String[] columnHead = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    public InvocieLineTable(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    
    
    @Override
    public int getRowCount() {
        if(lines != null)
            return lines.size();
        return 0;
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
        InvoiceLine line = lines.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return line.getInvoiceNum();
            }
                
            case 1 -> {
                return line.getItemName();
            }
                
            case 2 -> {
                return line.getItemPrice();
            }
                
            case 3 -> {
                return line.getCount();
            }
                
            case 4 -> {
                return line.getTotalPrice();
            }
                
            default -> throw new AssertionError();
        }
    }
    
}
