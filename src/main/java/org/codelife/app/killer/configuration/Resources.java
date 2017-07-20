package org.codelife.app.killer.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Resources {
    private final static Logger LOGGER= LoggerFactory.getLogger(Resources.class);

    private String dir;
    public Resources(){}

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public static File getResourceFile(final String dir,final String path){
        final String filePath=dir+path;

        LOGGER.info("资源目录: {}",filePath);
        try {
            return ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    public static String getResroucePath(final String dir,final String path){
        return getResourceFile(dir,path).getAbsolutePath();
    }
}
