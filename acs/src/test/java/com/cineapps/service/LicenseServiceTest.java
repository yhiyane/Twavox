/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cineapps.model.license.License;

public class LicenseServiceTest {

	private LicenseService service = new LicenseService();
	private File tmp;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

	@Before
	public void createTmpFile() throws IOException {
		tmp = File.createTempFile("test.ac", "license");
	}

	@After
	public void deleteTmpFile() {
		if (tmp.exists()) {
			tmp.delete();
		}
	}

	@Test
	public void testGenerateLicense() throws Exception {
		// Create the license
		String mac = "64-B9-E8-B9-16-DE";
		Date limitDate = null;
		service.generateLicense(mac, limitDate, 1, true, tmp.getPath());

		// Comparison
		String actual = new String(Files.readAllBytes(Paths.get(tmp.getPath())));
		String expectedPath = FileUtils.getFile("src", "test", "resources", "license",
		        "acs.license").getPath();
		String expected = new String(Files.readAllBytes(Paths.get(expectedPath)));
		Assert.assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void testLoad() throws ParseException {
		String expectedPath = FileUtils.getFile("src", "test", "resources", "license",
		        "acs.license").getPath();
		service.loadLicense(expectedPath);
		License license = service.getLicense();

		Assert.assertNotNull(license);
		Assert.assertEquals("64-B9-E8-B9-16-DE", license.getMac());
		Assert.assertNull(license.getLimitDate());
		Assert.assertEquals(1, license.getMultiStNbCoins());
		Assert.assertTrue(license.isAuxContent());
		Assert.assertTrue(license.isValid());
	}

	@Test
	public void testLoad20141231() throws ParseException {
		String expectedPath = FileUtils.getFile("src", "test", "resources", "license",
		        "acs.license.20141231").getPath();
		service.loadLicense(expectedPath);
		License license = service.getLicense();

		Assert.assertNotNull(license);
		Assert.assertEquals("64-B9-E8-B9-16-DE", license.getMac());
		Assert.assertTrue(license.getLimitDate().after(sdf.parse("2014-12-30")));
		Assert.assertTrue(license.getLimitDate().before(sdf.parse("2015-01-01")));
		Assert.assertEquals(1, license.getMultiStNbCoins());
		Assert.assertTrue(license.isAuxContent());
	}

	@Test
	public void testLoadNotLicense() {
		String expectedPath = FileUtils.getFile("src", "test", "resources", "license",
		        "acs.license.false").getPath();
		service.loadLicense(expectedPath);
		License actual = service.getLicense();

		Assert.assertNull(actual);
		Assert.assertFalse(service.isLicenseValid());
	}

	@Test
	public void testLoadPassedNoModule() throws ParseException {
		String expectedPath = FileUtils.getFile("src", "test", "resources", "license",
		        "acs.license.passed.no.module").getPath();
		service.loadLicense(expectedPath);
		License license = service.getLicense();

		Assert.assertNotNull(license);
		Assert.assertEquals("64-B9-E8-B9-16-DE", license.getMac());
		Assert.assertTrue(license.getLimitDate().after(sdf.parse("2013-12-30")));
		Assert.assertTrue(license.getLimitDate().before(sdf.parse("2014-01-01")));
		Assert.assertEquals(-1, license.getMultiStNbCoins());
		Assert.assertFalse(license.isAuxContent());
	}

	@Test
	public void testLoadAnotherLicense() throws ParseException {
		String expectedPath = FileUtils.getFile("src", "test", "resources", "license",
		        "acs.license.another").getPath();
		service.loadLicense(expectedPath);
		License license = service.getLicense();
		Assert.assertNotNull(license);
		Assert.assertEquals("00-26-BB-18-12-2E", license.getMac());
		Assert.assertTrue(license.getLimitDate().after(sdf.parse("2014-12-30")));
		Assert.assertTrue(license.getLimitDate().before(sdf.parse("2015-01-01")));
		Assert.assertEquals(-1, license.getMultiStNbCoins());
		Assert.assertFalse(license.isAuxContent());
	}

	@Test
	public void testNoLicense() {
		service.setLicense(null);
		Assert.assertFalse(service.isLicenseValid());
	}

	@Test
	public void testReadKey() {
		String expected = "64-B9-E8-B9-16-DE";

		String keyPath = FileUtils.getFile("src", "test", "resources", "license", "key").getPath();
		String actual = service.readKey(keyPath);
		Assert.assertEquals(expected, actual);
	}
}
