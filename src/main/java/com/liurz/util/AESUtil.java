package com.liurz.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对称加密
 * 
 * @author Administrator
 *
 */

public class AESUtil {
	static Logger log = LoggerFactory.getLogger(AESUtil.class);
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static void main(String[] args) {
		byte[] by = AESUtil.AESencrypt("222ww");
		AESUtil.AESdecrypt(by);
	}

	// 加密
	public static byte[] AESencrypt(String result) {
		byte[] bytes = initSecretKey();
		log.info("生成key：" + showByteArray(bytes));
		Key key = toKey(bytes);
		log.info("加密前的数据：" + result);
		try {
			byte[] by = encrypt(result.getBytes(), key);
			log.info("加密后的数据：" + showByteArray(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

	// 解密
	public static void AESdecrypt(byte[] encryptData) {
		byte[] bytes = initSecretKey();
		log.info("生成key：" + showByteArray(bytes));
		try {
			byte[] decryptData = decrypt(encryptData, bytes);
			log.info("解密后的数据：" + showByteArray(decryptData));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 初始化
	public static byte[] initSecretKey() {
		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(128);
		// 生成一个密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	// key转换-生成密钥
	private static Key toKey(byte[] key) {
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	// 生成key的内容
	public static byte[] encrypt(byte[] data, Key key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	// 还原密钥
	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}

	// 加密
	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 执行操作
		return cipher.doFinal(data);
	}

	// 显示数据，将字节转化为字符串
	private static String showByteArray(byte[] data) {
		if (null == data) {
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for (byte b : data) {
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	// 解密
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm);
	}

	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 执行操作
		return cipher.doFinal(data);
	}

}