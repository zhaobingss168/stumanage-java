package com.bing.stumanage.utils;

import com.bing.stumanage.entity.SysConfig;
import com.bing.stumanage.repository.SysConfigRepository;


/**
 * create by jacktong
 * date 2018/9/1 下午12:51
 **/
public class ConfigUtils {

    private static SysConfigRepository sysConfigRepository;
    /**
     * 加载系统配置
     * @param code
     * @return
     */
    public static String getSysConfig(String code) {
        sysConfigRepository = SpringUtil.getBean(SysConfigRepository.class);
        SysConfig sc = sysConfigRepository.findByConfigCode(code);
        if (null == sc) {
            return null;
        } else {
            return sc.getConfigValue();
        }
    }

    public static String getPort(){
        return getSysConfig("port");
    }

    public static String getIp(){
        return getSysConfig("ip");
    }

    public static String getFilePath(){
        return getSysConfig("filePath");
    }

}
