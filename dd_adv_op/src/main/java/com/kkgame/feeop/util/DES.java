package com.kkgame.feeop.util;


import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
	
	private final static String MODE_DES = "DES";

	/** 
	* 加密 
	* @param src 数据源 
	* @param key 密钥，长度必须是8的倍数 
	* @return 返回加密后的数据 
	* @throws Exception 
	*/ 
	public static byte[] encrypt(byte[] src, byte[] key)throws Exception { 
		//DES算法要求有一个可信任的随机数源 
		SecureRandom sr = new SecureRandom(); 
		// 从原始密匙数据创建DESKeySpec对象 
		DESKeySpec dks = new DESKeySpec(key); 
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成 
		// 一个SecretKey对象 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MODE_DES); 
		SecretKey securekey = keyFactory.generateSecret(dks); 
		// Cipher对象实际完成加密操作 
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding"); 
		// 用密匙初始化Cipher对象 
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr); 
		// 现在，获取数据并加密 
		// 正式执行加密操作 
		return cipher.doFinal(src); 
	} 

	/** 
	* 解密 
	* @param src 数据源 
	* @param key 密钥，长度必须是8的倍数 
	* @return 返回解密后的原始数据 
	* @throws Exception 
	*/ 
	public static byte[] decrypt(byte[] src, byte[] key)throws Exception { 
		// DES算法要求有一个可信任的随机数源 
		SecureRandom sr = new SecureRandom(); 
		// 从原始密匙数据创建一个DESKeySpec对象 
		DESKeySpec dks = new DESKeySpec(key); 
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成 
		// 一个SecretKey对象 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MODE_DES); 
		SecretKey securekey = keyFactory.generateSecret(dks); 
		// Cipher对象实际完成解密操作 
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding"); 
		// 用密匙初始化Cipher对象 
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr); 
		// 现在，获取数据并解密 
		// 正式执行解密操作 
		return cipher.doFinal(src); 
	} 
	
	public static void main(String[] args) throws Exception{

		String s = "9507649EDED47BDA20236A819D29D853510D17250DC0A96721B637C164DA0065EEBD638B8BA913DE406BA9189E5A58C6ABD1ABCEEFFF6C7BE7B5BC837BECFE06CC4B19C1D48F1B7F5464A07D78E97C4CB48CDD4BFE79FF9E60A779447598C633EF3A4A0816CD5A75C55078CA33E050ABAD0EE1429847B22E519926593E66AA0FFF5D2D327A77362ABF5475A8CBC3B2F65AD2F6CD12D67699CCF4372A02035F05";
		
		byte[] data = ByteUtils.hexSring2bytes(s);
		
		byte[] key = "12345678".getBytes();
		
		System.out.println(ByteUtils2.toHexString(DES.decrypt(data, key)));
	}
}
