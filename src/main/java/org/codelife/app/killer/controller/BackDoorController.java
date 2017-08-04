package org.codelife.app.killer.controller;

import org.codelife.app.killer.adapter.BaseControllerAdapter;
import org.codelife.app.killer.configuration.ResourceConfig;
import org.codelife.app.killer.constant.SymbolContract;
import org.codelife.app.killer.handler.BackDoorHandler;
import org.codelife.app.killer.security.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 *  usage:
 *      1. token generate program
 *      2. enter and open
 *      this controller which read and load the module.json file.then init basic feature to continue.
 * @author csl
 * @create 07/27/2017 11:31
 * @desc super admin only
 **/
@RequestMapping("/backdoor")
@Controller
public class BackDoorController extends BaseControllerAdapter{
    private final Logger LOGGER=LoggerFactory.getLogger(BackDoorController.class);

    BackDoorHandler backDoorHandler;

    @Autowired
    BackDoorController(ResourceConfig resourceConfig,BackDoorHandler backDoorHandler){
        this.backDoorHandler=backDoorHandler;
        this.backDoorHandler.setResource(resourceConfig.getConfigs());
    }

    @RequestMapping("/")
    public ModelAndView index(){
          boolean isValidateTokenFile=backDoorHandler.isValidateTokenFile();
        return modelAndViewProxy("console/backdoor/index")
                .addObject("",isValidateTokenFile)
                .toMAV();
    }


    @PostMapping("/validate.do")
    public String doValidate(@RequestParam("email")String email,@RequestParam("file")MultipartFile file,HttpSession session) throws IOException {

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF-8"));
        String line=bufferedReader.readLine().trim();
            bufferedReader.close();
        String[] tokenLine = line.split(SymbolContract.Colon.toString());
        String token=tokenLine[0];

        Date startTime=new Date(Long.valueOf(tokenLine[1]));
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(Calendar.MINUTE,Integer.valueOf(tokenLine[2]));

        Date expiredTime=calendar.getTime();

        boolean isCerExpried=expiredTime.before(new Date());
        if(isCerExpried){
            LOGGER.info("---证书已经过期---");
        }
        boolean isValidToken=PasswordUtils.isValid("NYC"+email,tokenLine[0]);
        LOGGER.info("令牌:{},时间:{},期限:{},令牌是否有效:{}",tokenLine[0],tokenLine[1],tokenLine[2],isValidToken);

        if(!isCerExpried&&isValidToken){
            session.setAttribute(KEY_CER_VALIDATE,true);
        }else {
            session.setAttribute(KEY_CER_VALIDATE,false);
        }

        return "redirect:/door/";
    }

    @ResponseBody
    @PostMapping("/enter.do")
    public ResponseEntity<ByteArrayResource> doApplyFile(@RequestParam("email")String email, HttpSession session) throws IOException {
      final String token= PasswordUtils.encodeByBcrypt("NYC"+email);
      final String lineFormat=token+SymbolContract.Colon+System.currentTimeMillis()+SymbolContract.Colon+"20"; // expired at 20 minutes

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Cache-Control","no-cache,no-store,must-revalidate");
        httpHeaders.add("Content-Disposition",String.format("attachment; filename=\"%s\"","init.token"));
        httpHeaders.add("Pragma","no-cache");
        httpHeaders.add("Expires","0");

        ByteArrayResource byteArrayResource=new ByteArrayResource(lineFormat.getBytes());

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentLength(lineFormat.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(byteArrayResource);
    }



}
