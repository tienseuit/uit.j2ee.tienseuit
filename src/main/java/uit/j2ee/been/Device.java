package uit.j2ee.been;
// Generated May 30, 2017 11:41:13 AM by Hibernate Tools 4.3.1



/**
 * Device generated by hbm2java
 */
public class Device  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String mac;
     private String address;
     private int shopId;

    public Device() {
    }

	
    public Device(String name, String mac, int shopId) {
        this.name = name;
        this.mac = mac;
        this.shopId = shopId;
    }
    public Device(String name, String mac, String address, int shopId) {
       this.name = name;
       this.mac = mac;
       this.address = address;
       this.shopId = shopId;
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
    public String getMac() {
        return this.mac;
    }
    
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public int getShopId() {
        return this.shopId;
    }
    
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }




}


