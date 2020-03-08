package com.bing.stumanage.controller;

import com.bing.stumanage.entity.SysConfig;
import com.bing.stumanage.service.SysConfigService;
import com.bing.stumanage.utils.PageUtil;
import com.bing.stumanage.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 系统配置
 * @Author zhaobing
 * @Date 2020/1/30 10:34
 **/
@RestController
@RequestMapping("/sysConfig")
@Api(tags={"系统配置相关操作接口"})
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;// 系统配置service

    /**
     * 查询参数列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/querySysConfigByPage")
    @ApiOperation(value="分页查询系统参数配置",notes="暂无")
    public ResponseMessage querySysConfigByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String configName
    ) {
        PageUtil pageUtil = sysConfigService.querySysConfigByPage(configName,page, size);
        if (pageUtil == null) {
            return ResponseMessage.error();
        } else {
            return ResponseMessage.success(pageUtil);
        }
    }

    /**
     * 新增配置
     * @param sysConfig 参数对象
     * @return 增加的参数结果
     */
    @PostMapping("/save")
    @ApiOperation(value="新增配置",notes="暂无")
    public ResponseMessage save(@RequestBody SysConfig sysConfig) {
        try {
            SysConfig result = sysConfigService.save(sysConfig);
            if (result == null) {
                return ResponseMessage.error();
            } else {
                return ResponseMessage.success(result);
            }
        } catch (Exception e) {
            return ResponseMessage.error();
        }
    }

    /**
     * 修改配置
     * @param sysConfig 属性对象
     * @return 修改后的属性对象
     */
    @PostMapping("/update")
    @ApiOperation(value="修改配置",notes="暂无")
    public ResponseMessage update(@RequestBody SysConfig sysConfig) {
        try {
            SysConfig result = sysConfigService.update(sysConfig);
            if (result == null) {
                return ResponseMessage.error();
            } else {
                return ResponseMessage.success(result);
            }
        } catch (Exception e) {
            return ResponseMessage.error();
        }
    }

    /**
     * 删除配置，支持批量删除
     * @param sysConfigList
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value="删除配置，支持批量删除",notes="暂无")
    public ResponseMessage delete(@RequestBody List<SysConfig> sysConfigList) {
        try {
            sysConfigService.delete(sysConfigList);
            return ResponseMessage.success();
        } catch (Exception e) {
            return ResponseMessage.error();
        }
    }
}