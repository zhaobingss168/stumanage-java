package com.bing.stumanage.service.impl;
import com.bing.stumanage.entity.SysMenu;
import com.bing.stumanage.entity.SysRole;
import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.entity.SysUserRole;
import com.bing.stumanage.repository.SysRoleRepository;
import com.bing.stumanage.repository.SysUserRepository;
import com.bing.stumanage.repository.SysUserRoleRepository;
import com.bing.stumanage.service.SysMenuService;
import com.bing.stumanage.service.SysUserService;
import com.bing.stumanage.utils.CopyProperties;
import com.bing.stumanage.utils.PageUtil;
import com.bing.stumanage.utils.PasswordUtils;
import com.bing.stumanage.utils.ResponseMessage;
import com.bing.stumanage.vo.UpdatePasswordBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 系统用户service
 * @author zhaobing
 * @date 2020/1/26
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;// 系统用户Repository

    @Autowired
    private SysRoleRepository sysRoleRepository;// 角色Repository

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;// 用户角色Repository

    @Autowired
    private SysMenuService sysMenuService;// 菜单service

    /**
     * 分页查询系统用户
     * @param username 用户名
     * @param status 用户状态
     * @param page 当前页码
     * @param size 每页显示条数
     * @return
     */
    @Override
    public PageUtil queryByPage(String username,Integer status,Integer page, Integer size) {
        List<SysUser> list = sysUserRepository.queryByPage(username,status,(page-1)*size, size);
        for (SysUser sysUser : list){
            int userId = sysUser.getId();
            // 根据用户id查询出所有角色
            List<SysUserRole> sysUserRoles = querySysUserRoleByUserId(userId);
            sysUser.setUserRoles(sysUserRoles);
            // 根据角色查询出角色名称
            String roleNames = queryRoleNamesByRoleIdList(sysUserRoles);
            sysUser.setRoleNames(roleNames);// 拥有的角色
        }
        int count = sysUserRepository.queryByPageTotalCount(username,status);
        PageUtil pageList = new PageUtil(page,size,count,list);
        return pageList;
    }

    /**
     * 根据角色查询出角色名称
     * @param sysUserRoles
     * @return
     */
    private String queryRoleNamesByRoleIdList(List<SysUserRole> sysUserRoles) {
        StringBuffer stringBuffer = new StringBuffer();
        for(Iterator<SysUserRole> iter = sysUserRoles.iterator(); iter.hasNext();) {
            SysUserRole userRole = iter.next();
            SysRole sysRole = sysRoleRepository.queryOneSysRoleByRoleId(userRole.getRoleId());
            if(sysRole == null) {
                continue ;
            }
            stringBuffer.append(sysRole.getName());
            if(iter.hasNext()) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 根据用户id查询角色
     * @param userId 系统用户id
     * @return
     */
    private List<SysUserRole> querySysUserRoleByUserId(int userId) {
        return sysUserRoleRepository.querySysUserRoleByuserId(userId);
    }

    /**
     * 保存系统用户
     * @param sysUser
     * @return
     */
    @Override
    public ResponseMessage save(SysUser sysUser){
        // 忽略用户状态查询
        SysUser valUsername = sysUserRepository.findByUserNameNeglectStatus(sysUser.getUsername());
        // 如果用户名存在
        if (valUsername != null){
            return ResponseMessage.errorWithMsg("用户名已经存在");
        }

        SysUser valPhone = sysUserRepository.findByPhone(sysUser.getPhone());
        // 如果电话号码存在
        if (valPhone != null){
            return ResponseMessage.errorWithMsg("电话号码已经存在");
        }

        String salt = PasswordUtils.getSalt();// 盐值
        sysUser.setSalt(salt);// 盐值
        sysUser.setStatus(1);// 默认值1，状态为有效
        String pwd = PasswordUtils.encrypte(sysUser.getUsername(),salt);// 生成一个和用户名相同的默认密码
        sysUser.setPassword(pwd);// 和用户名相同的默认密码
        SysUser resultUser = sysUserRepository.save(sysUser);// 保存系统用户
        // 保存角色信息
        for(SysUserRole sysUserRole:sysUser.getUserRoles()) {
            sysUserRole.setUserId(resultUser.getId());
            sysUserRoleRepository.save(sysUserRole);
        }

        return ResponseMessage.success();
    }

    /**
     * 修改系统用户
     * @param sysUser
     * @return
     */
    @Override
    public ResponseMessage update(SysUser sysUser){
        SysUser valPhone = sysUserRepository.findByPhoneAndId(sysUser.getPhone(),sysUser.getId());
        // 如果电话号码存在
        if (valPhone != null){
            return ResponseMessage.errorWithMsg("电话号码已经存在");
        }
        Optional<SysUser> optional = sysUserRepository.findById(sysUser.getId());
        if(optional.isPresent()){
            SysUser result = optional.get();
            CopyProperties.copyPropertiesIgnoreNull(sysUser,result);
            sysUserRepository.save(result);// 保存用户信息
            // 保存角色信息
            if (sysUser.getUserRoles() != null && sysUser.getUserRoles().size() > 0){
                int userId = sysUser.getUserRoles().get(0).getUserId();
                // 先删除原有的角色，再将新的全部保存
                sysUserRoleRepository.deleteSysUserRoleByUserId(userId);
                for(SysUserRole sysUserRole : sysUser.getUserRoles()) {
                    sysUserRoleRepository.save(sysUserRole);
                }
            }
            return ResponseMessage.success();
        }else{
            return ResponseMessage.error();
        }
    }
    /**
     * 删除，支持批量删除
     * @param records 用户的userid集合
     * @return
     */
    @Override
    public void delete(List<SysUser> records){
        for (SysUser record : records) {
            sysUserRepository.deleteById(record.getId());// 删除用户表数据
            sysUserRoleRepository.deleteSysUserRoleByUserId(record.getId());// 删除用户角色关系表数据
        }
    }

    /**
     * 根据用户名查找SysUser
     * @param userName 用户名
     * @return
     */
    @Override
    public SysUser findByUserName(String userName) {
        return sysUserRepository.findByUserName(userName);
    }

    /**
     * 根据用户名查询用户权限
     * @param userName
     * @return
     */
    @Override
    public Set<String> findPermissionsByUserName(String userName) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findByUserName(userName);
        for(SysMenu sysMenu:sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    /**
     * 根据用户id查找用户信息
     * @param userId
     * @return
     */
    @Override
    public SysUser findByUserId(Integer userId) {
        return sysUserRepository.getUserByUserId(userId);
    }

    /**
     * 常规保存用户信息
     * @param user
     */
    @Override
    public void commonSave(SysUser user) {
        sysUserRepository.save(user);
    }

    /**
     * 修改密码
     * @param updatePasswordBean
     * @return
     */
    @Override
    public ResponseMessage updatePassword(UpdatePasswordBean updatePasswordBean) {
        SysUser sysUser = sysUserRepository.findByUserName(updatePasswordBean.getUsername());
        if (sysUser == null){
            return ResponseMessage.errorWithMsg("账号不存在");
        }else {
            String dbOldPwd = sysUser.getPassword();
            if (!dbOldPwd.equals(PasswordUtils.encrypte(updatePasswordBean.getOldPwd(), sysUser.getSalt()))){
                return ResponseMessage.errorWithMsg("原密码错误");
            }else {
                sysUser.setPassword(PasswordUtils.encrypte(updatePasswordBean.getNewPwd(), sysUser.getSalt()));
                sysUserRepository.save(sysUser);
                return ResponseMessage.success();
            }
        }
    }

}
