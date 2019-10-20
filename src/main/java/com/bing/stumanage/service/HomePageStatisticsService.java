package com.bing.stumanage.service;


import com.alibaba.fastjson.JSONObject;


public interface HomePageStatisticsService {
    JSONObject queryCount();

    JSONObject getSevenDayApplyTotal();
}
