/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author LAP10599-local
 */
@Controller
public class ShopCTR { 
    @RequestMapping("/business")
    public ModelAndView shopListManagement(HttpServletRequest request, HttpServletResponse response) {
        if(request.getSession().getAttribute("user") == null)
            return new ModelAndView("user/access-denied");  
        
        return new ModelAndView("shop/shop-manager"); 
    }
 
}
