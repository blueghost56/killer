package org.codelife.app.killer.model.console;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author csl
 * @create 08/07/2017 18:06
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SuperAdminLog extends  SuperAdmin{
    
}
