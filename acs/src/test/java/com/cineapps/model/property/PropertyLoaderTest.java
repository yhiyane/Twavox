/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.cineapps.model.core.Ftp;

public class PropertyLoaderTest {

	@Test
	public void testLoadAllPropertiesNull() {
		String propertyFilePath = null;
		PropertyLoader propertyLoader = new PropertyLoader(propertyFilePath);
		Assert.assertFalse(propertyLoader.isValid());
	}

	@Test
	public void testLoadAllPropertiesWrongPath() {
		String propertyFilePath = "/wrong/path";
		PropertyLoader propertyLoader = new PropertyLoader(propertyFilePath);
		Assert.assertFalse(propertyLoader.isValid());
		Assert.assertFalse(propertyLoader.getCommunicationProperty().isValid());
		Assert.assertFalse(propertyLoader.getMediablockProperty().isValid());
		Assert.assertTrue(propertyLoader.getFtpProperty().isValid());
		Assert.assertFalse(propertyLoader.getSubtitleProperty().isValid());
		Assert.assertFalse(propertyLoader.getAuxiliaryContentProperty().isValid());
	}

	@Test
	public void testLoadAllProperties() {
		String propertiesFilePath = FileUtils.getFile("src", "test", "resources", "config",
		        "acs.properties").getPath();
		PropertyLoader propertyLoader = new PropertyLoader(propertiesFilePath);
		Assert.assertTrue(propertyLoader.isValid());

		FtpProperty ftpProperty = propertyLoader.getFtpProperty();
		List<Ftp> ftps = ftpProperty.getFtps();
		Assert.assertFalse(ftps.get(0).isNull());
		Assert.assertFalse(ftps.get(1).isNull());
	}

	@Test
	public void testLoadAllPropertiesWrongProperties() {
		// Miss free.languaes property
		String propertiesFilePath = FileUtils.getFile("src", "test", "resources", "config",
		        "acs-wrong.properties").getPath();
		PropertyLoader propertyLoader = new PropertyLoader(propertiesFilePath);
		Assert.assertFalse(propertyLoader.isValid());
		Assert.assertTrue(propertyLoader.getCommunicationProperty().isValid());
		Assert.assertTrue(propertyLoader.getMediablockProperty().isValid());
		Assert.assertTrue(propertyLoader.getFtpProperty().isValid());
		Assert.assertFalse(propertyLoader.getSubtitleProperty().isValid());
		Assert.assertTrue(propertyLoader.getAuxiliaryContentProperty().isValid());
	}

	@Test
	public void testNoFtpInfoProperties() {
		// Miss free.languaes property
		String propertiesFilePath = FileUtils.getFile("src", "test", "resources", "config",
		        "acs-wrong2.properties").getPath();
		PropertyLoader propertyLoader = new PropertyLoader(propertiesFilePath);

		FtpProperty ftpProperty = propertyLoader.getFtpProperty();
		Assert.assertFalse(propertyLoader.isValid());
		Assert.assertTrue(ftpProperty.isValid());
		Assert.assertTrue(ftpProperty.getFtps().isEmpty());
	}
}
