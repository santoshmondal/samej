package com.samej.common;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class PasswordUtil {

	private static Cipher ecipher;
	private static Cipher dcipher;

	private static SecretKey key;

	static {
		try {
			key = KeyGenerator.getInstance("DES").generateKey();
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String password) {

		try {
			byte[] utf8 = password.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);
			enc = Base64.encodeBase64(enc);
			return new String(enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String password) {

		try {
			byte[] dec = Base64.decodeBase64(password.getBytes());
			byte[] utf8 = dcipher.doFinal(dec);
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String password = "qwerty";
		String encrypt = PasswordUtil.encrypt(password);
		System.out.println(encrypt);
		System.out.println(PasswordUtil.decrypt(encrypt));

	}
}