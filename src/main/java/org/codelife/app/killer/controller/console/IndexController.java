package org.codelife.app.killer.controller.console;

import org.codelife.app.killer.adapter.BaseControllerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

/**
 * @author csl
 * @create 07/26/2017 15:17
 **/

@RequestMapping("/console")
@Controller
public class IndexController extends BaseControllerAdapter{

    @RequestMapping("/")
    public ModelAndView index(){

      return modelAndViewProxy("console/index")
                .toMAV();
    }



}
