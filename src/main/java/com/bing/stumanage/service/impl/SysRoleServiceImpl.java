package com.bing.stumanage.service.impl;

import com.bing.stumanage.entity.SysMenu;
import com.bing.stumanage.entity.SysRole;
import com.bing.stumanage.entity.SysRoleMenu;
import com.bing.stumanage.repository.SysMenuRepository;
import com.bing.stumanage.repository.SysRoleMenuRepository;
import com.bing.stumanage.repository.SysRoleRepository;
import com.bing.stumanage.service.SysRoleService;
import com.bing.stumanage.utils.CopyProperties;
import com.bing.stumanage.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Description 角色service
 * @Author zhaobing
 * @Date 2020/1/26 21:15
 **/
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;// 角色Repository

    @Autowired
    private SysMenuRepository sysMenuRepository;// 菜单Repository

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;// 角色菜单Repository

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<SysRole> findAllRoles() {
        return sysRoleRepository.findAll();
    }

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @Override
    public SysRole save(SysRole sysRole) {
        return sysRoleRepository.save(sysRole);
    }

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    @Override
    public SysRole update(SysRole sysRole) {
        Optional<SysRole> optional = sysRoleRepository.findById(sysRole.getId());
        if(optional.isPresent()){
            SysRole result = optional.get();
            CopyProperties.copyPropertiesIgnoreNull(sysRole,result);
            return sysRoleRepository.save(result);
        }else{
            return null;
        }
    }

    /**
     * 删除角色，支持批量删除
     * @param records
     */
    @Override
    public void delete(List<SysRole> records) {
        for (SysRole record : records) {
            sysRoleRepository.deleteById(record.getId());
        }
    }

    /**
     * 根据角色id查询目录
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> findRoleMenus(Integer roleId) {
        return sysMenuRepository.findRoleMenus(roleId);
    }

    /**
     * 保存角色对应的目录
     * @param records
     */
    @Override
    public void saveRoleMenus(List<SysRoleMenu> records) {
        if (records != null && records.size() > 0){
            long roleId = records.get(0).getRoleId();
            sysRoleMenuRepository.deleteByRoleId(roleId);
            for(SysRoleMenu record:records) {
                sysRoleMenuRepository.save(record);
            }
        }
    }

    /**
     * 分页查询角色
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageUtil queryByPage(String roleName,Integer page, Integer size) {
        List<SysRole> list = sysRoleRepository.queryByPage(roleName,(page-1)*size, size);
        int count = sysRoleRepository.queryByPageTotalCount(roleName);
        PageUtil pageList = new PageUtil(page,size,count,list);
        return pageList;
    }
}
