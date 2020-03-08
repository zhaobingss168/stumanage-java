package com.bing.stumanage.service;

import com.bing.stumanage.entity.SysConfig;
import com.bing.stumanage.utils.PageUtil;

import java.util.List;

public interface SysConfigService {
    PageUtil querySysConfigByPage(String configName, Integer page, Integer size);

    SysConfig save(SysConfig sysConfig);

    SysConfig update(SysConfig sysConfig);

    void delete(List<SysConfig> sysConfigList);
}
