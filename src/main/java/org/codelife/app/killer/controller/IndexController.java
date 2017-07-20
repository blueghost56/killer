package org.codelife.app.killer.controller;

import org.codelife.app.killer.configuration.ErrorCode;
import org.codelife.app.killer.bean.JsonResult;
import org.codelife.app.killer.mapper.TestMapper;
import org.codelife.app.killer.model.Pets;
import org.codelife.app.killer.util.JsonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    private final static Logger LOGGER= LoggerFactory.getLogger(IndexController.class);

    @Autowired
    TestMapper tm;

    @Autowired
    JsonResultUtils jsonResultUtils;

    @RequestMapping("/")
    public ModelAndView index(){
        final ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index/index");
        modelAndView.addObject("time",new Date());
        List<Pets> petsList=tm.findAllPets();
        modelAndView.addObject("pets",petsList);
        LOGGER.info("Test is a same class instance:{}",tm.toString());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/tm")
    public JsonResult<List<Pets>> writeFileTest(){
        // 1. Query from mysql 2. Write data to local file
        List<Pets> petsList= tm.findAllPets();

      return jsonResultUtils.newInstance(ErrorCode.SUCCESS,petsList);
    }

    @ResponseBody
    @RequestMapping("/tf")
    public JsonResult<Integer> insertDB(){
        return jsonResultUtils.newInstance(ErrorCode.SUCCESS,13);
    }

}
