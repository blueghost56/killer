package org.codelife.app.killer.handler;

import org.codelife.app.killer.bean.JsonResult;
import org.codelife.app.killer.bean.form.SignInForm;
import org.codelife.app.killer.bean.form.SignUpForm;
import org.codelife.app.killer.configuration.StatusCode;
import org.codelife.app.killer.factory.AccountFactory;
import org.codelife.app.killer.model.Account;
import org.codelife.app.killer.service.AccountService;
import org.codelife.app.killer.util.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * Handle DoorController's  Response
 *
 * @author csl
 * @create 07/24/2017 13:33
 **/

@Component
public class DoorHandler {

    @Autowired
    JsonResultUtils jsonResultUtils;

    @Autowired
    AccountService accountService;

    /**
     *  handle sign in method,after try 3 times to locked the account.
     */
    public JsonResult<String> handleSignIn(final SignInForm signInForm, final BindingResult result){

        if(result.hasErrors()){
            return jsonResultUtils.newInstance(StatusCode.E_ACCOUNT_OR_PASSWORD);
        }
       Account account= accountService.findAccount(signInForm.getUsername(),signInForm.getPassword());
        if(null == account){
            return jsonResultUtils.newInstance(StatusCode.E_ACCOUNT_OR_PASSWORD);
        }

        account.setLoginIp(signInForm.getLoginIp());
        account.setLastLoginTime(signInForm.getSignInTime());

        // update account's loginIp and last login time
        accountService.update(account);

        Authentication authentication=new UsernamePasswordAuthenticationToken("user",account.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jsonResultUtils.newInstance(StatusCode.SUCCESS);
    }

    public JsonResult<String> handleSignInFail(){
        return jsonResultUtils.newInstance(StatusCode.E_ACCOUNT_OR_PASSWORD);
    }

    public JsonResult<String> handleSignInSuccess(){
        return jsonResultUtils.newInstance(StatusCode.SUCCESS);
    }

    /**
     *
     * @param signUpForm - form
     * @param result - valid result
     * @return JsonResult
     */
    public JsonResult<String> handleSignUp(final SignUpForm signUpForm,final BindingResult result){
        if(result.hasErrors()){
            return jsonResultUtils.newInstance(StatusCode.E_INVALID_PARAMS);
        }

      Account account=accountService.findAccountByUsername(signUpForm.getUsername());
        if(null!=account){
            return jsonResultUtils.newInstance(StatusCode.E_EXISTS_ACCOUNT);
        }

        // save data to table
        account=AccountFactory.createWithForm(signUpForm);
        accountService.save(account);

        return jsonResultUtils.newInstance(StatusCode.SUCCESS);
    }
}
