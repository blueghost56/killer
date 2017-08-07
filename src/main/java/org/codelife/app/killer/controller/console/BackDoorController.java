package org.codelife.app.killer.controller.console;

import com.google.common.base.Strings;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.codelife.app.killer.adapter.BaseControllerAdapter;
import org.codelife.app.killer.configuration.ResourceConfig;
import org.codelife.app.killer.handler.BackDoorHandler;
import org.codelife.app.killer.util.ViewKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;

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

    /**
     *  verify certificate file:
     *  a. show the validate page
     *  b. deny to access
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(){
        boolean isTokenFileValidated=backDoorHandler.verifyTokenFile();
        return modelAndViewProxy("console/backdoor/index")
                .addObject("showVerifyPage",isTokenFileValidated)
                .toMAV();
    }

    @PostMapping("/validate.do")
    public String doValidate(@RequestParam("email")String email, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        boolean isHandleSuccess=false;

        if(!Strings.isNullOrEmpty(email) && null!=file && !file.isEmpty()){
            isHandleSuccess=true;
        }

        boolean isTokenValidated=isHandleSuccess?backDoorHandler.verifyToken(email,file.getInputStream(),file.getOriginalFilename()):false;
        if(isTokenValidated){
            backDoorHandler.createSuperAdminAccount(email,request);
        }

        String forwardUrl= ViewKit.redirect("door");
        if(isTokenValidated){
            forwardUrl=ViewKit.redirect("http://www.baidu.com");
        }
        return forwardUrl;
    }

    @ResponseBody
    @PostMapping("/enter.do")
    public ResponseEntity<ByteArrayResource> doApplyFile(@RequestParam("email")String email, HttpSession session) throws IOException {
       final String lineFormat=backDoorHandler.getSuperAdminToken(email);
       final String fileName=backDoorHandler.getTokenFileName();

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Cache-Control","no-cache,no-store,must-revalidate");
        httpHeaders.add("Content-Disposition",String.format("attachment; filename=\"%s\"",fileName));
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
