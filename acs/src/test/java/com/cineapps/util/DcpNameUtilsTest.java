/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import org.junit.Assert;
import org.junit.Test;

public class DcpNameUtilsTest {

	@Test
	public void testGetMovieName() {
		Assert.assertEquals("AmericanBluff", DcpNameUtils
		        .getMovieName("AmericanBluff_FTR-2_S_FR-FR_51-VI_4K_20140128_TEF_IOP_OV"));
		Assert.assertEquals(
		        "BelleEtLaBete",
		        DcpNameUtils
		                .getMovieName("BelleEtLaBete_FTR_S_FR-OCAP_FR_51-VI_2K_20140207_TEF_VF.GCDT271FA21D"));
		Assert.assertEquals("DIE-HARD-5", DcpNameUtils.getMovieName("DIE-HARD-5"));
	}

	@Test
	public void testIsVIVersion() {
		Assert.assertNull(DcpNameUtils.isVIVersion(null));
		Assert.assertTrue(DcpNameUtils
		        .isVIVersion("AmericanBluff_FTR-2_S_FR-FR_51-VI_4K_20140128_TEF_IOP_OV"));
		Assert.assertFalse(DcpNameUtils
		        .isVIVersion("AmericanBluff_FTR-2_S_FR-FR_51_4K_20140128_TEF_IOP_OV"));
		Assert.assertFalse(DcpNameUtils
		        .isVIVersion("SUPER-VISION_FTR-2_S_FR-FR_51_4K_20140128_TEF_IOP_OV"));
		Assert.assertFalse(DcpNameUtils.isVIVersion("NON-DCP-NAMING-CONVENTION"));
	}

	@Test
	public void testIsForHearingImpaired() {
		Assert.assertTrue(DcpNameUtils
		        .isForHearingImpaired("BelleEtLaBete_FTR_S_FR-OCAP_FR_51-VI_2K_20140207_TEF_VF.GCDT271FA21D"));
		Assert.assertTrue(DcpNameUtils
		        .isForHearingImpaired("BelleEtLaBete_FTR_S_FR-SME_FR_51-VI_2K_20140207_TEF_VF.GCDT271FA21D"));
		Assert.assertTrue(DcpNameUtils
		        .isForHearingImpaired("BelleEtLaBete_FTR_S_FR-HOH_FR_51-VI_2K_20140207_TEF_VF.GCDT271FA21D"));
		Assert.assertFalse(DcpNameUtils
		        .isForHearingImpaired("AmericanBluff_FTR-2_S_FR-FR_51_4K_20140128_TEF_IOP_OV"));
		Assert.assertFalse(DcpNameUtils.isForHearingImpaired("NON-DCP-NAMING-CONVENTION"));
	}
}
