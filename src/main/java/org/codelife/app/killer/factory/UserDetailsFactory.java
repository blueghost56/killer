package org.codelife.app.killer.factory;

import netscape.security.UserDialogHelper;
import org.codelife.app.killer.configuration.security.UserDetailsImpl;
import org.codelife.app.killer.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author csl
 * @create 07/25/2017 14:46
 **/
public final class UserDetailsFactory {
    private UserDetailsFactory(){}

    public static UserDetails creae(final Account account){
       return new UserDetailsImpl(account.getAccountId(),account.getUsername(),account.getPassword(),account.getRegisterTime(),account.getLastLoginTime(),account.getLoginIp(),mapToGrantedAuthorties(account.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorties(final List<String> authorities){
        return authorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
