package com.yhbnb.controller;

import com.yhbnb.service.DataJPA.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value ="user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value ="/addUser")
    public String addPerson(HttpServletRequest request){

      /*  //存起来
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addPerson");
        *//*modelAndView.addObject("user", ls);  */
        return "addPerson";
    }

    @RequestMapping(value ="/editUser")
    public String editPerson(HttpServletRequest request){

      /*  //存起来
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addPerson");
        *//*modelAndView.addObject("user", ls);  */
        return "editPerson";
    }

}
