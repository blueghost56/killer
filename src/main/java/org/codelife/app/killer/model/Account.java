package org.codelife.app.killer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * member's account
 *
 * @author csl
 * @create 07/21/2017 13:53
 **/
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class Account {
    private int accountId; // Account's id
    private String username; // for login
    @JsonIgnore
    private String password; // account's password
    @JsonFormat(pattern = "YYYY/MM/DD HH:mm:ss")
    private Timestamp registerTime;
    @JsonFormat(pattern = "YYYY/MM/DD HH:mm:ss")
    private Timestamp lastLoginTime;
    private String loginIp;
    @JsonIgnore
    private List<String> roles;

    public Account(){}

    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", lastLoginTime=" + lastLoginTime +
                ", loginIp='" + loginIp + '\'' +
                ", roles=" + roles +
                '}';
    }
}
