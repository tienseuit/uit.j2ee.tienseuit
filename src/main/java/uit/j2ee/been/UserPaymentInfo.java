package uit.j2ee.been;
// Generated May 30, 2017 11:41:13 AM by Hibernate Tools 4.3.1



/**
 * UserPaymentInfo generated by hbm2java
 */
public class UserPaymentInfo  implements java.io.Serializable {


     private Integer id;
     private int userId;
     private String pin;
     private long balance;

    public UserPaymentInfo() {
    }

    public UserPaymentInfo(int userId, String pin, long balance) {
       this.userId = userId;
       this.pin = pin;
       this.balance = balance;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPin() {
        return this.pin;
    }
    
    public void setPin(String pin) {
        this.pin = pin;
    }
    public long getBalance() {
        return this.balance;
    }
    
    public void setBalance(long balance) {
        this.balance = balance;
    }




}


