package org.codelife.app.killer.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import org.codelife.app.killer.configuration.ErrorCode;
import org.codelife.app.killer.configuration.ResourceConfig;
import org.codelife.app.killer.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author csl
 * @desc
 * @date 07/20/2017
 */
@Component
public class JsonCode {
    private final static Logger LOGGER= LoggerFactory.getLogger(JsonCodeInfo.class);

    @Autowired
    ResourceConfig resourceConfig;

    public JsonCodeInfo getJsonCodeInfo(final ErrorCode errorCode){
        Map<String,JsonCodeInfo> jsonCodeMap= null;
        try {
            jsonCodeMap = JsonUtils.getTemplateObj(resourceConfig.getStrings().getInfoFile(), new TypeReference<Map<String, JsonCodeInfo>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return jsonCodeMap.get(errorCode.toString());
    }


}
