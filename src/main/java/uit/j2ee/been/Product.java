package uit.j2ee.been;
// Generated May 30, 2017 11:41:13 AM by Hibernate Tools 4.3.1



/**
 * Product generated by hbm2java
 */
public class Product  implements java.io.Serializable {


     private Integer id;
     private String name;
     private long price;
     private int quantity;
     private Boolean checkCount;
     private String shortDescription;
     private int shopId;
     private Boolean isPublic;
     private String image;

    public Product() {
    }

	
    public Product(String name, long price, int quantity, int shopId, String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shopId = shopId;
        this.image = image;
    }
    public Product(String name, long price, int quantity, Boolean checkCount, String shortDescription, int shopId, Boolean isPublic, String image) {
       this.name = name;
       this.price = price;
       this.quantity = quantity;
       this.checkCount = checkCount;
       this.shortDescription = shortDescription;
       this.shopId = shopId;
       this.isPublic = isPublic;
       this.image = image;
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
    public long getPrice() {
        return this.price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Boolean getCheckCount() {
        return this.checkCount;
    }
    
    public void setCheckCount(Boolean checkCount) {
        this.checkCount = checkCount;
    }
    public String getShortDescription() {
        return this.shortDescription;
    }
    
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public int getShopId() {
        return this.shopId;
    }
    
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
    public Boolean getIsPublic() {
        return this.isPublic;
    }
    
    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }




}


