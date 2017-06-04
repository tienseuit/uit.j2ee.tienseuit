/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSResponeCode;
import uit.j2ee.model.ShopBUS;
import uit.j2ee.util.BUSReflection;

/**
 *
 * @author LAP10599-local
 */
@Controller
public class Statistic {

    @RequestMapping("/business/statistic")
    public ModelAndView statistic(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return new ModelAndView("user/access-denied");
        }

        ShopBUS shopBUS = new BUSReflection().createBUS(request, response, ShopBUS.class);

        try {
            Object oShopID = request.getParameter("shopID");
            if (oShopID != null) {
                String shopID = oShopID instanceof String ? (String) oShopID : oShopID.toString();
                BUSRespone res = shopBUS.GetShopByID(shopID);
                if (res.statusCode == BUSResponeCode.ok && res.data != null) {
                    return new ModelAndView("statistic/statistic");
                }
            };

        } catch (Exception ex) {
        }

        String msg;
        if(shopBUS.GetShopList(null, null, null).data.size() > 0)
            msg = "Bạn vui lòng chọn một cửa hàng để xem thống kê";
        else
            msg = "Bạn chưa có cửa hàng nào nên không thể sử dụng chức năng này";
            
                    
        return new ModelAndView(
                "statistic/shop-require",
                "msg",
                msg);
    }

}
