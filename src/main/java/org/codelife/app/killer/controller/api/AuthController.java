package org.codelife.app.killer.controller.api;

import org.codelife.app.killer.bean.JsonResult;
import org.codelife.app.killer.configuration.StatusCode;
import org.codelife.app.killer.util.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * for restful
 *
 * @author csl
 * @create 07/21/2017 13:57
 **/
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    JsonResultUtils jsonResultUtils;

    @RequestMapping("/t")
    public JsonResult<String> onTest(){
        return jsonResultUtils.newInstance(StatusCode.SUCCESS);
    }
}
