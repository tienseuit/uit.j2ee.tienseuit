/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;   
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView; 

@Controller  
public class UserCTR {  
    @RequestMapping("/user")
    public ModelAndView loginOrRegister() {
        return new ModelAndView("user/login-register");  
    }
     
    @RequestMapping("/logout")
    public String manager(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userID");
        return  "redirect:" + request.getContextPath();
    } 
    
     @RequestMapping("/access-denied")
    public ModelAndView manager() {
        return new ModelAndView("user/access-denied1");  
    } 
}  

 