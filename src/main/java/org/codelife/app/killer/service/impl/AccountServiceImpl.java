package org.codelife.app.killer.service.impl;

import org.codelife.app.killer.mapper.AccountMapper;
import org.codelife.app.killer.model.Account;
import org.codelife.app.killer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * implements for account operation
 *
 * @author csl
 * @create 07/24/2017 13:10
 **/
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account findAccount(String username, String password) {
        return accountMapper.findAccount(username,password);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountMapper.findAccountByUsername(username);
    }

    @Override
    public Integer save(Account account) {
        return accountMapper.save(account);
    }

    @Override
    public Integer update(Account account) {
        return accountMapper.update(account);
    }
}
