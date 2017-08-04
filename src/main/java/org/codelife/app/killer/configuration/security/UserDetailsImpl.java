package org.codelife.app.killer.configuration.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * @author csl
 * @create 07/25/2017 14:49
 **/
public class UserDetailsImpl implements UserDetails {
    private final int accountId;
    private final String username;
    private final String password;
    private final Timestamp registerTime;
    private final Timestamp lastLoginTime;
    private final String loginIp;
    private final List<GrantedAuthority> roles;

    public UserDetailsImpl(int accountId,String username,String password,Timestamp registerTime,Timestamp lastLoginTime,String loginIp,List<GrantedAuthority> roles){
        this.accountId=accountId;
        this.username=username;
        this.registerTime=registerTime;
        this.password=password;
        this.lastLoginTime=lastLoginTime;
        this.loginIp=loginIp;
        this.roles=roles;
    }

    @JsonIgnore
    public int getAccountId() {
        return accountId;
    }

    @JsonIgnore
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    @JsonIgnore
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    @JsonIgnore
    public String getLoginIp() {
        return loginIp;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
