package org.codelife.app.killer.controller.api;

import org.codelife.app.killer.util.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * for public sources
 *
 * @author csl
 * @create 08/03/2017 16:29
 **/
@RequestMapping("/apis/resource")
@RestController
public class PublicController {

    @Autowired
    JsonResultUtils jsonResultUtils;



}
