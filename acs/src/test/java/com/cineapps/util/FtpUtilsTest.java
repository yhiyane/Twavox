/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class FtpUtilsTest {

	@Test
	public void testConnect() {
		FTPClient ftpClient = FtpUtils.connect("localhost", "macbookproapple", "jcchrun");
		Assert.assertNotNull(ftpClient);
	}

	@Test
	public void testConnectNull() {
		FTPClient ftpClient = FtpUtils.connect("10.10.10.10", "wrong", "ftp");
		Assert.assertNull(ftpClient);
	}
}
