package uit.j2ee.model;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.Device;
import uit.j2ee.been.Shop;
import uit.j2ee.been.User;
import uit.j2ee.been.UserTypeRole;
import uit.j2ee.core.Business;
import uit.j2ee.core.DBUtil; 

public class UserTypeRoleBUS {

    public UserTypeRoleBUS() { 
    }

//    public boolean checkRole(String className, String methodName, Integer userTypeID) {
//        Session session = DBUtil.getSessionFactory().openSession();
//        try {
//            SQLQuery abQuery = session.createSQLQuery(
//                    "SELECT * FROM UserTypeRole where Function = :absolute");
//            abQuery.setParameter("absolute", className + "." + methodName);
//            abQuery.addEntity(UserTypeRole.class);
//
//            List<UserTypeRole> listAB = abQuery.list();
//            for (UserTypeRole item : listAB) {
//                if (item.getUserTypeId() == null || item.getUserTypeId() == userTypeID) {
//                    return item.isAccept();
//                }
//            }
//
//            SQLQuery rlQuery = session.createSQLQuery(
//                    "SELECT * FROM UserTypeRole where Function = :relative");
//            rlQuery.setParameter("relative", className + ".*");
//            rlQuery.addEntity(UserTypeRole.class);
//            List<UserTypeRole> listRL = rlQuery.list();
//            for (UserTypeRole item : listRL) {
//                if (item.getUserTypeId() == null || item.getUserTypeId() == userTypeID) {
//                    return item.isAccept();
//                }
//            }
//
//            return false;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw ex;
//        } finally {
//            session.close();
//        }
//    }
 
}
