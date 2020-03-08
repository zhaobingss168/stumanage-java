package com.bing.stumanage.controller;

import com.bing.stumanage.entity.AppApplyInfo;
import com.bing.stumanage.service.AppApplyInfoService;
import com.bing.stumanage.utils.PageUtil;
import com.bing.stumanage.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Description 报名信息管理
 * @Author zhaobing
 * @Date 2019/9/25 21:48
 **/
@RestController
@RequestMapping("/appApplyInfoManage")
@Api(tags={"报名信息操作接口"})
public class AppApplyInfoManageController {

    @Autowired
    private AppApplyInfoService appApplyInfoService;// 报名信息service
    /**
     * 分页查询报名信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value="分页查询报名信息",notes="暂无")
    public ResponseMessage queryByPage(String stuName,Integer payStatus,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        PageUtil pageUtil = appApplyInfoService.queryByPage(stuName,payStatus,page,size);
        if(null == pageUtil){
            return ResponseMessage.error();
        }else{
            return ResponseMessage.success(pageUtil);
        }
    }

    /**
     * 新增报名信息
     * @param appApplyInfo
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value="新增报名信息",notes="暂无")
    public ResponseMessage save(@RequestBody AppApplyInfo appApplyInfo){
        try{
            AppApplyInfo result = appApplyInfoService.save(appApplyInfo);
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
     * @param appApplyInfo
     * @return
     */
    @ApiOperation(value="修改报名信息",notes="暂无")
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody AppApplyInfo appApplyInfo){
        try{
            AppApplyInfo result = appApplyInfoService.update(appApplyInfo);
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
     * @param appApplyInfoList
     * @return
     */
    @ApiOperation(value="删除报名信息",notes="暂无")
    @PostMapping("/delete")
    public ResponseMessage delete(@RequestBody List<AppApplyInfo> appApplyInfoList){
        try{
            appApplyInfoService.delete(appApplyInfoList);
            return ResponseMessage.success();
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }

    /**
     * 获取所有，用于导出excel表格
     * @return
     */
    @GetMapping("/queryAll")
    @ApiOperation(value="获取所有，用于导出excel表格",notes="暂无")
    public ResponseMessage queryAll(String stuName,Integer payStatus){
        List<AppApplyInfo> list = appApplyInfoService.queryAll(stuName,payStatus);
        return ResponseMessage.success(list);
    }

}
