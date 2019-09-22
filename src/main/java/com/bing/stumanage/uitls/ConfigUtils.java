package com.bing.stumanage.uitls;

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

    public static String getQrcodePath(){
        return getSysConfig("qrcodePath");
    }

    public static String getQrcodeContent(){
        return getSysConfig("qrcodeContent");
    }

    public static String getWechat_app_id() {

        return "";
    }

    public static String getWechat_secret() {

        return "";
    }

    public static String getWechat_pay_mch_id() {
        return "";
    }

    public static String getWechat_notice_url() {

        return "";
    }

    public static String getWechat_pay_key() {
        return "";
    }

    public static String getYsAppKey(){
        return getSysConfig("ysAppKey");
    }

    public static String getYsAppSecret(){
        return getSysConfig("ysAppSecret");
    }
}
