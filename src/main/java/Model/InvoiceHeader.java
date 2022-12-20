package Model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private int invoiceNumber;
    private String invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> invLine;

    //Constractors start...
    public InvoiceHeader() {
    }

    public InvoiceHeader(int invoiceNumber, String invoiceDate, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }
    //Constractors end...
    

    //Setter and Getters start...
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getInvLine() {
        return invLine;
    }

    public void setInvLine(ArrayList<InvoiceLine> invLine) {
        this.invLine = invLine;
    }
    //Setter and Getters end...

    @Override
    public String toString() {
        return "InvoiceHeader{" + "invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate + ", customerName=" + customerName + ", invLine=" + invLine + '}';
    }
    
    public float getTotalInvocie(){
        float total = 0;
        for (InvoiceLine invoiceLine : invLine) {
            total += invoiceLine.getTotalPrice();
        }
        return total;
    }
}
