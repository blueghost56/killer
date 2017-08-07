package org.codelife.app.killer.handler;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.codelife.app.killer.configuration.ConfigResources;
import org.codelife.app.killer.configuration.Resources;
import org.codelife.app.killer.configuration.security.SuperAdminConfig;
import org.codelife.app.killer.constant.SymbolContract;
import org.codelife.app.killer.factory.SuperAdminFactory;
import org.codelife.app.killer.mapper.console.SuperAdminMapper;
import org.codelife.app.killer.security.PasswordUtils;
import org.codelife.app.killer.service.impl.SuperAdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author csl
 * @create 08/04/2017 17:01
 **/
@Component
public final class BackDoorHandler {
    private final Logger logger= LoggerFactory.getLogger(BackDoorHandler.class);

    private File superAminFile;
    private SuperAdminConfig superAdminConfig;

    @Autowired
    SuperAdminServiceImpl superAdminService;

    public BackDoorHandler(){
    }

    public void setResource(final ConfigResources configResources){
      superAdminConfig= configResources.getSuperAdmin();
      superAminFile= Resources.getResourceFile(configResources.getDir(),superAdminConfig.getPath());
    }

    /**
     * account is a validated
     * @return boolean
     */
    public boolean verifyTokenFile(){
       return isValidateTokenFile(superAminFile,superAdminConfig.getFileExpiration());
    }

    public boolean verifyToken(final String email, final InputStream inputStream,final String fileName) throws IOException {
        if(!fileName.endsWith(superAdminConfig.getSuffix())){
            logger.info("文件类型不正确!");
            return false;
        }

        String[] arguments=getTokenArgumentsFromFile(superAminFile,superAdminConfig.getArguments());
        if(null==arguments)return false;

        // verify the account is ok?
        boolean isUploadTokenValidated=isValidatedTokenArguments(arguments,superAdminConfig.getPrefix(),email,superAdminConfig.getExpiration());
        if(!isUploadTokenValidated)return false;

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,Charsets.UTF_8));
        String line=bufferedReader.readLine();
        String[] tokenArguments=parseTokenString(line,superAdminConfig.getArguments());

        if(null==tokenArguments) return false;
        // verify the uplodate file's token arguments are the same with local file's token arguments
        for(int i=0;i<superAdminConfig.getArguments();i++){
            if(!arguments[i].equals(tokenArguments[i])){
                logger.info("上传文件与配置文件内容不符！");
                return false;
            }
        }

       boolean isLocalTokenValidated=isValidatedTokenArguments(tokenArguments,superAdminConfig.getPrefix(),email,superAdminConfig.getExpiration());
       return (isUploadTokenValidated&&isLocalTokenValidated);
    }

    public void createSuperAdminAccount(final String account, final HttpServletRequest request){
        superAdminService.save(SuperAdminFactory.create());
    }

    /**
     * generate token with account and write to the local config file
     * @param account
     * @return String
     */
    public final String getSuperAdminToken(final String account){
        String token= generateToken(superAdminConfig.getPrefix(),account);
        token=(token+SymbolContract.Colon+System.currentTimeMillis()+SymbolContract.Colon+superAdminConfig.getExpiration());
        writeTokenToConfigFile(token,superAminFile);
        return token;
    }

    public final String getTokenFileName(){
        return (superAdminConfig.getFileName()+SymbolContract.Point+superAdminConfig.getSuffix());
    }

    private void writeTokenToConfigFile(final String token,final File filepath){
        logger.info("文件写入路径: {}-{}",filepath.getAbsolutePath(),token);
        FileOutputStream fileOutputStream=null;
        try {
             fileOutputStream=new FileOutputStream(filepath);
            fileOutputStream.write(token.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("无法写入配置文件!");
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private final String generateToken(final String prefix,final String content){
        return PasswordUtils.encodeByBcrypt((prefix+content));
    }

    private boolean isValidateTokenFile(final File tokenFile,final int fileExpiration){
        if(null==tokenFile)return false;
        if(!tokenFile.exists()){
            logger.info("令牌文件不存在!");
            return false;
        }
        if(tokenFile.length()<=0){
            logger.info("令牌文件内容为空(0)!");
            return false;
        }
        Date lastFileModifiedDate=new Date(tokenFile.lastModified());
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(lastFileModifiedDate);
        calendar.add(Calendar.MINUTE,fileExpiration);
        if(calendar.getTime().after(new Date()))return true;

        logger.info("令牌文件创建日期过期!");
        return false;
    }


    // get arguments from a nonnull and exists file
    private  String[] getTokenArgumentsFromFile(final File file,final int argumentNumber){
        List<String> lines=null;
        try {
            lines=Files.readLines(file,Charsets.UTF_8);
        } catch (IOException e) {
            logger.info("令牌文件解析: {}",e.getLocalizedMessage());
            return null;
        }

        if(lines.size()==0){
            logger.info("令牌文件内容为空!");
            return null;
        }

        return parseTokenString(lines.get(0),argumentNumber);
    }

    private String[] parseTokenString(final String tokenStr,final int argumentNumber){

        // 0 email. 1 generate timestamp. 2 expiration
        String[] arguments=null;
        try {
            arguments=tokenStr.split(SymbolContract.Colon.toString());
        }catch (Exception e){
            logger.info("令牌文件内容格式: {}",e.getLocalizedMessage());
            return null;
        }

        if(arguments.length!=argumentNumber){
            logger.info("令牌文件参数个数必须为: {}",argumentNumber);
            return null;
        }

        logger.info("文件信息: {}-{}-{}",arguments[0],arguments[1],arguments[2]);

        return arguments;
    }

    /**
     *
     * @param arguments - token argument which to verify
     * @param tokenPrefix - token=prefx+content
     * @param expiration - unit is minute
     * @return
     */
    private boolean isValidatedTokenArguments(final String[] arguments,final String tokenPrefix,final String content,final int expiration){
        String token=arguments[0];
        long gen=Long.valueOf(arguments[1]); // unit:millseconds.The date of generate token
        int exp=Integer.valueOf(arguments[2]); // unit: minute

        if(exp!=expiration)return false;

        if(isTokenExpiration(gen,exp)) return false;

        return isTokenValidated(token,(tokenPrefix+content));
    }

    private boolean isTokenExpiration(final long generateTime,final int expiration){
        Date startTime=new Date(generateTime);
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE,expiration);

        Date expirationTime=calendar.getTime();
        boolean isExpiration=expirationTime.before(new Date());
        if(isExpiration){
            logger.info("令牌过期无效!");
        }
        return isExpiration;
    }

    private boolean isTokenValidated(final String token,final String content){
        return PasswordUtils.isValid(content,token);
    }

}
