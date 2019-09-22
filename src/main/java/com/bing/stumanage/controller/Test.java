package com.bing.stumanage.controller;

import com.bing.stumanage.uitls.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试
 * @Author zhaobing
 * @Date 2019/9/16 21:02
 **/
@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/test")
    public ResponseMessage test(){
       return ResponseMessage.success();
    }
}
