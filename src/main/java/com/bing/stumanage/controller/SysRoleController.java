package com.bing.stumanage.controller;

import com.bing.stumanage.entity.SysRole;
import com.bing.stumanage.entity.SysRoleMenu;
import com.bing.stumanage.service.SysRoleService;
import com.bing.stumanage.utils.PageUtil;
import com.bing.stumanage.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 角色控制器
 * @Author zhaobing
 * @Date 2020/1/27 21:12
 **/
@RestController
@RequestMapping("/sysRole")
@Api(tags={"角色相关操作接口"})
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;// 角色service


    /**
     * 分页查询角色信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value="分页查询角色信息",notes="暂无")
    public ResponseMessage queryByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam String roleName) {
        PageUtil pageUtil = sysRoleService.queryByPage(roleName,page, size);
        if (null == pageUtil) {
            return ResponseMessage.error();
        } else {
            return ResponseMessage.success(pageUtil);
        }
    }

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value="新增角色",notes="暂无")
    public ResponseMessage save(@RequestBody SysRole sysRole) {
        try {
            SysRole result = sysRoleService.save(sysRole);
            if (null == result) {
                return ResponseMessage.error();
            } else {
                return ResponseMessage.success(result);
            }
        } catch (Exception e) {
            return ResponseMessage.error();
        }
    }

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value="修改角色",notes="暂无")
    public ResponseMessage update(@RequestBody SysRole sysRole) {
        try {
            SysRole result = sysRoleService.update(sysRole);
            if (null == result) {
                return ResponseMessage.error();
            } else {
                return ResponseMessage.success(result);
            }
        } catch (Exception e) {
            return ResponseMessage.error();
        }
    }

    /**
     * 删除角色，支持批量删除
     * @param records
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value="删除角色，支持批量删除",notes="暂无")
    public ResponseMessage delete(@RequestBody List<SysRole> records) {
        try {
            sysRoleService.delete(records);
            return ResponseMessage.success();
        } catch (Exception e) {
            return ResponseMessage.error();
        }
    }

    /**
     * 查询所有角色信息
     * @return
     */
    @GetMapping("/findAllRoles")
    @ApiOperation(value="查询所有角色信息",notes="暂无")
    public ResponseMessage findAllRoles() {
        return ResponseMessage.success(sysRoleService.findAllRoles());
    }

    /**
     * 根据角色id查询目录
     * @param roleId
     * @return
     */
    @GetMapping("/findRoleMenus")
    @ApiOperation(value="根据角色id查询目录",notes="暂无")
    public ResponseMessage findRoleMenus(@RequestParam Integer roleId) {
        return ResponseMessage.success(sysRoleService.findRoleMenus(roleId));
    }

    /**
     * 保存角色目录
     * @param records
     * @return
     */
    @PostMapping("/saveRoleMenus")
    @ApiOperation(value="保存角色目录",notes="暂无")
    public ResponseMessage saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        try {
            sysRoleService.saveRoleMenus(records);
            return ResponseMessage.success();
        } catch (Exception e) {
            return ResponseMessage.error();
        }
    }

}
