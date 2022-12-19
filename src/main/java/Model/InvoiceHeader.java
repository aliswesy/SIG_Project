package Model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private int invoiceNumber;
    private Date invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> invLine;

    //Constractors start...
    public InvoiceHeader() {
    }

    public InvoiceHeader(int invoiceNumber, Date invoiceDate, String customerName, ArrayList<InvoiceLine> invLine) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.invLine = invLine;
    }
    //Constractors end...
    

    //Setter and Getters start...
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
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
}
