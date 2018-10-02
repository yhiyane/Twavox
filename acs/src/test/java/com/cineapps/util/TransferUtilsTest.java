/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TransferUtilsTest {

	private String tempDirectoryPath;
	private File tempDirectory;

	@Before
	public void setup() {
		// Create a temporary directory to store the transfered subtitle folder
		tempDirectoryPath = FileUtils.getTempDirectoryPath() + "TmpSub";
		tempDirectoryPath += tempDirectoryPath.endsWith("/") ? "" : "/";
		tempDirectory = new File(tempDirectoryPath);
		if (tempDirectory.exists()) {
			tempDirectory.delete();
		}
		tempDirectory.mkdirs();
	}

	@After
	public void clear() throws IOException {
		// Delete the temporary directory
		FileUtils.deleteDirectory(tempDirectory);
	}

	@Test
	public void testCopyDirectory() {
		// FTP connection
		FTPClient ftpClient = FtpUtils.connect("localhost", "macbookproapple", "jcchrun");
		Assert.assertNotNull(ftpClient);

		// Transfer a DCP folder with subtitles
		File srcDir = FileUtils.getFile("src", "test", "resources", "dcp", "3Coeurs");
		TransferUtils.copyDirectory(srcDir.getAbsolutePath(), tempDirectoryPath, ftpClient);

		Assert.assertTrue(tempDirectory.list().length == 9);
		// Check CPL files
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "43ceb8f1-1412-47cc-a157-5bb85e1561ae.xml")));
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "c58f5f37-35c7-4384-b5db-23e5827662b5.xml")));
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "ebb913cc-087f-4a18-974d-f7d4ccdc799a.xml")));

		// Check subtitle files
		File subtitle = new File(tempDirectoryPath + "21f76f0f-baa6-496a-90cb-271cb8c88a7c");
		Assert.assertTrue(subtitle.exists());
		Assert.assertTrue(subtitle.list().length == 1);
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "21f76f0f-baa6-496a-90cb-271cb8c88a7c", "21f76f0f-baa6-496a-90cb-271cb8c88a7c.xml")));

		subtitle = new File(tempDirectoryPath + "432d9952-ead9-4270-b11e-3adb1ed8e87d");
		Assert.assertTrue(subtitle.exists());
		Assert.assertTrue(subtitle.list().length == 1);
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "432d9952-ead9-4270-b11e-3adb1ed8e87d", "432d9952-ead9-4270-b11e-3adb1ed8e87d.xml")));

		subtitle = new File(tempDirectoryPath + "45ba1633-6733-46ec-96ab-8dc9b402b53c");
		Assert.assertTrue(subtitle.exists());
		Assert.assertTrue(subtitle.list().length == 1);
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "45ba1633-6733-46ec-96ab-8dc9b402b53c", "45ba1633-6733-46ec-96ab-8dc9b402b53c.xml")));

		subtitle = new File(tempDirectoryPath + "a304b526-130e-48b7-9a34-7e3890b5af68");
		Assert.assertTrue(subtitle.exists());
		Assert.assertTrue(subtitle.list().length == 1);
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "a304b526-130e-48b7-9a34-7e3890b5af68", "a304b526-130e-48b7-9a34-7e3890b5af68.xml")));

		subtitle = new File(tempDirectoryPath + "daac122d-f868-4069-afa1-825acacbdb0b");
		Assert.assertTrue(subtitle.exists());
		Assert.assertTrue(subtitle.list().length == 1);
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "daac122d-f868-4069-afa1-825acacbdb0b", "daac122d-f868-4069-afa1-825acacbdb0b.xml")));

		subtitle = new File(tempDirectoryPath + "eaadbc9a-5d10-4ca2-83fc-c018bc58b6f9");
		Assert.assertTrue(subtitle.exists());
		Assert.assertTrue(subtitle.list().length == 1);
		Assert.assertTrue(Files.exists(Paths.get(tempDirectoryPath,
		        "eaadbc9a-5d10-4ca2-83fc-c018bc58b6f9", "eaadbc9a-5d10-4ca2-83fc-c018bc58b6f9.xml")));
	}
}
