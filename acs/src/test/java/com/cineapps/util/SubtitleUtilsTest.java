/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.cineapps.manager.CplFilesManager;
import com.cineapps.model.cpl.CompositionPlaylist;
import com.cineapps.model.subtitle.SubtitleInfo;

public class SubtitleUtilsTest {

	@Test
	public void testExtractSubtitlesStartWithSubtitle() {
		String subtitlePath = FileUtils.getFile("src", "test", "resources", "subtitle",
		        "Django_Swiss_R1.xml").getPath();
		List<Node> nodes = SubtitleUtils.extractSubtitles(subtitlePath);
		Assert.assertFalse(nodes.isEmpty());
		Assert.assertEquals(152, nodes.size());
		Assert.assertEquals("Subtitle", nodes.get(0).getNodeName());
	}

	@Test
	public void testExtractSubtitlesStartWithFont() {
		String subtitlePath = FileUtils.getFile("src", "test", "resources", "subtitle",
		        "LeDernierDiamant_R01.xml").getPath();
		List<Node> nodes = SubtitleUtils.extractSubtitles(subtitlePath);
		Assert.assertFalse(nodes.isEmpty());
		Assert.assertEquals(310, nodes.size());
		Assert.assertEquals("Font", nodes.get(0).getNodeName());
	}

	@Test
	public void testExtractSubtitlesInexistantFile() {
		String path = "path/to/inexisting/file";
		List<Node> nodes = SubtitleUtils.extractSubtitles(path);
		Assert.assertEquals(0, nodes.size());
	}

	@Test
	public void testNodeToString() throws Exception {
		String expected = "<Subtitle FadeDownTime=\"20\" FadeUpTime=\"20\" SpotNumber=\"144\" TimeIn=\"00:13:58:104\" TimeOut=\"00:14:00:031\"><Text VAlign=\"bottom\" VPosition=\"14.00\">Was starren die alle so?</Text></Subtitle>";

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document document = docBuilder.parse(new ByteArrayInputStream(expected.getBytes()));
		Node node = document.getFirstChild();

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		String result = SubtitleUtils.nodeToString(transformer, node);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testNodeToStringNull() throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		String result = SubtitleUtils.nodeToString(transformer, null);
		Assert.assertNull(result);
	}

	@Test
	public void testExtractLanguage() {
		String subtitleFilePath = FileUtils.getFile("src", "test", "resources", "subtitle",
		        "LeDernierDiamant_R01.xml").getPath();
		Assert.assertEquals("french", SubtitleUtils.extractLanguage(subtitleFilePath));
	}

	@Test
	public void testPrepareSubtitleInfoNoMultiST() {
		String rootDirectory = FileUtils.getFile("src", "test", "resources", "dcp", "DCP-MULTI-ST")
		        .getPath();
		boolean isMultiSubtitleActivate = false;
		CplFilesManager cplFilesManager = FileSystemUtils.findCplFiles(rootDirectory);
		List<CompositionPlaylist> cpls = cplFilesManager.getCpls();
		Assert.assertEquals(4, cpls.size());

		List<SubtitleInfo> subtitleInfos = SubtitleUtils.prepareSubtitleInfo(rootDirectory, cpls,
		        cpls.get(0), isMultiSubtitleActivate, Arrays.asList("french"), 17152);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo french = subtitleInfos.get(0);
		Assert.assertEquals("french", french.getLang());
		Assert.assertEquals(17152, french.getPort());
		Assert.assertFalse(french.isOptional());
	}

	@Test
	public void testPrepareSubtitleInfoWithMultiST() {
		String rootDirectory = FileUtils.getFile("src", "test", "resources", "dcp", "DCP-MULTI-ST")
		        .getPath();
		boolean isMultiSubtitleActivate = true;
		CplFilesManager cplFilesManager = FileSystemUtils.findCplFiles(rootDirectory);
		List<CompositionPlaylist> cpls = cplFilesManager.getCpls();
		Assert.assertEquals(4, cpls.size());

		List<SubtitleInfo> subtitleInfos = SubtitleUtils.prepareSubtitleInfo(rootDirectory, cpls,
		        cpls.get(0), isMultiSubtitleActivate, Arrays.asList("french"), 17152);
		Assert.assertEquals(4, subtitleInfos.size());

		Map<String, SubtitleInfo> expecteds = getPrepareSubtitleInfoWithMultiSTOcapExpected();
		List<Integer> ports = new ArrayList<>();

		for (SubtitleInfo actual : subtitleInfos) {
			String lang = actual.getLang();
			int port = actual.getPort();
			SubtitleInfo expected = expecteds.get(lang);
			Assert.assertNotNull(expected);
			Assert.assertEquals(expected.isOptional(), actual.isOptional());
			if ("french".equals(lang)) {
				Assert.assertEquals(17152, port);
			}
			Assert.assertTrue(!ports.contains(port));
			ports.add(port);
		}
	}

