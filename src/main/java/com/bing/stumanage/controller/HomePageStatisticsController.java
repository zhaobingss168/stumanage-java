package com.bing.stumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.bing.stumanage.service.HomePageStatisticsService;
import com.bing.stumanage.uitls.ResponseMessage;
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
public class HomePageStatisticsController {

    @Autowired
    private HomePageStatisticsService homePageStatisticsService; // 主页统计数据service

    /**
     * 获取统计数据，报名总人数，未支付人数,已支付人数，报名支付比例
     * @return
     */
    @GetMapping("/headcount")
    public ResponseMessage headcount(){
        return ResponseMessage.success(homePageStatisticsService.queryCount());
    }

    /**
     * 获取近7日报名数
     * @return
     */
    @GetMapping("/getSevenDayApplyTotal")
    public ResponseMessage getSevenDayApplyTotal() {
        try {
            JSONObject json = homePageStatisticsService.getSevenDayApplyTotal();
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
