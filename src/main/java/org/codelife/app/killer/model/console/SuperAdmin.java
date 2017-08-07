package org.codelife.app.killer.model.console;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * super admin for website
 *
 * @author csl
 * @create 08/07/2017 16:56
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SuperAdmin {
    private String accountName;
    @JsonIgnore
    private String accountCode;
    private String loginIpAddr;
    private int loginIpPort;
    private boolean enable;
}
