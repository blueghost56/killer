package org.codelife.app.killer.util;

import org.codelife.app.killer.constant.SymbolContract;

/**
 * utils class for view
 *
 * @author csl
 * @create 07/21/2017 14:08
 **/
public final class ViewKit {
    private ViewKit(){}
    static class Action{
        static final String REDIRECT="redirect:";
    }

    public static String getViewName(final String moduleName,final String... subPaths){
        String path= makePath(moduleName,subPaths);
        return path.substring(1);
    }

    public static String redirect(final String moduleName,final String... subPaths){
        return (Action.REDIRECT+makePath(moduleName,subPaths));
    }

    public static String redirect(final String moduleName){
        return redirect(moduleName,null);
    }

    private static String makePath(final String moduleName,final String... subPaths){
        if(null==subPaths)return (SymbolContract.BackSlash+moduleName);

        StringBuilder sb=new StringBuilder(SymbolContract.BackSlash.toString());
        sb.append(moduleName);
        for (String subPath:subPaths){
            sb.append(SymbolContract.BackSlash.toString()).append(subPath);
        }
        return sb.toString();
    }
}
