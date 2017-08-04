package org.codelife.app.killer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "resources")
public class ResourceConfig {
    private StringResources strings;
    private ConfigResources configs;

}
