package org.codelife.app.killer.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * build strategy for ModelAndView
 *
 * @author csl
 * @create 07/21/2017 14:11
 **/
public class ModelAndViewProxy {

    private final ModelAndView modelAndView;
    public ModelAndViewProxy(){
        modelAndView=new ModelAndView();
    }

    public ModelAndViewProxy(String viewName){
        this();
        modelAndView.setViewName(viewName);
    }

    public ModelAndViewProxy setViewName(final String viewName){
        modelAndView.setViewName(viewName);
        return this;
    }

    public ModelAndViewProxy addObject(final String attributeName,final Object attributeValue){
        modelAndView.addObject(attributeName,attributeValue);
        return this;
    }
    public ModelAndViewProxy addObject(final Object attributeValue){
        modelAndView.addObject(attributeValue);
        return this;
    }

    public ModelAndView toMAV(){
        return modelAndView;
    }
}
