package com.bing.stumanage.controller;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.service.SysUserService;
import com.bing.stumanage.uitls.PageUtil;
import com.bing.stumanage.uitls.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 管理员用户管理
 * @Author zhaobing
 * @Date 2019/10/19 9:19
 **/
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;// 系统管理员service

    /**
     * 分页查询报名信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/queryByPage")
    public ResponseMessage queryByPage(String userName,String nickName,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        PageUtil pageUtil = sysUserService.queryByPage(userName,nickName,page,size);
        if(null == pageUtil){
            return ResponseMessage.error();
        }else{
            return ResponseMessage.success(pageUtil);
        }
    }

    @PostMapping("/save")
    public ResponseMessage save(@RequestBody SysUser sysUser){
        try{
            SysUser result = sysUserService.save(sysUser);
            if(null == result){
                return ResponseMessage.error();
            }else{
                return ResponseMessage.success(result);
            }
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }

    /**
     * 修改
     * @param sysUser
     * @return
     */
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody SysUser sysUser){
        try{
            SysUser result = sysUserService.update(sysUser);
            if(null == result){
                return ResponseMessage.error();
            }else{
                return ResponseMessage.success(result);
            }
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResponseMessage delete(Integer id){
        try{
            sysUserService.delete(id);
            return ResponseMessage.success();
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }
}
