package com.bing.stumanage.controller;

import com.bing.stumanage.entity.SysMenu;
import com.bing.stumanage.service.SysMenuService;
import com.bing.stumanage.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 菜单控制器
 * @Author zhaobing
 * @Date 2020/1/26 16:32
 **/
@RestController
@RequestMapping("/sysMenu")
@Api(tags={"菜单相关操作接口"})
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;// 菜单service


    /**
     * 新增菜单,修改菜单
     * @param sysMenu
     * @return
     */
    @PostMapping(value="/save")
    @ApiOperation(value="新增菜单,修改菜单",notes="暂无")
    public ResponseMessage save(@RequestBody SysMenu sysMenu) {
        try {
            SysMenu result = sysMenuService.save(sysMenu);
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
     * 删除，支持批量删除
     * @param sysMenuList
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value="删除，支持批量删除",notes="暂无")
    public ResponseMessage delete(@RequestBody List<SysMenu> sysMenuList){
        try{
            sysMenuService.delete(sysMenuList);
            return ResponseMessage.success();
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }


    /**
     * 根据用户名查询菜单左侧导航树
     * @param userName 用户名
     * @return
     */
    @GetMapping("/findNavTree")
    @ApiOperation(value="根据用户名查询菜单左侧导航树",notes="暂无")
    public ResponseMessage findNavTree(@RequestParam String userName){
        return ResponseMessage.success(sysMenuService.findNavTreeByUserName(userName,1));
    }

    /**
     * 查询出所有菜单
     * @return
     */
    @GetMapping("/findMenuTree")
    @ApiOperation(value="查询出所有菜单",notes="暂无")
    public ResponseMessage findMenuTree(){
        return ResponseMessage.success(sysMenuService.findNavTreeByUserName(null,0));
    }
}
