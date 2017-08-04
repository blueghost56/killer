package org.codelife.app.killer.configuration;

import lombok.Data;

import java.io.File;

/**
 * String resource class
 *
 * @author csl
 * @create 07/20/2017 14:44
 **/
@Data
public class StringResources extends Resources{
    private String infoPath;
    public StringResources(){}

    public File getInfoFile(){
        return Resources.getResourceFile(getDir(),getInfoPath());
    }
}

