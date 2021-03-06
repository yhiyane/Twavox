/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.utils;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptUtils {

	/**
	 * Encodes a message with a private key.
	 * 
	 * @param value
	 * @param key
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static String encrypt(String value, String key) throws GeneralSecurityException {
		SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
		byte[] encrypted = cipher.doFinal(value.getBytes());
		return byteArrayToHexString(encrypted);
	}

	/**
	 * Decodes an encoded message with a private key.
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static String decrypt(String message, String key) throws GeneralSecurityException {
		SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, sks);
		byte[] decrypted = cipher.doFinal(hexStringToByteArray(message));
		return new String(decrypted);
	}

	/**
	 * Returns a Hex string from a byte array.
	 * 
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * Returns a byte array from a Hex string.
	 * 
	 * @param s
	 * @return
	 */
	private static byte[] hexStringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}

}
