package com.bing.stumanage.service.impl;
import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.repository.SysUserRepository;
import com.bing.stumanage.service.SysUserService;
import com.bing.stumanage.uitls.CopyProperties;
import com.bing.stumanage.uitls.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 2019-07-29 15:37:10
 * zhaobing
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public PageUtil queryByPage(String userName,String nickName,Integer page, Integer size) {
        List<SysUser> list = sysUserRepository.queryByPage(userName,nickName,(page-1)*size, size);
        int count = sysUserRepository.queryByPageTotalCount(userName,nickName);
        PageUtil pageList = new PageUtil(page,size,count,list);
        return pageList;
    }

    @Override
    public SysUser save(SysUser sysUser){
        sysUser.setCreateTime(new Date());
        sysUser.setRoleId(1);
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser update(SysUser sysUser){
        Optional<SysUser> optional = sysUserRepository.findById(sysUser.getId());
        if(optional.isPresent()){
            SysUser result = optional.get();
            CopyProperties.copyPropertiesIgnoreNull(sysUser,result);
            return sysUserRepository.save(result);
        }else{
            return null;
        }
    }
    @Override
    public void delete(Integer id){
        sysUserRepository.deleteById(id);
    }

    @Override
    public SysUser findByUserName(String userName) {
        return sysUserRepository.findByUserName(userName);
    }

}
