/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSResponeCode;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.app.NavItem;
import uit.j2ee.been.Shop;
import uit.j2ee.model.ShopBUS;
import uit.j2ee.util.BUSReflection;
import uit.j2ee.util.NumberUtil;

/**
 *
 * @author LAP10599-local
 */
public class NavInfo {

    public NavItem selectedNavShop;
    public List<NavItem> listNavShop;
    public NavItem selectedNavFeature;
    public List<NavItem> listNavFeature;
    protected boolean isAllShop;
    protected String requestURL;

    public String selectedShopStringID;

    public BUSRespone build(HttpServletRequest request, HttpServletResponse response) throws Exception {
        requestURL = (String) request.getAttribute("javax.servlet.forward.request_uri");

        if (request.getSession().getAttribute("userID") == null) {
            return new BUSRespone(null, BUSStatus.denied());
        }

        BUSRespone re = buildShops(request, response);
        if (re.statusCode != BUSResponeCode.ok) {
            return re;
        }

        return buildFeture(request);
    }

    public BUSRespone buildShops(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ShopBUS shopBUS = new ShopBUS();
        BUSReflection rf = new BUSReflection(null);
        rf.setDataFromRequest(request, response, shopBUS);
        BUSRespone busRE = shopBUS.GetShopList(null, null, null);
        if (busRE.statusCode == BUSResponeCode.denied) {
            return busRE;
        }

        List<Shop> listShop = (List<Shop>) busRE.data;
        selectedShopStringID = request.getParameter("shopID");
        this.listNavShop = new ArrayList<>();
        try {
            int selectedShopID = NumberUtil.parseInt(selectedShopStringID, -1);
            int n = listShop.size();
            for (Shop item : listShop) {
                NavItem navItem = new NavItem(item.getName(), "?shopID=" + item.getId());
                navItem.setId(item.getId().toString());
                if (item.getId() == selectedShopID) {
                    this.selectedNavShop = navItem;
                } else {
                    this.listNavShop.add(navItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.selectedNavShop == null) {
            isAllShop = true;
            for (NavItem item : listNavShop) {
                item.setLink("/business/product" + item.getLink());
            }

            if (this.listNavShop.size() == 0) {
                this.selectedNavShop = new NavItem("Chưa có cửa hàng nào", "");
            } else {
                this.selectedNavShop = new NavItem("Tất cả cửa hàng", "?");
            }
        } else {
            isAllShop = false;
            this.listNavShop.add(new NavItem("Tất cả cửa hàng", "?"));
        }

        return new BUSRespone(this.selectedNavShop);
    }

    public BUSRespone buildFeture(HttpServletRequest request) {
        String shopLink = this.selectedNavShop == null
                ? "" : this.selectedNavShop.getLink();
        List<NavItem> listFeature = new ArrayList<NavItem>();
        listFeature.add(new NavItem("Các thiết bị POS", "/business/device"));
        listFeature.add(new NavItem("Các mặt hàng", "/business/product"));
        
        if(isAllShop == false)
            listFeature.add(new NavItem("Thống kê", "/business/statistic"));

        this.listNavFeature = new ArrayList<>();
        for (NavItem item : listFeature) {
            if (Objects.equals(item.getLink(), requestURL)) {
                item.setLink("");
                this.selectedNavFeature = item;
            } else {
                item.setLink(item.getLink() + shopLink);
                this.listNavFeature.add(item);
            }
        }

        if (this.selectedNavFeature == null) {
//            if(listNavShop.size() > 0)
            this.selectedNavFeature = new NavItem("Quản lý cửa hàng", "/business");
//            else
//                this.selectedNavFeature = new NavItem("Chọn một chức năng", "/businnes");
        } else {
            if (isAllShop) {
                this.listNavFeature.add(new NavItem("Quản lý cửa hàng", "/business"));
            }
        }

        return new BUSRespone(this.selectedNavFeature);
    }
}
