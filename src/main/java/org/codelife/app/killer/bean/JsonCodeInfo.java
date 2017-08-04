package org.codelife.app.killer.bean;


/**
 * reponse  status,message
 *
 * @author csl
 * @create 07/20/2017 14:52
 **/
public class JsonCodeInfo {
    private int status;
    private String msg;
    public JsonCodeInfo(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
