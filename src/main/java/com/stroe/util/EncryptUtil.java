package com.stroe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具
 * @author zengjintao
 * 2017年3月17号上午11:40
 */
public class EncryptUtil {

	/**
	 * md5算法加密
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getMd5(String Md5key,String salt) throws NoSuchAlgorithmException{
		String key=DigestUtils.md5Hex(Md5key)+"&"+salt;
		return DigestUtils.md5Hex(key);
	}
	

	/**
	 * SHA摘要算法
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getSHA(String key) throws NoSuchAlgorithmException{
		MessageDigest sha=MessageDigest.getInstance("SHA");
		byte[] by=sha.digest(key.getBytes());
	    System.out.println(Hex.encodeHexString(by));
	    return Hex.encodeHexString(by);
	}
	
	/**
	 * 
	 * @param salt 可以是时间戳
	 * @return
	 */
	public static String encodeSalt(String salt){
		return DigestUtils.sha1Hex(salt);
	}
	
	/**
	 * mac消息摘要算法
	 */
	public static String getMac(String macStr){
		try {
			KeyGenerator keygenerator=KeyGenerator.getInstance("HmacMD5");//初始化密钥
			SecretKey secretkey=keygenerator.generateKey();
			byte[] key= secretkey.getEncoded();//获得密钥
			
			SecretKey secretkeySpec=new SecretKeySpec(key, "HmacMD5");
			Mac mac=Mac.getInstance(secretkeySpec.getAlgorithm());
			mac.init(secretkeySpec);
			byte[] hmac=mac.doFinal(macStr.getBytes());
			return Hex.encodeHexString(hmac);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
