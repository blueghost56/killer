package org.codelife.app.killer.controller.console;

import org.codelife.app.killer.adapter.BaseControllerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Console - super admin
 * @author csl
 * @create 07/27/2017 9:59
 **/

@RequestMapping("/console/module")
@Controller
public class ModuleManagementController extends BaseControllerAdapter{

    @RequestMapping("/")
    public ModelAndView index(){
        return modelAndViewProxy("console/module/index")
                .toMAV();
    }

}