	@Test
	public void testPrepareSubtitleInfoWithMultiSTOcap() {
		String rootDirectory = FileUtils.getFile("src", "test", "resources", "dcp",
		        "DCP-MULTI-ST-OCAP").getPath();
		boolean isMultiSubtitleActivate = true;
		CplFilesManager cplFilesManager = FileSystemUtils.findCplFiles(rootDirectory);
		List<CompositionPlaylist> cpls = cplFilesManager.getCpls();
		Assert.assertEquals(5, cpls.size());

		List<SubtitleInfo> subtitleInfos = SubtitleUtils.prepareSubtitleInfo(rootDirectory, cpls,
		        cpls.get(0), isMultiSubtitleActivate, Arrays.asList("french"), 17152);
		Assert.assertEquals(4, subtitleInfos.size());

		Map<String, SubtitleInfo> expecteds = getPrepareSubtitleInfoWithMultiSTOcapExpected();
		List<Integer> ports = new ArrayList<>();

		for (SubtitleInfo actual : subtitleInfos) {
			String lang = actual.getLang();
			int port = actual.getPort();
			SubtitleInfo expected = expecteds.get(lang);
			Assert.assertNotNull(expected);
			Assert.assertEquals(expected.isOptional(), actual.isOptional());
			if ("french".equals(lang)) {
				Assert.assertEquals(17152, port);
			}
			Assert.assertTrue(!ports.contains(port));
			ports.add(port);
		}
	}

	private Map<String, SubtitleInfo> getPrepareSubtitleInfoWithMultiSTOcapExpected() {
		Map<String, SubtitleInfo> expected = new HashMap<>();
		SubtitleInfo ocap = new SubtitleInfo("dcp title", null, 0, 0, "french");
		ocap.setPort(17152);
		ocap.setOptional(false);
		expected.put(ocap.getLang(), ocap);
		SubtitleInfo english = new SubtitleInfo("dcp title", null, 0, 0, "english");
		english.setOptional(true);
		expected.put(english.getLang(), english);
		SubtitleInfo spanish = new SubtitleInfo("dcp title", null, 0, 0, "spanish");
		spanish.setOptional(true);
		expected.put(spanish.getLang(), spanish);
		SubtitleInfo german = new SubtitleInfo("dcp title", null, 0, 0, "german");
		german.setOptional(true);
		expected.put(german.getLang(), german);
		return expected;
	}

	@Test
	public void testPrepareSubtitleInfoMazeRunnner() {
		String rootDirectory = FileUtils.getFile("src", "test", "resources", "dcp", "MazeRunner")
		        .getPath();
		CplFilesManager cplFilesManager = FileSystemUtils.findCplFiles(rootDirectory);
		List<CompositionPlaylist> cpls = cplFilesManager.getCpls();
		CompositionPlaylist currentCpl = cplFilesManager
		        .findByName("MazeRunner_FTR_S_FR-XX_FR_51-DBOX_4K_TCF_20140901_DUK_IOP_OV");
		List<SubtitleInfo> subtitleInfos = SubtitleUtils.prepareSubtitleInfo(rootDirectory, cpls,
		        currentCpl, false, Arrays.asList("french", "fr"), 17152);

		Assert.assertEquals(1, subtitleInfos.size());
		Assert.assertEquals("MazeRunner_FTR_S_EN-FR_FR_71-DBOX_4K_TCF_20140902_DUK_IOP_VF",
		        subtitleInfos.get(0).getDcpTitle());
		Assert.assertEquals("french", subtitleInfos.get(0).getLang());
		Assert.assertEquals(17152, subtitleInfos.get(0).getPort());
	}

	@Test
	public void testPrepareSubtitleInfoLesProfs2() {
		String rootDirectory = FileUtils.getFile("src", "test", "resources", "dcp", "LesProfs2")
		        .getPath();
		CplFilesManager cplFilesManager = FileSystemUtils.findCplFiles(rootDirectory);
		List<CompositionPlaylist> cpls = cplFilesManager.getCpls();
		CompositionPlaylist currentCpl = cplFilesManager
		        .findByName("LesProfs2_FTR_S_FR-XX_FR_51-HI-VI_2K_UGC_20150617_ECL_IOP_OV");
		List<SubtitleInfo> subtitleInfos = SubtitleUtils.prepareSubtitleInfo(rootDirectory, cpls,
		        currentCpl, false, Arrays.asList("french", "fr"), 17152);

		Assert.assertEquals(1, subtitleInfos.size());
		Assert.assertEquals(5, subtitleInfos.get(0).getSubtitleFiles().size());
	}
}
