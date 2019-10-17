package com.bing.stumanage.service.impl;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.repository.SysUserRepository;
import com.bing.stumanage.service.BasicFunctionService;
import com.bing.stumanage.uitls.CopyProperties;
import com.bing.stumanage.uitls.UserPasswordGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Description 基础功能（修改密码，退出登录）
 * @Author zhaobing
 * @Date 2019/10/17 19:27
 **/
@Service
@Transactional
public class BasicFunctionServiceImpl implements BasicFunctionService{

    @Autowired
    private SysUserRepository sysUserRepository;
    /**
     * 修改密码
     * @param userName  用户名
     * @param oldPwd   原始密码
     * @param newPwd  新密码
     * @return
     */
    @Override
    public boolean updatePwd(String userName,String oldPwd,String newPwd) {
        SysUser sysUser = sysUserRepository.findByUserName(userName);
        if (null != sysUser){
            if (UserPasswordGenerateUtil.SHA1(oldPwd + sysUser.getSalt()).equals(sysUser.getPassword())){
                sysUser.setPassword( UserPasswordGenerateUtil.SHA1(newPwd + sysUser.getSalt()));
                sysUserRepository.save(sysUser);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 退出登录，清空token
     * @param userName 用户名
     */
    @Override
    public void logOut(String userName) {
        SysUser sysUser = sysUserRepository.findByUserName(userName);
        if(null != sysUser){
            sysUser.setToken("");
            update(sysUser);
        }
    }

    /**
     * 修改
     * @param sysUser
     * @return
     * @throws Exception
     */
    public boolean update(SysUser sysUser){
        Optional<SysUser> optional = sysUserRepository.findById(sysUser.getId());
        if(optional.isPresent()){
            SysUser sysUser1 = optional.get();
            CopyProperties.copyPropertiesIgnoreNull(sysUser1,sysUser);
            sysUserRepository.save(sysUser);
            return true;
        }else{
            return false;
        }
    }
}
