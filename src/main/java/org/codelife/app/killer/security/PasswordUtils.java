package org.codelife.app.killer.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author csl
 * @create 07/26/2017 14:26
 **/
public final class PasswordUtils {
    private PasswordUtils(){}

    public final static String encodeByBcrypt(final CharSequence rawPassword){
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    public final static boolean isValid(final String rawPassword,final String encodePassword){
        return new BCryptPasswordEncoder().matches(rawPassword,encodePassword);
    }
}
