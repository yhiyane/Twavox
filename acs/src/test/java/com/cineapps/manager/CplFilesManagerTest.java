/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.manager;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.cineapps.model.cpl.CompositionPlaylist;
import com.cineapps.util.CplUtils;

public class CplFilesManagerTest {

	@Test
	public void testFindByNameNoCpls() {
		CplFilesManager manager = new CplFilesManager(null, null);
		CompositionPlaylist cpl = manager
		        .findByName("Hercule_FTR-3D_S_FR-XX_FR_51-VI_2K_20140220_TEF_IOP-3D_OV1");
		Assert.assertNull(cpl);
	}

	@Test
	public void testFindByName() {
		File file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_33947920-b653-412e-9aa1-1146149ec692.xml");
		CompositionPlaylist cpl1 = CplUtils.parseFromFile(file.getPath());
		file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_25648bf8-b463-4531-8d57-104f8fa7fa56.xml");
		CompositionPlaylist cpl2 = CplUtils.parseFromFile(file.getPath());
		file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_3576edab-eca5-48e2-8117-b1d60d822162.xml");
		CompositionPlaylist cpl3 = CplUtils.parseFromFile(file.getPath());

		CplFilesManager manager = new CplFilesManager(null, Arrays.asList(cpl1, cpl2, cpl3));
		CompositionPlaylist cpl = manager
		        .findByName("Hercule_FTR-2D_S_FR-FR-OCAP_FR_51-VI_2K_20140305_TEF_IOP_OV2");
		Assert.assertEquals(cpl1, cpl);
	}

	@Test
	public void testFindByNameNotFound() {
		File file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_25648bf8-b463-4531-8d57-104f8fa7fa56.xml");
		CompositionPlaylist cpl2 = CplUtils.parseFromFile(file.getPath());
		file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_3576edab-eca5-48e2-8117-b1d60d822162.xml");
		CompositionPlaylist cpl3 = CplUtils.parseFromFile(file.getPath());

		CplFilesManager manager = new CplFilesManager(null, Arrays.asList(cpl2, cpl3));
		CompositionPlaylist cpl = manager
		        .findByName("Hercule_FTR-2D_S_FR-FR-OCAP_FR_51-VI_2K_20140305_TEF_IOP_OV2");
		Assert.assertNull(cpl);
	}
}
