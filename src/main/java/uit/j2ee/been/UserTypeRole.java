package uit.j2ee.been;
// Generated May 30, 2017 11:41:13 AM by Hibernate Tools 4.3.1



/**
 * UserTypeRole generated by hbm2java
 */
public class UserTypeRole  implements java.io.Serializable {


     private Integer id;
     private String function;
     private Integer userTypeId;
     private boolean accept;

    public UserTypeRole() {
    }

	
    public UserTypeRole(String function, boolean accept) {
        this.function = function;
        this.accept = accept;
    }
    public UserTypeRole(String function, Integer userTypeId, boolean accept) {
       this.function = function;
       this.userTypeId = userTypeId;
       this.accept = accept;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFunction() {
        return this.function;
    }
    
    public void setFunction(String function) {
        this.function = function;
    }
    public Integer getUserTypeId() {
        return this.userTypeId;
    }
    
    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }
    public boolean isAccept() {
        return this.accept;
    }
    
    public void setAccept(boolean accept) {
        this.accept = accept;
    }




}

