package org.codelife.app.killer.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 *  For response
 * @author csl
 * @param <T> T is response data type
 * @create  07/20/2017 16:20
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JsonResult<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    public JsonResult(){}

    public JsonResult(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status=status;
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
