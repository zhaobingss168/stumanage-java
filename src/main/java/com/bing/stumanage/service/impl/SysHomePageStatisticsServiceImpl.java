package com.bing.stumanage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bing.stumanage.repository.HomePageStatisticsRepository;
import com.bing.stumanage.service.SysHomePageStatisticsService;
import com.bing.stumanage.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 主页统计数据
 * @Author zhaobing
 * @Date 2019/10/19 21:36
 **/
@Service
@Transactional
public class SysHomePageStatisticsServiceImpl implements SysHomePageStatisticsService {

    @Autowired
    private HomePageStatisticsRepository homePageStatisticsRepository;

    /**
     * 获取统计数据，报名总人数，未支付人数,已支付人数，报名支付比例
     * @return
     */
    @Override
    public JSONObject queryCount(){
        JSONObject data = new JSONObject();
        List<Object> list = homePageStatisticsRepository.queryCount();
        for(Object obj : list){
            Object[] bean = (Object[]) obj;
            data.put("totalPeople",dataConvert(bean[0]));// 总人数
            data.put("pay0",dataConvert(bean[1])); // 未支付人数
            data.put("pay1",dataConvert(bean[2])); // 已支付人数
            int pay1 = Integer.parseInt(dataConvert(bean[2]));
            int totalPeople = Integer.parseInt(dataConvert(bean[0]));
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format((float)pay1/(float)totalPeople*100);
            data.put("proportion",result + "%");// 报名支付比例
        }
        return data;
    }

    /**
     * 获取近7日报名数
     * @return
     */
    @Override
    public JSONObject getSevenDayApplyTotal() {
        //生成x轴的日期数据
        List<String> xDataList = new ArrayList<String>();
        for (int i = 7; i >= 1; i--) {
            String pastDay = DateUtils.getPastDate(i);
            xDataList.add(pastDay);
        }
        //生成y轴用户数数据
        List<String> yDataList = new ArrayList<>();
        List<Object> list = homePageStatisticsRepository.getSevenDayUserTotal();
        HashMap<String,String> map = new HashMap<>();
        for(Object obj : list){
            Object[] bean = (Object[]) obj;
            String total = bean[0].toString();
            String time = bean[1].toString();
            map.put(time,total);
        }
        //匹配数据，匹配不到置0
        for(String pastTime : xDataList){
            if(map.containsKey(pastTime)){
                yDataList.add(map.get(pastTime));
            }else{
                yDataList.add("0");
            }
        }
        //封装返回数据
        JSONObject data = new JSONObject();
        data.put("xData",xDataList);
        data.put("yData",yDataList);
        return data;
    }

    private String dataConvert(Object obj){
        if(null != obj){
            return obj.toString();
        }else{
            return "0";
        }
    }
}
