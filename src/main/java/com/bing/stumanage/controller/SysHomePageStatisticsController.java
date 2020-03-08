package com.bing.stumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.bing.stumanage.service.SysHomePageStatisticsService;
import com.bing.stumanage.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 主页统计数据
 * @Author zhaobing
 * @Date 2019/10/19 20:48
 **/
@RestController
@RequestMapping("/homePageStatistics")
@Api(tags={"管理员首页数据统计相关操作接口"})
public class SysHomePageStatisticsController {

    @Autowired
    private SysHomePageStatisticsService sysHomePageStatisticsService; // 主页统计数据service

    /**
     * 获取统计数据，报名总人数，未支付人数,已支付人数，报名支付比例
     * @return
     */
    @GetMapping("/headcount")
    @ApiOperation(value="获取统计数据，报名总人数，未支付人数,已支付人数，报名支付比例",notes="暂无")
    public ResponseMessage headcount(){
        return ResponseMessage.success(sysHomePageStatisticsService.queryCount());
    }

    /**
     * 获取近7日报名数
     * @return
     */
    @ApiOperation(value="获取近7日报名数",notes="暂无")
    @GetMapping("/getSevenDayApplyTotal")
    public ResponseMessage getSevenDayApplyTotal() {
        try {
            JSONObject json = sysHomePageStatisticsService.getSevenDayApplyTotal();
            if (null != json) {
                return ResponseMessage.success(json);
            } else {
                return ResponseMessage.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error();
        }
    }

}
