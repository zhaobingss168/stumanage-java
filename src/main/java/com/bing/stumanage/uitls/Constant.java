package com.bing.stumanage.uitls;
/**
 * 常量类
 */
public class Constant {
	
	/**
	 * 成功编码
	 */
	public static final String SUCCESS_CODE = "0";
	
	/**
	 * 成功描述
	 */
	public static final String SUCCESS_MESSAGE = "success";
	
	/**
	 * 失败编码
	 */
	public static final String ERROR_CODE = "-1";
	
	/**
	 * 失败描述
	 */
	public static final String ERROR_MESSAGE = "error";

	/**
	 * 缺少登录必要参数
	 */
	public static final String PARAM_LACK = "1001";

	/**
	 * 参数不少，但是值错误，即根据参数查询不到登录信息
	 */
	public static final String PARAM_ERROR = "1002";

	/**
	 * 登录超时,大于了30分钟
	 */
	public static final String TIME_OUT = "1003";

	/**
	 * 登录session时间为30min
	 */
	public static final int TIME_OUT_VALUE = 30;
	/**
	 * 没有权限
	 */
	public static final String NOT_ALLOW = "1004";

}
