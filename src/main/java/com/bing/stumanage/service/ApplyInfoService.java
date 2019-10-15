package com.bing.stumanage.service;

import com.bing.stumanage.entity.ApplyInfo;
import com.bing.stumanage.uitls.PageUtil;

import java.util.List;

public interface ApplyInfoService {
    PageUtil queryByPage(String stuName,Integer payStatus,Integer page, Integer size);

    ApplyInfo save(ApplyInfo applyInfo);

    ApplyInfo update(ApplyInfo applyInfo);

    void delete(Integer id);

    List<ApplyInfo> queryAll(String stuName,Integer payStatus);
}
