package uit.j2ee.been;
// Generated May 30, 2017 11:41:13 AM by Hibernate Tools 4.3.1



/**
 * BillDetail generated by hbm2java
 */
public class BillDetail  implements java.io.Serializable {


     private Integer id;
     private int billId;
     private int productId;
     private int quantity;
     private long subtotal;

    public BillDetail() {
    }

    public BillDetail(int billId, int productId, int quantity, long subtotal) {
       this.billId = billId;
       this.productId = productId;
       this.quantity = quantity;
       this.subtotal = subtotal;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getBillId() {
        return this.billId;
    }
    
    public void setBillId(int billId) {
        this.billId = billId;
    }
    public int getProductId() {
        return this.productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public long getSubtotal() {
        return this.subtotal;
    }
    
    public void setSubtotal(long subtotal) {
        this.subtotal = subtotal;
    }




}

