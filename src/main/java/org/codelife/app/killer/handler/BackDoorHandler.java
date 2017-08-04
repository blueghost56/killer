package org.codelife.app.killer.handler;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.codelife.app.killer.configuration.ConfigResources;
import org.codelife.app.killer.configuration.ResourceConfig;
import org.codelife.app.killer.configuration.Resources;
import org.codelife.app.killer.configuration.security.SuperAdminConfig;
import org.codelife.app.killer.constant.SymbolContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.codelife.app.killer.constant.CharSetContract.*;

/**
 * @author csl
 * @create 08/04/2017 17:01
 **/
@Component
public class BackDoorHandler {
    private final Logger logger= LoggerFactory.getLogger(BackDoorHandler.class);

    private File superAminFile;
    private SuperAdminConfig superAdminConfig;

    public BackDoorHandler(){
    }

    public void setResource(final ConfigResources configResources){
      superAdminConfig= configResources.getSuperAdmin();
      superAminFile= Resources.getResourceFile(configResources.getDir(),superAdminConfig.getPath());
    }

    public boolean isValidateTokenFile(){
        if(null==superAminFile)return false;
        if(!superAminFile.exists())return false;
        List<String> lines=null;

        try {
            lines=Files.readLines(superAminFile,Charsets.UTF_8);
        } catch (IOException e) {
            return false;
        }

        if(lines.size()==0)return false;

        // 0 email. 1 generate time. 2 expiration
       String[] configs= lines.get(0).split(SymbolContract.Colon.toString());


        return false;
    }

}
