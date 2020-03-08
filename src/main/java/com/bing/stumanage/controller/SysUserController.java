package com.bing.stumanage.controller;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.service.SysUserService;
import com.bing.stumanage.utils.PageUtil;
import com.bing.stumanage.utils.ResponseMessage;
import com.bing.stumanage.vo.UpdatePasswordBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 系统用户controller
 * @Author zhaobing
 * @Date 2020/1/25 11:02
 **/
@RestController
@RequestMapping("/sysUser")
@Api(tags={"用户相关操作接口"})
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;// 系统管理员service

    /**
     * 分页查询系统用户信息
     * @param page
     * @param size
     * @param username 系统用户用户名
     * @param status 系统用户用户状态
     * @return
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value="分页查询系统用户信息",notes="暂无")
    public ResponseMessage queryByPage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam String username,
                                       @RequestParam Integer status){
        PageUtil pageUtil = sysUserService.queryByPage(username,status,page,size);
        if(null == pageUtil){
            return ResponseMessage.error();
        }else{
            return ResponseMessage.success(pageUtil);
        }
    }

    /**
     * 新增或修改系统用户
     * @param sysUser
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value="新增用户信息",notes="暂无")
    public ResponseMessage save(@RequestBody SysUser sysUser){
        try{
            return sysUserService.save(sysUser);
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
    @ApiOperation(value="修改用户信息",notes="暂无")
    public ResponseMessage update(@RequestBody SysUser sysUser){
        try{
            return sysUserService.update(sysUser);
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }

    /**
     * 删除，支持批量删除
     * @param records 用户的userid集合
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value="删除用户信息",notes="暂无")
    public ResponseMessage delete(@RequestBody List<SysUser> records){
        try{
            sysUserService.delete(records);
            return ResponseMessage.success();
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }

    /**
     * 根据用户名查询用户权限
     * @param userName
     * @return
     */
    @GetMapping("/findPermissions")
    @ApiOperation(value="根据用户名查询用户权限",notes="暂无")
    public ResponseMessage findPermissions(@RequestParam String userName) {
        return ResponseMessage.success(sysUserService.findPermissionsByUserName(userName));
    }


    /**
     * 系统用户修改密码
     * @param updatePasswordBean
     * @return
     */
    @PostMapping("/updatePassword")
    @ApiOperation(value="系统用户修改密码",notes="暂无")
    public ResponseMessage updatePassword(@RequestBody UpdatePasswordBean updatePasswordBean){
        try{
            return sysUserService.updatePassword(updatePasswordBean);
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }

}
