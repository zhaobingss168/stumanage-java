package com.bing.stumanage.controller;

import com.bing.stumanage.service.ApplyInfoService;
import com.bing.stumanage.uitls.PageUtil;
import com.bing.stumanage.uitls.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 报名信息管理
 * @Author zhaobing
 * @Date 2019/9/25 21:48
 **/
@RestController
@RequestMapping("/applyInfoManage")
public class ApplyInfoManageController {

    @Autowired
    private ApplyInfoService applyInfoService;// 报名信息service
    /**
     * 分页查询报名信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/queryByPage")
    public ResponseMessage queryByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        PageUtil pageUtil = applyInfoService.queryByPage(page,size);
        if(null == pageUtil){
            return ResponseMessage.error();
        }else{
            return ResponseMessage.success(pageUtil);
        }
    }
}
