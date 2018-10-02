/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.cineapps.manager.CplFilesManager;

public class FileSystemUtilsTest {

	@Test
	public void testFindCplFiles() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "Unbroken")
		        .getPath();
		CplFilesManager result = FileSystemUtils.findCplFiles(directory);
		Assert.assertNotNull(result);
		Assert.assertEquals(directory, result.getDirectoryPath());
		Assert.assertEquals(5, result.getCpls().size());
	}

	@Test
	public void testFindCplFilesNull() {
		String directory = "/incorrect/path/";
		CplFilesManager result = FileSystemUtils.findCplFiles(directory);
		Assert.assertNull(result);
	}

	@Test
	public void testGetList() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp").getPath();
		List<String> list = FileSystemUtils.getList(directory);
		Assert.assertTrue(list.size() > 14);
	}

	@Test
	public void testGetListNull() {
		String directory = "/incorrect/path/";
		List<String> list = FileSystemUtils.getList(directory);
		Assert.assertNull(list);
	}

	@Test
	public void testGetAvailablePercent() {
		int percent = FileSystemUtils.getAvailablePercent("/");
		Assert.assertTrue(0 < percent);
		Assert.assertTrue(percent < 100);
	}
}
