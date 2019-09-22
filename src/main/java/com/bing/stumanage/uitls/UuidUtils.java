package com.bing.stumanage.uitls;

import java.util.Random;
import java.util.UUID;

public class UuidUtils {
    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
    public static String getData(){
        Random rand = new Random();
        StringBuffer sb=new StringBuffer();
        for (int i=1;i<=20;i++){
            int randNum = rand.nextInt(9)+1;
            String num=randNum+"";
            sb=sb.append(num);
        }
        return sb.toString();
    }
}
