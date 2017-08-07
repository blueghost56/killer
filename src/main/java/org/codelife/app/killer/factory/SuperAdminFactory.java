package org.codelife.app.killer.factory;

import org.codelife.app.killer.model.console.SuperAdmin;

/**
 * @author csl
 * @create 08/07/2017 18:21
 **/

public final class SuperAdminFactory {
    private SuperAdminFactory(){}

    public static SuperAdmin create(){
        SuperAdmin superAdmin=new SuperAdmin();
        return superAdmin;
    }
}
