/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.dolby.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.cineapps.mediablock.core.dcinema.SPLElement;
import com.cineapps.mediablock.core.dcinema.SPLInfos;
import com.sun.xml.messaging.saaj.util.JaxmURI.MalformedURIException;

public class DolbyUtilsTest {

	@Test
	public void testDurationToSeconds() throws DatatypeConfigurationException {
		int hours = 1;
		int minutes = 20;
		int seconds = 5;
		int expectedMillis = hours * 60 * 60 * 1000 + minutes * 60 * 1000 + (int) seconds * 1000;
		Duration duration = DatatypeFactory.newInstance().newDuration(expectedMillis);
		int actualSeconds = DolbyUtils.durationToSeconds(duration);
		Assert.assertEquals(expectedMillis / 1000, actualSeconds);
	}

	@Test
	public void testToJavaUUID() throws MalformedURIException {
		UUID uuid = UUID.randomUUID();
		String dolbyUuidStr = new StringBuilder("urn:uuid:").append(uuid.toString()).toString();
		Assert.assertEquals(uuid, DolbyUtils.toJavaUUID(dolbyUuidStr));
	}

	@Test
	public void testToDolbyUUID() throws MalformedURIException {
		UUID uuid = java.util.UUID.randomUUID();
		String dolbyUuidStr = new StringBuilder("urn:uuid:").append(uuid.toString()).toString();
		Assert.assertEquals(dolbyUuidStr, DolbyUtils.toDolbyUUID(uuid));
	}

	@Test
	public void testToSPLInfos() throws UnsupportedEncodingException, IOException {
	/*	File showXmlFile = FileUtils.getFile("src", "main", "resources", "dolby",
		        "show.xml");
		System.out.println(showXmlFile.getPath());
		String showXml = new String(Files.readAllBytes(Paths.get("/src/main/resources/dolby/show.xml")), "UTF-8");
		SPLInfos splInfos = DolbyUtils.toSPLInfos(showXml);

		List<SPLElement> elements = splInfos.getElements();
		Assert.assertEquals(9, elements.size());

		Assert.assertEquals("JBF_BLACK_20SEC", elements.get(0).getName());
		Assert.assertEquals(20000, elements.get(0).getDurationInMs());
		Assert.assertEquals("CSI CARTON PANO ADV 2D 24 F FR XX FR XX 2K 20141021 KINEPOLIS IOP",
		        elements.get(1).getName());
		Assert.assertEquals(7000, elements.get(1).getDurationInMs());
		Assert.assertEquals(
		        "KIN-ULTRABILLBOARDv1_ADV_2D_24_C_FR_XX_FR_51_2K_20150603_KINEPOLIS_IOP", elements
		                .get(2).getName());
		Assert.assertEquals(17000, elements.get(2).getDurationInMs());
		Assert.assertEquals("75632CFABANQUES_ADV_F_FR_FR_51_2K_20150403_CCI", elements.get(3)
		        .getName());
		Assert.assertEquals(13000, elements.get(3).getDurationInMs());
		Assert.assertEquals("LaFemmeAuTableau_TLR_F-239_FR-XX_FR_51_2K_20150524_ECL_IOP_OV",
		        elements.get(4).getName());
		Assert.assertEquals(76000, elements.get(4).getDurationInMs());
		Assert.assertEquals("JBF_BLACK_03SEC", elements.get(5).getName());
		Assert.assertEquals(3000, elements.get(5).getDurationInMs());
		Assert.assertEquals("JBF_BLACK_03SEC", elements.get(6).getName());
		Assert.assertEquals(3000, elements.get(6).getDurationInMs());
		Assert.assertEquals("Maggie_FTR_S_FR-FR-OCAP_FR_51_2K_20150505_TEF_IOP_OV", elements.get(7)
		        .getName());
		Assert.assertEquals(5713000, elements.get(7).getDurationInMs());
		Assert.assertEquals("JBF_BLACK_03SEC", elements.get(8).getName());
		Assert.assertEquals(3000, elements.get(8).getDurationInMs());*/
	}
}
