package uit.j2ee.been;
// Generated May 30, 2017 11:41:13 AM by Hibernate Tools 4.3.1



/**
 * UserType generated by hbm2java
 */
public class UserType  implements java.io.Serializable {


     private int id;
     private String name;
     private String description;

    public UserType() {
    }

	
    public UserType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public UserType(int id, String name, String description) {
       this.id = id;
       this.name = name;
       this.description = description;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


