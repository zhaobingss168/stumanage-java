package com.bing.stumanage.service.impl;

import com.bing.stumanage.entity.SysUserToken;
import com.bing.stumanage.repository.SysUserTokenRepository;
import com.bing.stumanage.service.SysUserTokenService;
import com.bing.stumanage.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 系统用户token service
 * @author zhaobing
 * @date 2020/1/26
 */
@Service
@Transactional
public class SysUserTokenServiceImpl implements SysUserTokenService {

	// 12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Autowired
	private SysUserTokenRepository sysUserTokenRepository;// 用户tokenRepository

	/**
	 * 根据token查找
	 * @param token
	 * @return
	 */
	@Override
	public SysUserToken findByToken(String token) {
		return sysUserTokenRepository.findByToken(token);
	}

	/**
	 * 创建token保存
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public SysUserToken createToken(Integer userId) {
		// 生成一个token
		String token = TokenGenerator.generateToken();
		// 当前时间
		Date now = new Date();
		// 过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
		// 判断是否生成过token
		SysUserToken sysUserToken = sysUserTokenRepository.findByUserId(userId);
		if(sysUserToken == null){
			sysUserToken = new SysUserToken();
			sysUserToken.setUserId(userId);
			sysUserToken.setToken(token);
			sysUserToken.setLastUpdateTime(now);
			sysUserToken.setExpireTime(expireTime);
			// 保存token，这里选择保存到数据库，也可以放到Redis或Session之类可存储的地方
			sysUserTokenRepository.save(sysUserToken);
		} else{
			sysUserToken.setToken(token);
			sysUserToken.setLastUpdateTime(now);
			sysUserToken.setExpireTime(expireTime);
			// 如果token已经生成，则更新token的过期时间
			sysUserTokenRepository.save(sysUserToken);
		}
		return sysUserToken;
	}
}
