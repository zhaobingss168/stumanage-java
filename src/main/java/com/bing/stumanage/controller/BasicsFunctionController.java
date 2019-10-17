package com.bing.stumanage.controller;

import com.bing.stumanage.service.BasicFunctionService;
import com.bing.stumanage.uitls.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 基础功能（修改密码，退出登录）
 * @Author zhaobing
 * @Date 2019/10/17 19:23
 **/
@RestController
@RequestMapping("/basicsFunction")
public class BasicsFunctionController {

    @Autowired
    private BasicFunctionService basicFunctionService;
    /**
     * 密码修改
     * @param userName  用户名
     * @param oldPwd   原始密码
     * @param newPwd  新密码
     * @return
     */
    @PostMapping("/updatePwd")
    public ResponseMessage updatePwd(String userName,String oldPwd,String newPwd){
        boolean result = basicFunctionService.updatePwd(userName,oldPwd,newPwd);
        if(result){
            return ResponseMessage.success();
        }else{
            return ResponseMessage.errorWithMsg("oldPwdError");
        }
    }

    /**
     * 退出登录
     * @param userName 用户名
     * @return
     */
    @PostMapping("/logOut")
    public ResponseMessage logOut(String userName){
        basicFunctionService.logOut(userName);
        return ResponseMessage.success();
    }
}
