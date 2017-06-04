/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.core;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSResponeCode;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.Shop;
import uit.j2ee.been.User;
import uit.j2ee.been.UserTypeRole;
import uit.j2ee.model.ShopBUS;
import uit.j2ee.util.BUSReflection;
import uit.j2ee.util.Json;
import uit.j2ee.util.NumberUtil;

/**
 *
 * @author LAP10599-local
 */
public class Business<T extends Object> {

    protected Class<T> _BeenClass;
    protected HttpServletRequest _Request;
    protected HttpServletResponse _Response;

    public Business(Class<T> beenClass) {
        _BeenClass = beenClass;
    }

    public T insert(T data) throws Exception {
        SessionFactory factory = DBUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(data);
            tx.commit();
        } catch (HibernateException e) {
            data = null;
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }

        return data;
    }

    public T update(T data) throws Exception {
        SessionFactory factory = DBUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(data);
            tx.commit();
        } catch (HibernateException e) {
            data = null;
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }

        return data;
    }

    public int delete(int id) throws Exception {

        SessionFactory factory = DBUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            String jsonData = String.format("{\"id\":%d}", id);
//            T data = null;
//            try {
//                data = Json.toObject(jsonData, _BeenClass);
//            } catch (Exception e) {
//                data = (T) session.load(_BeenClass, id);
//            }
//
//            Object temp = session.load(_BeenClass, id);
//                data = (T) temp;

            Object temp = session.load(_BeenClass, id);
            T data = (T) temp;
            if (data != null) {
                session.delete(data);
                tx.commit();
                return 1;
            } else {
                return 1;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<T> saveList(List<T> data) throws Exception {
        SessionFactory factory = DBUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (T item : data) {
                session.save(item);
            }
            tx.commit();
        } catch (HibernateException e) {
            data.clear();
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
        return data;
    }

    public T selectByID(Integer ID) throws Exception {
        if (ID == null) {
            return null;
        }

        Session session = DBUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(_BeenClass);
            Object re = session.get(_BeenClass, ID);
            return re == null ? null : (T) re;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    public List<T> select() throws Exception {
        Object oUserID = _Request.getSession().getAttribute("userID");
        Object oShopID = _Request.getParameter("shopID");

        Integer userID = null;
        Integer shopID = null;

        if (oUserID instanceof Integer) {
            userID = (Integer) oUserID;
        } else if (oUserID instanceof String) {
            userID = NumberUtil.parseInt((String) oUserID, null);
        }

        if (oShopID instanceof Integer) {
            shopID = (Integer) shopID;
        } else if (oShopID instanceof String) {
            shopID = NumberUtil.parseInt((String) oShopID, null);
        }

        return selectInDatabase(
                userID,
                shopID,
                list -> list);
    }

    public List<T> select(Integer userID, Integer shopID) throws Exception {
        return selectInDatabase(userID, shopID, list -> list);
    }

    public List<T> select(Integer userID, Integer shopID, int index, int count) throws Exception {
        return selectInDatabase(
                userID,
                shopID,
                list -> list.subList(index, count)
        );
    }

    public List<T> select(Integer userID, Integer shopID, BoolFilter<T> selectFilter) throws Exception {
        return selectInDatabase(
                userID,
                shopID,
                list -> getList(list, selectFilter, 0, -1)
        );
    }

    public List<T> select(Integer userID, Integer shopID, BoolFilter<T> selectFilter, int index, int count) throws Exception {

        return selectInDatabase(
                userID,
                shopID,
                list -> getList(list, selectFilter, index, count)
        );
    }

    public List<T> select(BoolFilter<T> selectFilter, int index, int count) throws Exception {

        Object oUserID = _Request.getSession().getAttribute("userID");
        Object oShopID = _Request.getParameter("shopID");

        Integer userID = null;
        Integer shopID = null;

        if (oUserID instanceof Integer) {
            userID = (Integer) oUserID;
        } else if (oUserID instanceof String) {
            userID = NumberUtil.parseInt((String) oUserID, null);
        }

        if (oShopID instanceof Integer) {
            shopID = (Integer) shopID;
        } else if (oShopID instanceof String) {
            shopID = NumberUtil.parseInt((String) oShopID, null);
        }

        return selectInDatabase(
                userID,
                shopID,
                list -> getList(list, selectFilter, index, count)
        );
    }

    private void addWhere(Criteria criteria, String fieldName, Object value) {
        if (value == null) {
            return;
        }

        String fn = null;
        for (Field m : _BeenClass.getDeclaredFields()) {
            String fName = m.getName();
            if (Objects.equals(fName, fieldName)) {
                fn = fName;
                break;
            }
        }

        if (fn != null) {
            criteria.add(Restrictions.eq(fn, value));
        }

    }

    private List<T> selectInDatabase(Integer userID, Integer shopID, ListFilter<T> filter) throws Exception {

        Session session = DBUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(_BeenClass);

            addWhere(criteria, "userId", userID);
            addWhere(criteria, "shopId", shopID);

            return filter.select(criteria.list());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    private <T> List<T> getList(List<T> list, BoolFilter<T> boolFilter, int index, int count) {
        if (count == 0) {
            return new ArrayList();
        }

        int i = 0;
        int c = 0;
        int n = list.size();
        if (count == -1) {
            count = n;
        }

        List<T> re = new ArrayList<T>(n);
        for (T item : list) {
            if (++i > index && boolFilter.check(item)) {
                re.add(item);
                if (++c >= count) {
                    return re;
                }
            }
        }

        return re;
    }

    protected BUSRespone insertWithJsonData(String jsonData) {
        T data = jsonToBeen(jsonData);
        if (data == null) {
            return new BUSRespone(null, BUSStatus.jsonFailedd());
        }

        try {
            T re = insert(data);
            return new BUSRespone(re);
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
        }
    }

    protected BUSRespone updateWithJsonData(String jsonData) {
        T data = jsonToBeen(jsonData);
        if (data == null) {
            return new BUSRespone(null, BUSStatus.jsonFailedd());
        }

        try {
            T re = update(data);
            return new BUSRespone(re);
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
        }
    }

    protected BUSRespone deleteWithJsonData(String ID) {
        int id = NumberUtil.parseInt(ID, Integer.MIN_VALUE);
        if (id == Integer.MIN_VALUE) {
            return new BUSRespone(null, BUSStatus.jsonFailedd());
        }

        try {
            int re = delete(id);
            return new BUSRespone(re);
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
        }
    }

    protected BUSRespone<List<T>> selectByShop() throws Exception {

        Object oShopID = _Request.getParameter("shopID");
        Integer shopID = NumberUtil.parseInt(oShopID, null);
        List<Shop> listShop = null;
        if (shopID == null) {
            ShopBUS shopBUS = new BUSReflection().createBUS(_Request, _Response, ShopBUS.class);
            BUSRespone<List<Shop>> res = shopBUS.GetShopList(null, null, null);
            if (res.statusCode != BUSResponeCode.ok) {
                return res.convert();
            }

            if (res.data.size() == 0) {
                return new BUSRespone<>(new ArrayList<>());
            }

            listShop = res.data;
        } else {
            listShop = new ArrayList<>();
            Shop shop = new Shop();
            shop.setId(shopID);
            listShop.add(shop);
        }

        Session session = DBUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(_BeenClass);

            int n = listShop.size();
            SimpleExpression[] arrExp = new SimpleExpression[n];
            for (int i =0;i < n; i++) {
                arrExp[i] = Restrictions.eq("shopId", listShop.get(i).getId());
            }
            criteria = criteria.add(Restrictions.or(arrExp));
            return new BUSRespone(criteria.list());
        } catch (Exception ex) {
            return new BUSRespone(null, BUSStatus.exceptiond(), ex);
        } finally {
            session.close();
        }
    }

    protected T jsonToBeen(String jsonData) {
        return Json.<T>toObject(jsonData, _BeenClass);
    }

    protected String bennToJson(T data) {
        return Json.toJson(data);
    }

    /*
        Kiểm tra quyền truy cập của method
        @Param dLevel: độ chênh level của method checkRole và method cần check  
     */
    protected boolean checkRole(int dLevel) throws Exception {
        StackTraceElement st = Thread.currentThread().getStackTrace()[1 + dLevel];
        String[] arr = st.getClassName().split(Pattern.quote("."));

        String className = arr[arr.length - 1];
        String methodName = st.getMethodName();
        Object userTypeID = _Request.getSession().getAttribute("userTypeID");

        Session session = DBUtil.getSessionFactory().openSession();
        try {
            SQLQuery abQuery = session.createSQLQuery(
                    "SELECT * FROM UserTypeRole where Function = :absolute");
            abQuery.setParameter("absolute", className + "." + methodName);
            abQuery.addEntity(UserTypeRole.class);

            List<UserTypeRole> listAB = abQuery.list();
            for (UserTypeRole item : listAB) {
                if (item.getUserTypeId() == null || Objects.equals(item.getUserTypeId(), userTypeID)) {
                    return item.isAccept();
                }
            }

            SQLQuery rlQuery = session.createSQLQuery(
                    "SELECT * FROM UserTypeRole where Function = :relative");
            rlQuery.setParameter("relative", className + ".*");
            rlQuery.addEntity(UserTypeRole.class);
            List<UserTypeRole> listRL = rlQuery.list();
            for (UserTypeRole item : listRL) {
                if (item.getUserTypeId() == null || Objects.equals(item.getUserTypeId(), userTypeID)) {
                    return item.isAccept();
                }
            }

            return false;

        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
    }

    protected boolean checkRoleOfCurrentMethod() throws Exception {
        return checkRole(2);
    }

}
