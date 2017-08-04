package org.codelife.app.killer.factory;

import org.codelife.app.killer.bean.form.SignUpForm;
import org.codelife.app.killer.model.Account;
import org.codelife.app.killer.security.PasswordUtils;

/**
 * @author csl
 * @create 07/26/2017 13:54
 **/
public final class AccountFactory {
    private AccountFactory(){}

    public static Account createWithForm(final SignUpForm signUpForm){
       Account account=new Account();
       account.setUsername(signUpForm.getUsername());
       account.setPassword(PasswordUtils.encodeByBcrypt(signUpForm.getPassword()));
       account.setLoginIp(signUpForm.getRemoteIP());
       return account;
    }
}
