package org.codelife.app.killer.util;

import org.codelife.app.killer.configuration.StatusCode;
import org.codelife.app.killer.bean.JsonCode;
import org.codelife.app.killer.bean.JsonCodeInfo;
import org.codelife.app.killer.bean.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A simple utils class for instance JsonResult to response
 *
 * @author csl
 * @create 07/20/2017 15:27
 **/
@Component
public final class JsonResultUtils{

    @Autowired
    JsonCode jsonCode;


    public <T>JsonResult<T> newInstance(final StatusCode code, final T data){
        return makeJsonResult(jsonCode,code,data);
    }

    public <T>JsonResult<T> newInstance(final int code,final String msg,final T data){
        return new JsonResult<>(code,msg,data);
    }

    public <T>JsonResult<T> newInstance(final StatusCode code){
        return makeJsonResult(jsonCode,code,null);
    }
    private static <T>JsonResult<T> makeJsonResult(final JsonCode jsonCode, final StatusCode code, final T data){
        JsonCodeInfo info=jsonCode.getJsonCodeInfo(code);
        return new JsonResult<>(info.getStatus(),info.getMsg(),data);
    }
}
