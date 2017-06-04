package uit.j2ee.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSResponeCode;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.Bill;
import uit.j2ee.been.BillDetail;
import uit.j2ee.util.Json;

public class DetailBUS<T> extends Business {

    public DetailBUS(Class<?> c) {
        super(c);
    }

    public BUSRespone<List<T>> insertDetailWithJsonData(String ownerColumn, Object onwerValue, String jsonData) {
        List<T> list = null;

        try {
            Type listType = new TypeToken<ArrayList<BillDetail>>() {
            }.getType();
            List<BillDetail> yourClassList = Json.toObject(jsonData, listType);
            list = Json.toObject(jsonData, listType);
        } catch (Exception ex) {
            return new BUSRespone(null, BUSStatus.jsonFailedd());
        }

        try {
            return insertDetail(ownerColumn, onwerValue, list);
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
        }
    }

    protected BUSRespone<List<T>> insertDetail(String ownerMethodName, Object onwerValue, List<T> listDetail) throws Exception{
        String methodName = ownerMethodName;
        Method setOwnerMethod = null;
        for (Method m : _BeenClass.getMethods()) {
            if (m.getName().equals(methodName)) {
                setOwnerMethod = m;
                break;
            }
        }

        if (setOwnerMethod == null) {
            return new BUSRespone(null, new BUSStatus(BUSResponeCode.busFailed, "invalid owner column"));
        }

        SessionFactory factory = DBUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (T item : listDetail) {
                setOwnerMethod.invoke(item, onwerValue);
                session.save(item);
            }

            tx.commit();
            return new BUSRespone(listDetail);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }

    }
}
