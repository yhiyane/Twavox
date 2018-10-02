/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.mediablock;

import org.junit.Assert;
import org.junit.Test;

public class MediablockTypeTest {

	@Test
	public void testFromName() {
		Assert.assertEquals(MediablockType.DOLBY, MediablockType.fromName("DOLBY"));
		Assert.assertEquals(MediablockType.DOREMI, MediablockType.fromName("DOREMI"));
		Assert.assertEquals(MediablockType.GDC, MediablockType.fromName("GDC"));
		Assert.assertEquals(MediablockType.QUBE, MediablockType.fromName("QUBE"));
		Assert.assertEquals(MediablockType.SONY, MediablockType.fromName("SONY"));
		Assert.assertNull(MediablockType.fromName("OTHER"));
	}
}
