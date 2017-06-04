package uit.j2ee.model;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import uit.j2ee.core.Business;
import uit.j2ee.core.DBUtil;
import uit.j2ee.core.DetailBUS;
import uit.j2ee.util.Json;
import uit.j2ee.util.NumberUtil;
import uit.j2ee.util.StringUtil;

public class BillBUS extends Business<Bill> {

    public BillBUS() {
        super(Bill.class);
    }

    public BUSRespone<List<Bill>> GetBillListOfShop() throws Exception {
        if (_Request.getParameter("shopID") == null) {
            return new BUSRespone<>(null, BUSStatus.busFailed());
        }

        return selectByShop();
    }

    public BUSRespone<Bill> InsertBill(String jsonData, String jsonDetailData) throws Exception {
        BUSRespone<Bill> res = insertWithJsonData(jsonData);

        if (res.statusCode != BUSResponeCode.ok) {
            return res;
        }
        
             
//       
//        
//           List<T> list = null;
//
//        try {
//            list = Json.toObject(jsonData, _ListBeenClass);
//        } catch (Exception ex) {
//            return new BUSRespone(null, BUSStatus.jsonFailedd());
//        } 
//        
//        try {
//            return insertDetail(ownerColumn, onwerValue, list);
//        } catch (Exception e) {
//            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
//        }

        DetailBUS<BillDetail> detailBUS = new DetailBUS<> (BillDetail.class);
        BUSRespone<List<BillDetail>> resDetail = 
                detailBUS.insertDetailWithJsonData("setBillId", res.data.getId(), jsonDetailData);
        
        if(resDetail.statusCode == BUSResponeCode.ok)
            return res;
        else
            return resDetail.<Bill>convert();
    }

    
}
