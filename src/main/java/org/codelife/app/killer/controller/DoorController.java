package org.codelife.app.killer.controller;

import org.codelife.app.killer.model.Module;
import org.codelife.app.killer.util.ModelAndViewProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * signin,signup
 *
 * @author csl
 * @create 07/21/2017 11:50
 **/
@RequestMapping("/door")
@Controller
public class DoorController{

    @RequestMapping(path = {"/","signIn"})
    public ModelAndView onLogin(){
        return modelAndViewProxy()
                .addObject("","")
                .setViewName("door/signin");
    }

    @RequestMapping("signUp")
    public ModelAndView onRegister(){

        return modelAndViewProxy()
                .addObject("","")
                .setViewName("door/signup");
    }


    @Bean
    ModelAndViewProxy modelAndViewProxy(){
        return new ModelAndViewProxy();
    }
}
