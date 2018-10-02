/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.license;

import java.net.SocketException;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.cineapps.util.SystemUtils;

public class LicenseTest {

	@Ignore
	@Test
	public void testIsValid() throws SocketException {
		License license = new License();
		license.setMac(SystemUtils.getMacAddress());
		license.setLimitDate(DateTime.now().plusDays(1).toDate());
		license.setMultiStNbCoins(1);
		license.setAuxContent(true);

		Assert.assertTrue(license.isValid());
	}

	@Test
	public void testIsInvalidMac() {
		License license = new License();
		license.setMac("12-12-12-12-12-12");
		license.setLimitDate(DateTime.now().plusDays(1).toDate());
		license.setMultiStNbCoins(1);
		license.setAuxContent(true);

		Assert.assertFalse(license.isValid());
	}

	@Test
	public void testIsInvalidLimitDate() throws SocketException {
		License license = new License();
		license.setMac(SystemUtils.getMacAddress());
		license.setLimitDate(DateTime.now().minusDays(1).toDate());
		license.setMultiStNbCoins(1);
		license.setAuxContent(true);

		Assert.assertFalse(license.isValid());
	}
}
