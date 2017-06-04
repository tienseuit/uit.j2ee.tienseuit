package uit.j2ee.model;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.criterion.Example;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.User;
import uit.j2ee.core.Business;
import uit.j2ee.core.DBUtil;
import uit.j2ee.util.NumberUtil;

public class UserBUS extends Business<User> {

    public UserBUS() {
        super(User.class);
    }

    public BUSRespone Login(String email, String password) throws Exception{
        try {
            email = email.toLowerCase();            
            Object re = LoginDAO(email, password);
            if (re == null) {
                return new BUSRespone(null, BUSStatus.busFailed());
            } else { 
                _Request.getSession().setAttribute("user", re);
                _Request.getSession().setAttribute("userID", ((User)re).getId());
                _Request.getSession().setAttribute("userTypeID", ((User)re).getUserTypeId());
                return new BUSRespone("login success");
            }
        } catch (Exception ex) {
            return new BUSRespone(null, BUSStatus.exceptiond(), ex);
        }
    }

    public BUSRespone InsertUser(String jsonData) {

        User data = jsonToBeen(jsonData);
        if (data == null) 
            return new BUSRespone(null, BUSStatus.jsonFailedd());
        
        data.setEmail(data.getEmail().toLowerCase());

        try {
            User re = insert(data);
            
                      _Request.getSession().setAttribute("user", re);
                _Request.getSession().setAttribute("userID", ((User)re).getId());
                _Request.getSession().setAttribute("userTypeID", ((User)re).getUserTypeId());
            
            return new BUSRespone(re);
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
        }
    }

    public BUSRespone UpdateUser(String jsonData) {
        return updateWithJsonData(jsonData);
    }

    protected User LoginDAO(String email, String password) {
        Session session = DBUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(_BeenClass);
            User loginInfo = new User();
            loginInfo.setEmail(email);
            loginInfo.setPassword(password);
            criteria.add(Example.create(loginInfo));

            Object re = criteria.uniqueResult();
            return re == null ? null : (User)re;
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
    }
     
    public BUSRespone DevLogin(String userID) throws Exception{
        
        User userInfo = null;
        Session session = DBUtil.getSessionFactory().openSession();
        try {
        Integer id = Integer.valueOf(userID);
        
            Object re  = session.get(_BeenClass, id);
            userInfo = re == null ? null : (User)re;
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
         
        try {      
            
            if (userInfo == null) {
                return new BUSRespone(null, BUSStatus.busFailed());
            } else { 
                _Request.getSession().setAttribute("user", userInfo);
                _Request.getSession().setAttribute("userID", ((User)userInfo).getId());
                _Request.getSession().setAttribute("userTypeID", ((User)userInfo).getUserTypeId());
                return new BUSRespone("login success");
            }
        } catch (Exception ex) {
            return new BUSRespone(null, BUSStatus.exceptiond(), ex);
        }
    }

    
    public BUSRespone<Integer> GetCurrentUserID() throws Exception{
        Object oUserID = _Request.getSession().getAttribute("userID");
        
        if(oUserID instanceof Integer)
            return new BUSRespone<>((Integer)oUserID);
        
        Integer re = NumberUtil.parseInt(oUserID, null);
        
        return re == null ? new BUSRespone<>(null, BUSStatus.busFailed()) : new BUSRespone<>(re);
    }

}
