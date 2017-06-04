package uit.j2ee.model;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.Product;
import uit.j2ee.core.Business;
import uit.j2ee.util.NumberUtil;
import uit.j2ee.util.StringUtil;

public class ProductBUS extends Business<Product> {

    public ProductBUS() {
        super(Product.class);
    }

    public BUSRespone<List<Product>> GetProductList(String keyword, String index, String count) throws Exception {

        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }

        
        
        return selectByShop();
        
//        List<Product> re;
//        try {
//            if (StringUtil.isNullOrSpaces(keyword)
//                    && StringUtil.isNullOrSpaces(index)
//                    && StringUtil.isNullOrSpaces(count)) {
//                re = select();
//            } else {
//                re = select(
//                        item -> item.getName().contains(keyword),
//                        NumberUtil.parseInt(index, 0),
//                        NumberUtil.parseInt(count, -1)
//                );
//            }
//
//            return new BUSRespone(re);
//        } catch (Exception e) {
//            throw e;
//        } 
    }

    public BUSRespone<List<Product>> GetProductListOfShop() throws Exception {
        if( _Request.getParameter("shopID") == null)
            return new BUSRespone<>(null, BUSStatus.busFailed());
        
        return selectByShop();
    }

    public BUSRespone<Product> InsertProduct(String jsonData) throws Exception {
        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }
        
        return insertWithJsonData(jsonData);
    }

    public BUSRespone<Product> UpdateProduct(String jsonData) throws Exception {
        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }
        return updateWithJsonData(jsonData);
    }

    public BUSRespone DeleteProduct(String ID) throws Exception {
        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }
        return deleteWithJsonData(ID);
    }
     
}
