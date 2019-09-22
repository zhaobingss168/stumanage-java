package com.bing.stumanage.uitls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


/**
 * create by jacktong
 * date 2018/9/27 下午6:20
 **/

public final class UserPasswordGenerateUtil {
    private static final String USERNAME_FLAG = "\\[username\\]";
    private static final String RANDOM_FLAG = "\\[random\\]";
    private static final int RANDOM_LENGTH = 6;


    /*
     * 生成随机16位
     */

    public static String generateShortUuid() {
        String key = UUID.randomUUID().toString();
        key = key.replace("-", "").substring(0, 16);// 替换掉-号
        return key;
    }


    /**
     * 加密算法
     * @param decript
     * @return
     */
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String [] args){
//		String s = SHA1(SHA1("000000123456")+SHA1("f1839bc918af4a23"));
//		System.out.println(s);
//		String randomNum = UserPasswordGenerateUtil.generateShortUuid();
//		System.out.println(randomNum);
//		String s = SHA1("000000"+SHA1(randomNum));
//		System.out.println(s);
		String s = SHA1("000000"+"123456");
		System.out.println(s);
	}


    
}
