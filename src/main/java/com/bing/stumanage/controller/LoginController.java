package com.bing.stumanage.controller;
import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.repository.SessionValidCodeRepository;
import com.bing.stumanage.service.SysUserService;
import com.bing.stumanage.uitls.ResponseMessage;
import com.bing.stumanage.uitls.UserPasswordGenerateUtil;
import com.bing.stumanage.uitls.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SessionValidCodeRepository sessionValidCodeRepository;

    /**
     * 第一次登录
     * @param userName
     * @param validCode
     * @return
     */
    @PostMapping(value="/4a/first")
    @ResponseBody
    public Object loginFirstForAdmin(String userName, String validCode, HttpServletRequest request){
        //1.校验验证码
        //System.out.println("sessionid:"+request.getSession().getId()+"***"+valid_code);
        //SessionValidCode svc = sessionValidCodeRepository.findOneBySessionId(request.getSession().getId());
        if(true){
            //2.根据用户名取用户
            SysUser user = sysUserService.findByUserName(userName);
            if(null == user){
                return ResponseMessage.errorWithMsg("用户名或密码错误");
            }else{
                //3.生成随机数保存数据库
                String randomNum = UserPasswordGenerateUtil.generateShortUuid();
                user.setRandom(randomNum);
                sysUserService.save(user);
                //4.返回前台随机数与盐值
                SysUser temp_user = new SysUser();
                temp_user.setRandom(randomNum);
                temp_user.setSalt(user.getSalt());
                return ResponseMessage.success(temp_user);
            }
        }else{
            return ResponseMessage.errorWithMsg("验证码校验失败");
        }
    }

    /**
     * 第二次登录
     * @param user
     * @return
     */
    @PostMapping(value="/4a/second")
    @ResponseBody
    public Object loginSecondForAdmin(SysUser user){
        //1.根据用户名取用户
        String password_1 = user.getPassword();
        SysUser user_db = sysUserService.findByUserName(user.getUserName());
        //2.校验密码
        String password_2 = UserPasswordGenerateUtil.SHA1(user_db.getPassword() + UserPasswordGenerateUtil.SHA1(user_db.getRandom()));
        if(password_1.equals(password_2)){
            //3.随机数置空，更新数据库
            user_db.setRandom("");
            user_db.setToken(UuidUtils.getUUID32());
            user_db.setLoginTime(new Date());
            sysUserService.save(user_db);
            user_db.setPassword(null);
            user_db.setSalt(null);
            //4.返回用户-不包含敏感字段
            return ResponseMessage.success(user_db);
        }else{
            return ResponseMessage.errorWithMsg("用户名或密码错误");
        }
    }

    public static  void main(String[] args){
        String pwd = "321f068a97b3b7a8d1005a767be61bfd2151761d";
        String pwdIn = UserPasswordGenerateUtil.SHA1("000000"+UserPasswordGenerateUtil.SHA1("e766e29009af443f"));
        System.out.println(pwdIn);
        System.out.println(pwd.equals(pwdIn));
    }
}
