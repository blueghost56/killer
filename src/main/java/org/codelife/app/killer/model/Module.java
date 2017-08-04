package org.codelife.app.killer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

/**
 * table module
 *
 * @author csl
 * @create 07/21/2017 13:48
 **/

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class Module {
    private int id; // Moudle's id
    private String name; // Module's name
    private boolean isEnable; // Module is enable or disable
    private int[] subId; // sub's module id
    private int parentId; // module's parent id
    private String desc; // Module's description
    private String version; // major_verion_number.minor_version_number[.revision_number[.builde_number]]
    private Timestamp lastUpdateTime; // module's last update time

    public Module(){}
}
