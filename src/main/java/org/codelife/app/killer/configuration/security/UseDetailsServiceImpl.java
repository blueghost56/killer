package org.codelife.app.killer.configuration.security;

import org.codelife.app.killer.factory.UserDetailsFactory;
import org.codelife.app.killer.model.Account;
import org.codelife.app.killer.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author csl
 * @create 07/25/2017 14:38
 **/

@Service
public class UseDetailsServiceImpl implements UserDetailsService {
    private final Logger LOGGER= LoggerFactory.getLogger(UseDetailsServiceImpl.class);

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Account account= accountService.findAccountByUsername(username);
       LOGGER.info("用户名:{}-账户:{}",username,account);
       return UserDetailsFactory.creae(account);
    }
}
