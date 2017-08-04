package org.codelife.app.killer.configuration.security;

import lombok.Data;

/**
 * @author csl
 * @create 08/04/2017 18:04
 **/
@Data
public class SuperAdminConfig {
    private String path;
    private long expiration;
}
