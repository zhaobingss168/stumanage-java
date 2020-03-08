package com.bing.stumanage.service.impl;

import com.bing.stumanage.service.SysConfigService;
import com.bing.stumanage.entity.SysConfig;
import com.bing.stumanage.repository.SysConfigRepository;
import com.bing.stumanage.utils.CopyProperties;
import com.bing.stumanage.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * 系统配置service
 * @author zhaobing
 * @date 2020/1/30
 */
@Service
@Transactional
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigRepository sysConfigRepository;

    /**
     * 分页查询
     * @param configName 系统配置名称
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageUtil querySysConfigByPage(String configName,Integer page, Integer size) {
        List<SysConfig> list = sysConfigRepository.querySysConfigByPage(configName,(page-1)*size,size);
        int count = sysConfigRepository.querySysConfigByPageTotalCount(configName);
        PageUtil pageList = new PageUtil(page,size,count,list);
        return pageList;
    }

    /**
     * 保存
     * @param sysConfig
     * @return
     */
    @Override
    public SysConfig save(SysConfig sysConfig){
        return sysConfigRepository.save(sysConfig);
    }

    /**
     * 修改
     * @param sysConfig
     * @return
     */
    @Override
    public SysConfig update(SysConfig sysConfig){
        Optional<SysConfig> optional = sysConfigRepository.findById(sysConfig.getId());
        if(optional.isPresent()){
            SysConfig result = optional.get();
            CopyProperties.copyPropertiesIgnoreNull(sysConfig,result);
            return sysConfigRepository.save(result);
        }else{
            return null;
        }
    }

    /**
     * 删除，支持批量删除
     * @param sysConfigList
     */
    @Override
    public void delete(List<SysConfig> sysConfigList){
        for (SysConfig sysConfig : sysConfigList) {
            sysConfigRepository.deleteById(sysConfig.getId());
        }
    }

}