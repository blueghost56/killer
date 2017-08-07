package org.codelife.app.killer.adapter;

import org.codelife.app.killer.util.ModelAndViewProxy;
import org.codelife.app.killer.util.ViewKit;

import javax.servlet.http.HttpSession;

/**
 * @author csl
 * @create 07/26/2017 12:46
 **/
public abstract class BaseControllerAdapter {
    public final static String KEY_CER_VALIDATE="bd-key-cer-va";

    public boolean isCerValidate(final HttpSession session){
        return (null!=session.getAttribute(KEY_CER_VALIDATE)&&(Boolean)session.getAttribute(KEY_CER_VALIDATE));
    }
    public ModelAndViewProxy modelAndViewProxy(){
        return new ModelAndViewProxy();
    }

    public ModelAndViewProxy modelAndViewProxy(final String moduleName,final String... subPaths){
        return new ModelAndViewProxy(ViewKit.getViewName(moduleName,subPaths));
    }
}
