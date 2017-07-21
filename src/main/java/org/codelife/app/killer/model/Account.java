package org.codelife.app.killer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

/**
 * member's account
 *
 * @author csl
 * @create 07/21/2017 13:53
 **/
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Account {
    private int accountId; // Account's id
    private String username; // for login
    @JsonIgnore
    private String password; // account's password
    @JsonFormat(pattern = "YYYY/MM/DD HH:mm:ss")
    private Timestamp lastLoginTime;
    private String loginIp;

    public Account(){}

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
