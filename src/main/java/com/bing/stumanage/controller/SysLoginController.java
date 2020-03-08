package com.bing.stumanage.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.entity.SysUserToken;
import com.bing.stumanage.service.SysUserService;
import com.bing.stumanage.service.SysUserTokenService;
import com.bing.stumanage.utils.IOUtils;
import com.bing.stumanage.utils.PasswordUtils;
import com.bing.stumanage.utils.ResponseMessage;
import com.bing.stumanage.utils.ShiroUtils;
import com.bing.stumanage.vo.LoginBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 登录控制器
 * @author zhaobing
 * @date 2020/1/26
 */
@RestController
@Api(tags={"登录，登出操作接口"})
public class SysLoginController {

	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	/**
	 * 生成图形验证码
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到验证码到 session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		System.out.println("存的session是：" + ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY));
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 * @param loginBean
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value="登录",notes="账号，密码，验证码均为必填项")
	@PostMapping(value = "/login")
	public ResponseMessage login(@RequestBody LoginBean loginBean) throws IOException {
		String userName = loginBean.getUsername();// 用户名
		String password = loginBean.getPassword();// 密码
		String captcha = loginBean.getCaptcha();// 验证码
		System.out.println("取的session是：" + ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY));
		// 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
		Object kaptcha = ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(kaptcha == null){
			return ResponseMessage.errorWithMsg("验证码已失效");
		}
		if(!captcha.equals(kaptcha)){
			return ResponseMessage.errorWithMsg("验证码不正确");
		}

		// 获取用户信息
		SysUser user = sysUserService.findByUserName(userName);

		// 账号不存在、密码错误
		if (user == null) {
			return ResponseMessage.errorWithMsg("账号不存在");
		}

		// 验证密码
		if (!match(user, password)) {
			return ResponseMessage.errorWithMsg("密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			return ResponseMessage.errorWithMsg("账号已被锁定,请联系管理员");
		}

		// 生成token，并保存到数据库
		SysUserToken data = sysUserTokenService.createToken(user.getId());
		user.setLastLoginTime(new Date());// 保存最后登录时间
		sysUserService.commonSave(user);
		user.setToken(data.getToken());
		return ResponseMessage.success(user);
	}

	/**
	 * 验证用户密码
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean match(SysUser user, String password) {
		// 表中密码和入参密码进行equals
		return user.getPassword().equals(PasswordUtils.encrypte(password, user.getSalt()));
	}

	/**
	 * 退出登录
	 * @return
	 */
	@GetMapping(value = "/logout")
	public ResponseMessage logout() {
		ShiroUtils.logout();// 退出登录
		return ResponseMessage.success();
	}
}
