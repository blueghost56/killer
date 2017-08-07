package org.codelife.app.killer.controller;

import org.codelife.app.killer.adapter.BaseControllerAdapter;
import org.codelife.app.killer.bean.JsonResult;
import org.codelife.app.killer.bean.form.SignInForm;
import org.codelife.app.killer.bean.form.SignUpForm;
import org.codelife.app.killer.handler.DoorHandler;
import org.codelife.app.killer.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * signin,signup
 *
 * @author csl
 * @create 07/21/2017 11:50
 **/
@RequestMapping("/door")
@Controller
public class DoorController extends BaseControllerAdapter{
    private final static Logger LOGGER= LoggerFactory.getLogger(DoorController.class);

    @Autowired
    DoorHandler doorHandler;

    @RequestMapping(path = {"","/signIn.html"})
    public ModelAndView onSignIn(){

        return modelAndViewProxy("door","signin")
                .toMAV();
    }

    @RequestMapping("/signUp.html")
    public ModelAndView onSignUp(HttpSession session){
        return modelAndViewProxy("door","signup")
                .addObject("","")
                .toMAV();
    }

    // handle client request
    @ResponseBody
    @PostMapping("/signin.do")
    public JsonResult<String> doSignIn(@Valid SignInForm signInForm, BindingResult result,HttpServletRequest request){
        signInForm.setLoginIp(request.getRemoteAddr()+":"+request.getRemotePort());
        signInForm.setSignInTime(TimeUtils.getNowTimeStamp());
      return doorHandler.handleSignIn(signInForm,result);
    }

    @ResponseBody
    @RequestMapping("/signInFail.do")
    public JsonResult<String> doSignInFail(){
        return doorHandler.handleSignInFail();
    }

    @ResponseBody
    @RequestMapping("/signInSuccess.do")
    public JsonResult<String> doSignInSuccess(){
        return doorHandler.handleSignInSuccess();
    }

    @ResponseBody
    @PostMapping("/signup.do")
    public JsonResult<String> doSignup(@Valid SignUpForm signUpForm, BindingResult result, HttpServletRequest request,HttpSession session){
        if(!isCerValidate(session)){
            return null;
        }
        signUpForm.setRemoteIP(request.getRemoteAddr()+":"+request.getRemotePort());
        return doorHandler.handleSignUp(signUpForm,result);
    }

}
