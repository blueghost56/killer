package org.codelife.app.killer.configuration.security;

import lombok.Data;

/**
 * @author csl
 * @create 08/04/2017 18:04
 **/
@Data
public class SuperAdminConfig {
    private String path; // super admin configuration file path
    private int expiration; // certificate token expiration,unit: minutes
    private String prefix; // certificate token prefix
    private String suffix; // suffix token file
    private int fileExpiration; // certificate file expiration
    private int arguments; // the arguments in token file to verify
    private String fileName;
}
