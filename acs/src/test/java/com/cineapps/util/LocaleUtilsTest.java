/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import org.junit.Assert;
import org.junit.Test;

public class LocaleUtilsTest {

	@Test
	public void testGetDisplqyName() {
		// French
		Assert.assertEquals("french", LocaleUtils.getDisplayLanguage("French"));
		Assert.assertEquals("french", LocaleUtils.getDisplayLanguage("french"));
		Assert.assertEquals("french", LocaleUtils.getDisplayLanguage("FR"));
		Assert.assertEquals("FR-dub", LocaleUtils.getDisplayLanguage("FR-dub"));

		// German
		Assert.assertEquals("GermanDubbed", LocaleUtils.getDisplayLanguage("GermanDubbed"));
		Assert.assertEquals("german", LocaleUtils.getDisplayLanguage("German"));

		// Dutch
		Assert.assertEquals("dutch", LocaleUtils.getDisplayLanguage("nl"));

		// Unkwnon
		Assert.assertEquals("unknown", LocaleUtils.getDisplayLanguage("Unknown"));
	}
}
