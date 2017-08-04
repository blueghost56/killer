package org.codelife.app.killer.configuration.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author csl
 * @create 07/25/2017 13:46
 **/

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private final Logger logger= LoggerFactory.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        if(request.getMethod().equals(HttpMethod.POST)){
            throw new AuthenticationServiceException("Authentication method not support: "+request.getMethod());
        }

        String username=obtainUsername(request);
        String password=obtainPassword(request);

        if(null == username){
            username    =   "";
        }

        if(null == password){
            password    =   "";
        }

        username    =   username.trim();

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        setDetails(request,authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
