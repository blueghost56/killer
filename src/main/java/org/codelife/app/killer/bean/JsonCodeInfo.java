package org.codelife.app.killer.bean;

/**
 * reponse  code,message
 *
 * @author csl
 * @create 07/20/2017 14:52
 **/
public class JsonCodeInfo {
    private int code;
    private String msg;
    public JsonCodeInfo(){}

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
}
