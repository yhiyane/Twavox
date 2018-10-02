/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.net.SocketException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SystemUtilsTest {

	@Ignore
	@Test
	public void testGetMacAddress() throws SocketException {
		String mac = SystemUtils.getMacAddress();
		Assert.assertNotNull(mac);
	}

	@Ignore
	@Test
	public void testIsMacAddressValid() throws SocketException {
		String mac = SystemUtils.getMacAddress();
		Assert.assertNotNull(mac);

		boolean isValid = SystemUtils.isMacAddressValid(mac);
		Assert.assertTrue(isValid);
	}

	@Test
	public void testIsMacAddressInvalid() throws SocketException {
		String invalidMac = "12-12-12-12-12-12";
		boolean isValid = SystemUtils.isMacAddressValid(invalidMac);
		Assert.assertFalse(isValid);
	}
}
