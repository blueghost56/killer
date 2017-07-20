package org.codelife.app.killer.bean;

import java.io.Serializable;

/**
 *  For response
 * @author csl
 * @param <T> T is response data type
 * @create  07/20/2017 16:20
 */
public class JsonResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public JsonResult(){}

    public JsonResult(int code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
