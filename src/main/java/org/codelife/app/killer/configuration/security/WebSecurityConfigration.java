package org.codelife.app.killer.configuration.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * web security config
 *
 * @author csl
 * @create 07/20/2017 17:57
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigration extends WebSecurityConfigurerAdapter {
    private final Logger logger= LoggerFactory.getLogger(WebSecurityConfigration.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/favicon.ico","/**/*.html","/**/*.css","/**/*.js","/**/*.jpg","/**/*.png","/**/*.do")
                .permitAll()
                .antMatchers("/apis/**","/door/**","/backdoor/**")
                .permitAll()
                .antMatchers("/console/**")
                .access("hasRole('USER')")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();

                http.addFilterAt(concurrentSessionFilter(),ConcurrentSessionFilter.class);
                http.addFilterBefore(authenticationFilter(),UsernamePasswordAuthenticationFilter.class);
                http.addFilterBefore(logoutFilter(), LogoutFilter.class);
                http.headers().frameOptions().sameOrigin().contentTypeOptions();
                http.sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy());
    }

    @Bean
    ConcurrentSessionFilter concurrentSessionFilter(){
        ConcurrentSessionFilter concurrentSessionFilter=new ConcurrentSessionFilter(sessionRegistry());
        return concurrentSessionFilter;
    }

    @Bean
    LogoutFilter logoutFilter(){
        LogoutFilter logoutFilter=new LogoutFilter("/door/",logoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout");
        return logoutFilter;
    }

    @Bean
    CustomUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter= new CustomUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        customUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/login");
        return customUsernamePasswordAuthenticationFilter;
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler(){
        SavedRequestAwareAuthenticationSuccessHandler successHandler=new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setAlwaysUseDefaultTargetUrl(true);
        successHandler.setDefaultTargetUrl("/door/signInSuccess.do");
        return successHandler;
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler(){
        SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler=new SimpleUrlAuthenticationFailureHandler();
        simpleUrlAuthenticationFailureHandler.setDefaultFailureUrl("/door/signInFail.do");
        return simpleUrlAuthenticationFailureHandler;
    }

    @Bean
    SessionAuthenticationStrategy sessionAuthenticationStrategy(){
        List<SessionAuthenticationStrategy> sessionAuthenticationStrategyList=new ArrayList<>();
        sessionAuthenticationStrategyList.add(concurrentSessionControlAuthenticationStrategy());
        sessionAuthenticationStrategyList.add(sessionFixationProtectionStrategy());
        sessionAuthenticationStrategyList.add(registerSessionAuthenticationStrategy());

        CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy=new CompositeSessionAuthenticationStrategy(sessionAuthenticationStrategyList);
        return sessionAuthenticationStrategy;
    }

    @Bean
    SessionAuthenticationStrategy concurrentSessionControlAuthenticationStrategy(){
        ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy=new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);
        concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(true);
        return concurrentSessionControlAuthenticationStrategy;
    }

    @Bean
    SessionAuthenticationStrategy sessionFixationProtectionStrategy(){
        SessionFixationProtectionStrategy sessionFixationProtectionStrategy=new SessionFixationProtectionStrategy();
        return sessionFixationProtectionStrategy;
    }

    @Bean
    SessionAuthenticationStrategy registerSessionAuthenticationStrategy(){
        RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy=new RegisterSessionAuthenticationStrategy(sessionRegistry());
        return registerSessionAuthenticationStrategy;
    }

    @Bean
    SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    LogoutHandler logoutHandler(){
        return new SecurityContextLogoutHandler();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
