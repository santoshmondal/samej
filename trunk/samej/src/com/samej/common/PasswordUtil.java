package com.samej.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import sun.misc.BASE64Encoder;

public class PasswordUtil {

	public static String encrypt(String plaintext) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5"); // step 2 MD5
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		try {
			md.update(plaintext.getBytes("UTF-8")); // step 3
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		byte raw[] = md.digest(); // step 4
		String hash = new BASE64Encoder().encode(raw); // step 5
		return hash; // step 6
	}

	public static String md5Hex(String plaintext) {
		if (plaintext == null) {
			return "";
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5"); // step 2 MD5
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		try {
			md.update(plaintext.getBytes("UTF-8")); // step 3
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		byte raw[] = md.digest(); // step 4
		return new String(Hex.encodeHex(raw)); // step 6
	}

	public static String base64Encrypt(String plainText) {
		if (plainText == null) {
			return "";
		}
		return Base64.encodeBase64String(plainText.getBytes());
	}

	public static String md5HexEncrypt(String plainText) {
		if (plainText != null) {
			return "";
		}
		return md5Hex(plainText);
	}

	public static void main(String[] args) {
		String password = "qwerty";
		System.out.println(PasswordUtil.encrypt(password));
		System.out.println(PasswordUtil.encrypt(password));

	}
}