package com.bing.stumanage.service;

import com.bing.stumanage.entity.AppApplyInfo;
import com.bing.stumanage.utils.PageUtil;

import java.util.List;

public interface AppApplyInfoService {
    PageUtil queryByPage(String stuName,Integer payStatus,Integer page, Integer size);

    AppApplyInfo save(AppApplyInfo appApplyInfo);

    AppApplyInfo update(AppApplyInfo appApplyInfo);

    void delete(List<AppApplyInfo> appApplyInfoList);

    List<AppApplyInfo> queryAll(String stuName, Integer payStatus);
}
