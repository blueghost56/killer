package org.codelife.app.killer.configuration;

import lombok.Data;
import org.codelife.app.killer.configuration.security.SuperAdminConfig;

import java.util.List;

/**
 * @author csl
 * @create 08/04/2017 16:55
 **/
@Data
public class ConfigResources extends Resources {
    private SuperAdminConfig superAdmin;

}
