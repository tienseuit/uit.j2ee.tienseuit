package uit.j2ee.model;

import java.util.List;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.Shop;
import uit.j2ee.core.Business;
import uit.j2ee.util.NumberUtil;
import uit.j2ee.util.StringUtil;

public class ShopBUS extends Business<Shop> {

    public ShopBUS() {
        super(Shop.class);
    }

    public BUSRespone<List<Shop>> GetShopList(String keyword, String index, String count) throws Exception {

        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }

        List<Shop> re;
        try {
            if (StringUtil.isNullOrSpaces(keyword)
                    && StringUtil.isNullOrSpaces(index)
                    && StringUtil.isNullOrSpaces(count)) {
                re = select();
            } else {
                re = select(
                        item -> item.getName().contains(keyword),
                        NumberUtil.parseInt(index, 0),
                        NumberUtil.parseInt(count, -1)
                );
            }

            return new BUSRespone<>(re);
        } catch (Exception e) {
            throw e;
        }

    }

    public BUSRespone<Shop> GetShopByID(String shopID) throws Exception {
        try {
            Integer id = NumberUtil.parseInt(shopID, null);
            Shop re = selectByID(id);
            return new BUSRespone<>(re);
        } catch (Exception e) {
            return new BUSRespone<>(null, BUSStatus.dbFailedd(), e);
        }
    }

    public BUSRespone InsertShop(String jsonData) throws Exception {
        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }

        Shop data = jsonToBeen(jsonData);
        if (data == null) {
            return new BUSRespone(null, BUSStatus.jsonFailedd());
        }

        try {
            data.setUserId((Integer) _Request.getSession().getAttribute("userID"));
            Shop re = insert(data);
            return new BUSRespone(re);
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
        }
    }

    public BUSRespone UpdateShop(String jsonData) throws Exception {
        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }
        return updateWithJsonData(jsonData);
    }

    public BUSRespone DeleteShop(String ID) throws Exception {
        if (checkRoleOfCurrentMethod() == false) {
            return new BUSRespone(null, BUSStatus.denied());
        }
        return deleteWithJsonData(ID);
    }
}
