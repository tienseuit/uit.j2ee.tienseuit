///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package uit.j2ee.controller;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpRequest;
//import org.springframework.stereotype.Controller;   
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView; 
//import uit.j2ee.app.BUSRespone;
//import uit.j2ee.app.BUSStatus;
//import uit.j2ee.been.User;
//import uit.j2ee.model.UserBUS;
//import uit.j2ee.util.BUSReflection;
//
//@Controller  
//public class Dev {  
//    @RequestMapping("/dev/login")
//    public ModelAndView loginOrRegister() {
//        
//        
//    public BUSRespone Login(HttpServletRequest request, HttpServletResponse response) throws Exception{
//       
//        UserBUS bus= new UserBUS();
//        BUSReflection rf  =new BUSReflection();
//        rf.setDataFromRequest(request, response, bus);
//        bus.DevLogin(request.getParameter("userID"));
//        response.get
//        return new ModelAndView("user/login-register");  
//    } 
//}  
//
// 