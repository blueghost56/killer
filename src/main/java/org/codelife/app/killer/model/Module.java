package org.codelife.app.killer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

/**
 * table module
 *
 * @author csl
 * @create 07/21/2017 13:48
 **/

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Module {
    private int id; // Moudle's id
    private String name; // Module's name
    private boolean isEnable; // Module is enable or disable
    private int subId; // sub's module id
    private String desc; // Module's description
    private String version; // major_verion_number.minor_version_number[.revision_number[.builde_number]]
    private Timestamp lastUpdateTime; // module's last update time

    public Module(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
