package uit.j2ee.been;
// Generated May 30, 2017 11:41:13 AM by Hibernate Tools 4.3.1



/**
 * Shop generated by hbm2java
 */
public class Shop  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String address;
     private int userId;

    public Shop() {
    }

	
    public Shop(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }
    public Shop(String name, String address, int userId) {
       this.name = name;
       this.address = address;
       this.userId = userId;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }




}


