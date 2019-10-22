package com.bing.stumanage.controller;

import com.bing.stumanage.entity.ApplyInfo;
import com.bing.stumanage.service.ApplyInfoService;
import com.bing.stumanage.uitls.PageUtil;
import com.bing.stumanage.uitls.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseMessage queryByPage(String stuName,Integer payStatus,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        PageUtil pageUtil = applyInfoService.queryByPage(stuName,payStatus,page,size);
        if(null == pageUtil){
            return ResponseMessage.error();
        }else{
            return ResponseMessage.success(pageUtil);
        }
    }

    @PostMapping("/save")
    public ResponseMessage save(@RequestBody ApplyInfo applyInfo){
        try{
            ApplyInfo result = applyInfoService.save(applyInfo);
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
     * @param applyInfo
     * @return
     */
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody ApplyInfo applyInfo){
        try{
            ApplyInfo result = applyInfoService.update(applyInfo);
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
            applyInfoService.delete(id);
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
    public ResponseMessage queryAll(String stuName,Integer payStatus){
        List<ApplyInfo> list = applyInfoService.queryAll(stuName,payStatus);
        return ResponseMessage.success(list);
    }


    @PostMapping("/mobileSave")
    public ResponseMessage mobileSave(@RequestBody ApplyInfo applyInfo){
        try{
            ApplyInfo result = applyInfoService.save(applyInfo);
            if(null == result){
                return ResponseMessage.error();
            }else{
                return ResponseMessage.success(result);
            }
        }catch (Exception e){
            return ResponseMessage.error();
        }
    }

}
