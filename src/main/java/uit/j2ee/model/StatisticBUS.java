package uit.j2ee.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;
import org.springframework.util.NumberUtils;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.Product;
import uit.j2ee.core.Business;
import uit.j2ee.core.DBUtil;
import uit.j2ee.util.Json;
import uit.j2ee.util.NumberUtil;

public class StatisticBUS extends Business<Product> {

    public StatisticBUS() {
        super(Product.class);
    }

    public BUSRespone<List<HashMap>> GetProductStatisticByShop(String shopID, String startTime, String endTime) {

        Integer id = null;
        Date start = null;
        Date end = null;
        try {
            id = NumberUtil.parseInt(shopID, null);
            start = Json.toObject(startTime, Date.class);
            end = Json.toObject(endTime, Date.class);
        } catch (Exception ex) {
            return new BUSRespone<>(null, BUSStatus.jsonFailedd(), ex);
        }

        return callStoreGetStatisticByDevice(id, start, end);
    }

    protected BUSRespone<List<HashMap>> callStoreGetStatisticByDevice(Integer shopID, Date startTime, Date endTime) {
        Session session = DBUtil.getSessionFactory().openSession();
        try {
            Query query = session.createSQLQuery(
                    "CALL GetProductStatisticByShop(:shopId, :startTime, :endTime)")
                    .setParameter("shopId", shopID)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List re = query.list();
            return new BUSRespone<>(re);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new BUSRespone(null, BUSStatus.dbFailedd(), ex);
        } finally {
            session.close();
        }
    }

    public BUSRespone<List<HashMap>> GetPOSStatisticByShop(String shopID, String startTime, String endTime) {

        Integer id = null;
        Date start = null;
        Date end = null;
        try {
            id = NumberUtil.parseInt(shopID, null);
            start = Json.toObject(startTime, Date.class);
            end = Json.toObject(endTime, Date.class);
        } catch (Exception ex) {
            return new BUSRespone<>(null, BUSStatus.jsonFailedd(), ex);
        }

        return callStoreGetPOSStatisticByShop(id, start, end);
    }

    protected BUSRespone<List<HashMap>> callStoreGetPOSStatisticByShop(Integer shopID, Date startTime, Date endTime) {
        Session session = DBUtil.getSessionFactory().openSession();
        try {
            Query query = session.createSQLQuery(
                    "CALL GetPOSStatisticByShop(:shopId, :startTime, :endTime)")
                    .setParameter("shopId", shopID)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List re = query.list();
            return new BUSRespone<>(re);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new BUSRespone(null, BUSStatus.dbFailedd(), ex);
        } finally {
            session.close();
        }
    }

}
