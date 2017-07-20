package org.codelife.app.killer.configuration;

import java.io.File;

/**
 * String resource class
 *
 * @author csl
 * @create 07/20/2017 14:44
 **/
public class StringResources extends Resources{
    private String infoPath;
    public StringResources(){}

    public String getInfoPath() {
        return this.infoPath;
    }

    public void setInfoPath(String infoPath) {
        this.infoPath = infoPath;
    }

    public File getInfoFile(){
        return Resources.getResourceFile(getDir(),getInfoPath());
    }
}

