package org.codelife.app.killer.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * A util class to instance class from json file simply
 *
 * @author csl
 * @create 07/20/2017 15:43
 **/
public final class JsonUtils {
    private JsonUtils(){}

    public static <T> T getTemplateObj(final File jsonFilePath,final TypeReference<T> tr) throws IOException {
       return new ObjectMapper().readValue(jsonFilePath,tr);
    }
}
