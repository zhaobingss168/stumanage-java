package com.bing.stumanage.service;

public interface BasicFunctionService {
    boolean updatePwd(String userName, String oldPwd,String newPwd);
    void logOut(String userName);
}
