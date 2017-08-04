package org.codelife.app.killer.service;

import org.codelife.app.killer.model.Account;

public interface AccountService {
    Account findAccount(final String username,final String password);
    Account findAccountByUsername(final String username);
    Integer save(final Account account);
    Integer update(final Account account);

}
