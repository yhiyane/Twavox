/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import org.junit.Assert;
import org.junit.Test;

public class EncryptDescryptUtilsTest {

	@Test
	public void testEncrypt() throws GeneralSecurityException, UnsupportedEncodingException {
		String message = "a message very important to encrypt";
		String key = "c76cfb76010511e491910800200c9a66";
		String expected = "68A9986C96311FDC3159BF4302F3B94B8A2D86B4870BC3C8F73494C65F03AE3AF8AC52039B662453DE4FCB6546D47A87";
		String actual = EncryptDecryptUtils.encrypt(message, key);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDecrypt() throws GeneralSecurityException, UnsupportedEncodingException {
		String expected = "a message very important to encrypt";
		String key = "c76cfb76010511e491910800200c9a66";
		String message = "68A9986C96311FDC3159BF4302F3B94B8A2D86B4870BC3C8F73494C65F03AE3AF8AC52039B662453DE4FCB6546D47A87";
		String actual = EncryptDecryptUtils.decrypt(message, key);
		Assert.assertEquals(expected, actual);
	}
}
