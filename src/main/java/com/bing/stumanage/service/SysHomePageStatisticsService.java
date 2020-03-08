package com.bing.stumanage.service;


import com.alibaba.fastjson.JSONObject;


public interface SysHomePageStatisticsService {
    JSONObject queryCount();

    JSONObject getSevenDayApplyTotal();
}
