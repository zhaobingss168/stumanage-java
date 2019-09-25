package com.bing.stumanage.service.impl;

import com.bing.stumanage.entity.ApplyInfo;
import com.bing.stumanage.repository.ApplyInfoRepository;
import com.bing.stumanage.service.ApplyInfoService;
import com.bing.stumanage.uitls.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 分页查询报名信息service
 * @Author zhaobing
 * @Date 2019/9/25 21:57
 **/
@Service
@Transactional
public class ApplyInfoServiceImpl implements ApplyInfoService{

    @Autowired
    private ApplyInfoRepository applyInfoRepository;

    @Override
    public PageUtil queryByPage(Integer page, Integer size) {
        List<ApplyInfo> list = applyInfoRepository.queryByPage((page-1)*size, size);
        int count = applyInfoRepository.queryByPageTotalCount();
        PageUtil pageList = new PageUtil(page,size,count,list);
        return pageList;
    }
}
