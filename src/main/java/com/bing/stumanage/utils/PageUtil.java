package com.bing.stumanage.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PageUtil implements Serializable {
    Object list;
    int pageNumber;
    int pageSize;
    int totalElements;
    int totalPages;
    public PageUtil(int pageNumber,int pageSize,int totalElements, Object obj) {
        super();
        this.pageNumber = pageNumber;
        this.list = obj;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = getTotalPages(totalElements,pageSize);
    }
    private int getTotalPages(int countNum, int pageSize) {
        if((countNum%pageSize)==0) {
            return countNum/pageSize;
        }else {
            return (countNum/pageSize)+1;
        }
    }

    public static boolean ifTimeOut(Date tokenTime,long constantDay) {
        Date date = new Date();
        long ms = date.getTime() - tokenTime.getTime();
        long day = ms / (1000 * 60 * 60 * 24);
        if (day > constantDay) {
            return false;
        } else {
            return true;
        }
    }


}
