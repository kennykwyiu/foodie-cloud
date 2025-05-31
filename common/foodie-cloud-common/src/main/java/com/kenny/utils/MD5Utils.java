package com.kenny.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class MD5Utils {

	/**
	 * 
	 * @Title: MD5Utils.java
	 * @Package com.kenny.utils
	 * @Description: MD5 encryption for strings
	 */
	public static String getMD5Str(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		return newstr;
	}

	public static void main(String[] args) {
		try {
			String md5 = getMD5Str("Kenny");
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
