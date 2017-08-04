package org.codelife.app.killer.controller.api;

import org.codelife.app.killer.bean.JsonResult;
import org.codelife.app.killer.configuration.StatusCode;
import org.codelife.app.killer.model.Account;
import org.codelife.app.killer.security.JWTUtils;
import org.codelife.app.killer.service.AccountService;
import org.codelife.app.killer.util.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author csl
 * @create 08/03/2017 16:27
 **/

@RequestMapping("/apis/user")
@RestController
public class UserController {

    @Autowired
    JsonResultUtils jsonResultUtils;

    @Autowired
    AccountService accountService;

    @Autowired
    JWTUtils jwtUtils;

    @RequestMapping("/signIn")
    public JsonResult<String> doSignIn(){
     return jsonResultUtils.newInstance(StatusCode.SUCCESS);
    }

    @RequestMapping("/test")
    public JsonResult<Account> onTest(){
       Account account= accountService.findAccountByUsername("alex_ching");
       return jsonResultUtils.newInstance(StatusCode.SUCCESS,account);
    }

}
