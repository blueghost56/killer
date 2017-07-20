package org.codelife.app.killer.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "resources")
public class ResourceConfig {
    private StringResources strings;

    public void setStrings(StringResources strings){
        this.strings=strings;
    }
    public StringResources getStrings(){
        return this.strings;
    }


}
