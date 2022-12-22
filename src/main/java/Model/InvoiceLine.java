package Model;

public class InvoiceLine {

    private int invoiceNum;
    private String itemName;
    private float itemPrice;
    private int count;

    //Constractors start...
    public InvoiceLine() {
    }

    public InvoiceLine( int invoiceNum, String itemName, float itemPrice, int count) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoiceNum = invoiceNum;
    }
    // Constracotrs end...

    //Setter and Getters start...
    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    //Setter and Getters end...

    @Override
    public String toString() {
        return "InvoiceLine{" + "invoiceNum=" + invoiceNum + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", count=" + count + '}';
    }
    
    public float getTotalPrice(){
        return itemPrice * count;
    }
    
        public String getCSV(){
        return invoiceNum + "," + itemName + "," + itemPrice + "," + count + "\n";
    }
}
