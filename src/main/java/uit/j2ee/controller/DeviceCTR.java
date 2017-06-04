/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;   
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView; 

@Controller  
public class DeviceCTR {  
    @RequestMapping("business/device")
    public ModelAndView manager(HttpServletRequest request) {
        if(request.getSession().getAttribute("user") == null)
            return new ModelAndView("user/access-denied");  
        
        return new ModelAndView("device/device-manager");  
    }
    
    @RequestMapping("business/pos")
    public ModelAndView pos(HttpServletRequest request) {
        return new ModelAndView("device/pos");  
    }
    
    @RequestMapping("business/pos-setting")
    public ModelAndView setting(HttpServletRequest request) {
        if(request.getSession().getAttribute("user") == null)
            return new ModelAndView("user/access-denied");  
        
        return new ModelAndView("device/pos-setting");  
    }
    
}  

 