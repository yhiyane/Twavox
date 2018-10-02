/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.cineapps.model.cpl.CompositionPlaylist;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel.AssetList;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel.AssetList.MainPicture;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel.AssetList.MainSound;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle;
import com.cineapps.model.subtitle.SubtitleFile;
import com.cineapps.model.subtitle.SubtitleInfo;

public class CplUtilsTest {

	@Test
	public void testParseWithSubtitleVersion() {
		File file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_33947920-b653-412e-9aa1-1146149ec692.xml");
		CompositionPlaylist cpl = CplUtils.parseFromFile(file.getPath());

		Assert.assertNotNull(cpl);
		Assert.assertEquals("urn:uuid:33947920-b653-412e-9aa1-1146149ec692", cpl.getId());

		List<Reel> reel = cpl.getReelList().getReel();
		Assert.assertEquals(8, reel.size());
		MainPicture mainPicture;
		MainSound mainSound;
		List<MainSubtitle> mainSubtitles;
		MainSubtitle mainSubtitle;

		// Reel 0
		AssetList assetList = reel.get(0).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNotNull(mainPicture);
		Assert.assertEquals("urn:uuid:d1b36e8e-5a2b-4499-a776-5c5df528e659", mainPicture.getId());
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:eb91b220-1e03-4212-984c-ecbadb0b9567", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertTrue(mainSubtitles.isEmpty());

		// Reel 1
		assetList = reel.get(1).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNotNull(mainPicture);
		Assert.assertEquals("urn:uuid:a7796d82-0654-440d-90f6-aa0150908039", mainPicture.getId());
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:74cce9e3-047d-4149-80c2-e443db40af64", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertNotNull(mainSubtitle);
		Assert.assertEquals("urn:uuid:9bbeda71-7590-4919-b43a-338d792fb326", mainSubtitle.getId());

		// Reel 2
		assetList = reel.get(2).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNotNull(mainPicture);
		Assert.assertEquals("urn:uuid:76bcf4d4-cc90-4af0-9c0a-011df782e38c", mainPicture.getId());
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:8d9de369-1e87-42dc-bf58-5179dc61807e", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertNotNull(mainSubtitle);
		Assert.assertEquals("urn:uuid:5483609d-7ac5-4dc6-b234-6ea49513bc29", mainSubtitle.getId());
	}

	@Test
	public void testParseWithBasicVersion() {
		File file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_3576edab-eca5-48e2-8117-b1d60d822162.xml");
		CompositionPlaylist cpl = CplUtils.parseFromFile(file.getPath());

		Assert.assertNotNull(cpl);
		Assert.assertEquals("urn:uuid:3576edab-eca5-48e2-8117-b1d60d822162", cpl.getId());

		List<Reel> reel = cpl.getReelList().getReel();
		Assert.assertEquals(8, reel.size());
		MainPicture mainPicture;
		MainSound mainSound;
		List<MainSubtitle> mainSubtitles;

		// Reel 0
		AssetList assetList = reel.get(0).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNotNull(mainPicture);
		Assert.assertEquals("urn:uuid:d1b36e8e-5a2b-4499-a776-5c5df528e659", mainPicture.getId());
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:eb91b220-1e03-4212-984c-ecbadb0b9567", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertTrue(mainSubtitles.isEmpty());

		// Reel 1
		assetList = reel.get(1).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNotNull(mainPicture);
		Assert.assertEquals("urn:uuid:a7796d82-0654-440d-90f6-aa0150908039", mainPicture.getId());
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:74cce9e3-047d-4149-80c2-e443db40af64", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertTrue(mainSubtitles.isEmpty());

		// Reel 2
		assetList = reel.get(2).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNotNull(mainPicture);
		Assert.assertEquals("urn:uuid:76bcf4d4-cc90-4af0-9c0a-011df782e38c", mainPicture.getId());
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:8d9de369-1e87-42dc-bf58-5179dc61807e", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertTrue(mainSubtitles.isEmpty());
	}

	@Test
	public void testParseWithNoPictureVersion() {
		File file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_25648bf8-b463-4531-8d57-104f8fa7fa56.xml");
		CompositionPlaylist cpl = CplUtils.parseFromFile(file.getPath());

		Assert.assertNotNull(cpl);
		Assert.assertEquals("urn:uuid:25648bf8-b463-4531-8d57-104f8fa7fa56", cpl.getId());

		List<Reel> reel = cpl.getReelList().getReel();
		Assert.assertEquals(8, reel.size());
		MainPicture mainPicture;
		MainSound mainSound;
		List<MainSubtitle> mainSubtitles;

		// Reel 0
		AssetList assetList = reel.get(0).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNull(mainPicture);
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:eb91b220-1e03-4212-984c-ecbadb0b9567", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertTrue(mainSubtitles.isEmpty());

		// Reel 1
		assetList = reel.get(1).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNull(mainPicture);
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:74cce9e3-047d-4149-80c2-e443db40af64", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertTrue(mainSubtitles.isEmpty());

		// Reel 2
		assetList = reel.get(2).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertNull(mainPicture);
		mainSound = assetList.getMainSound();
		Assert.assertNotNull(mainSound);
		Assert.assertEquals("urn:uuid:8d9de369-1e87-42dc-bf58-5179dc61807e", mainSound.getId());
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertTrue(mainSubtitles.isEmpty());
	}

	@Test
	public void testParseHercule() {
		File file = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE",
		        "CPL_37947920-b653-412e-9aa1-1146149ec692.xml");
		CompositionPlaylist cpl = CplUtils.parseFromFile(file.getPath());
		List<Reel> reel = cpl.getReelList().getReel();
		MainSubtitle mainSubtitle;
		List<MainSubtitle> mainSubtitles;

		AssetList assetList = reel.get(1).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertNotNull(mainSubtitle);
		Assert.assertEquals(24825, mainSubtitle.getDuration());

		assetList = reel.get(2).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertNotNull(mainSubtitle);
		Assert.assertEquals(23755, mainSubtitle.getDuration());

		assetList = reel.get(3).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertNotNull(mainSubtitle);
		Assert.assertEquals(26222, mainSubtitle.getDuration());

		assetList = reel.get(4).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertNotNull(mainSubtitle);
		Assert.assertEquals(22951, mainSubtitle.getDuration());

		assetList = reel.get(5).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertNotNull(mainSubtitle);
		Assert.assertEquals(15355, mainSubtitle.getDuration());
	}

	@Test
	public void testParseValleyOfLove() {
		File file = FileUtils.getFile("src", "test", "resources", "dcp", "VALLEYOFLOVE",
		        "39ae8ea7-26b3-4cdd-bca0-edd2f7c3e5d4.xml");
		CompositionPlaylist cpl = CplUtils.parseFromFile(file.getPath());
		List<Reel> reel = cpl.getReelList().getReel();
		Assert.assertEquals(7, reel.size());
		AssetList assetList;
		MainSubtitle mainSubtitle;
		MainPicture mainPicture;
		List<MainSubtitle> mainSubtitles;

		assetList = reel.get(0).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertEquals(199, mainPicture.getDuration());

		assetList = reel.get(1).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertEquals(240, mainPicture.getDuration());

		assetList = reel.get(2).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertEquals(126, mainPicture.getDuration());

		assetList = reel.get(3).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertEquals(144, mainPicture.getDuration());

		assetList = reel.get(4).getAssetList();
		mainPicture = assetList.getMainPicture();
		Assert.assertEquals(120, mainPicture.getDuration());

		assetList = reel.get(5).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertEquals(65710, mainSubtitle.getDuration());
		mainPicture = assetList.getMainPicture();
		Assert.assertEquals(65710, mainPicture.getDuration());

		assetList = reel.get(6).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(1, mainSubtitles.size());
		mainSubtitle = mainSubtitles.get(0);
		Assert.assertEquals(66200, mainSubtitle.getDuration());
		mainPicture = assetList.getMainPicture();
		Assert.assertEquals(66200, mainPicture.getDuration());
	}

	@Test
	public void testParseSkyfallCCAP() {
		File file = FileUtils.getFile("src", "test", "resources", "ccap", "SKYFALL",
		        "5868e4bd-a147-44e8-98ad-8f928f4b1ac0.xml");
		CompositionPlaylist cpl = CplUtils.parseFromFile(file.getPath());
		List<Reel> reel = cpl.getReelList().getReel();
		Assert.assertEquals(1, reel.size());
		AssetList assetList;
		MainSubtitle mainSubtitle;
		List<MainSubtitle> mainSubtitles;

		assetList = reel.get(0).getAssetList();
		mainSubtitles = assetList.getMainSubtitle();
		Assert.assertEquals(6, mainSubtitles.size());

		mainSubtitle = mainSubtitles.get(0);
		Assert.assertEquals(3560, mainSubtitle.getDuration());
		Assert.assertEquals("urn:uuid:fd6dd096-eaac-4d2b-beb1-b6feeb8174b3", mainSubtitle.getId());
		Assert.assertEquals("pt", mainSubtitle.getLanguage());

		mainSubtitle = mainSubtitles.get(1);
		Assert.assertEquals(3560, mainSubtitle.getDuration());
		Assert.assertEquals("urn:uuid:75dfdb93-d838-471c-a5f6-9d5aa5ce6f80", mainSubtitle.getId());
		Assert.assertEquals("nl", mainSubtitle.getLanguage());

		mainSubtitle = mainSubtitles.get(2);
		Assert.assertEquals(3560, mainSubtitle.getDuration());
		Assert.assertEquals("urn:uuid:f61a06c4-1657-4160-8bbc-6d9677bd644f", mainSubtitle.getId());
		Assert.assertEquals("fr", mainSubtitle.getLanguage());

		mainSubtitle = mainSubtitles.get(3);
		Assert.assertEquals(3560, mainSubtitle.getDuration());
		Assert.assertEquals("urn:uuid:f50cbf25-bc17-4f11-8e16-bab427a4c477", mainSubtitle.getId());
		Assert.assertEquals("es", mainSubtitle.getLanguage());

		mainSubtitle = mainSubtitles.get(4);
		Assert.assertEquals(3560, mainSubtitle.getDuration());
		Assert.assertEquals("urn:uuid:c136217b-a769-419b-8f7b-6e5dac0b5fdc", mainSubtitle.getId());
		Assert.assertEquals("de", mainSubtitle.getLanguage());

		mainSubtitle = mainSubtitles.get(5);
		Assert.assertEquals(3560, mainSubtitle.getDuration());
		Assert.assertEquals("urn:uuid:8c18acef-3a39-4f5c-8427-4a86f8651693", mainSubtitle.getId());
		Assert.assertEquals("en", mainSubtitle.getLanguage());
	}

	@Test
	public void testGenerateSubtitleInfo() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "NOAH_FTR")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist cpl = CplUtils.parseFromFile(directory
		        + "CPL_5d5e705b-4f3b-4241-bd0d-4418f88a9244.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "CPL_19f7429a-4cfa-4383-9924-e442043a0746.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils
		        .generateSubtitleInfo(directory, cpl, currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(10, subtitleFiles.size());
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/35d37d80-82a0-4b90-9a5d-1d7d3be61bfd/35d37d80-82a0-4b90-9a5d-1d7d3be61bfd.xml",
		        subtitleFiles.get(0).getPath());
		Assert.assertEquals(0, subtitleFiles.get(0).getRelativeStartMillis());
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/bc0f8736-4d2f-4602-90e2-4d2b1fc3f66e/bc0f8736-4d2f-4602-90e2-4d2b1fc3f66e.xml",
		        subtitleFiles.get(1).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(3281, 24)
		        - subtitleFiles.get(1).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/1d8dbae9-e926-441f-b6c3-6e0e978a2951/1d8dbae9-e926-441f-b6c3-6e0e978a2951.xml",
		        subtitleFiles.get(2).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(7000, 24)
		        - subtitleFiles.get(2).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/7412c9f6-9098-4b23-82cb-058acb0e7733/Prince_Localized_French_R2.xml",
		        subtitleFiles.get(3).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(26903, 24)
		        - subtitleFiles.get(3).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/3979f09d-0348-4bb2-abcd-67242451834c/Prince_Localized_French_R3.xml",
		        subtitleFiles.get(4).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(58981, 24)
		        - subtitleFiles.get(4).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/961f3643-31f2-4964-9343-0f34bfe1272b/Prince_Localized_French_R4.xml",
		        subtitleFiles.get(5).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(88087, 24)
		        - subtitleFiles.get(5).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/836fd175-b8b3-4cf4-8609-c02ec4e1099b/Prince_Localized_French_R5.xml",
		        subtitleFiles.get(6).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(118764, 24)
		        - subtitleFiles.get(6).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/c018b2a6-ba25-4bbc-aa62-886a61382aed/Prince_Localized_French_R6.xml",
		        subtitleFiles.get(7).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(147520, 24)
		        - subtitleFiles.get(7).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/91f90a7e-4153-4551-85d7-2e32b886f0e5/91f90a7e-4153-4551-85d7-2e32b886f0e5.xml",
		        subtitleFiles.get(8).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(179506, 24)
		        - subtitleFiles.get(8).getRelativeStartMillis()) <= 10);
		Assert.assertEquals(
		        "src/test/resources/dcp/NOAH_FTR/36f9d418-a5c6-4e8e-8861-f7dcc1341e7f/36f9d418-a5c6-4e8e-8861-f7dcc1341e7f.xml",
		        subtitleFiles.get(9).getPath());
		Assert.assertTrue(Math.abs(TimeUtils.editRateToMillis(192364, 24)
		        - subtitleFiles.get(9).getRelativeStartMillis()) <= 10);

		Assert.assertEquals(TimeUtils.editRateToMillis(24, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	/**
	 * Miss 0a8529fe-b5fb-4c38-8d08-ff4f7b57c1c9 directory
	 */
	@Test
	public void testGenerateSubtitleInfoMissingReelFile() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "HERCULE")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		String cplPath = directory + "CPL_33947920-b653-412e-9aa1-1146149ec692.xml";
		CompositionPlaylist cpl = CplUtils.parseFromFile(cplPath);

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, cpl, cpl);

		Assert.assertNull(subtitleInfos);
	}

	@Test
	public void testGenerateSubtitleHippocrate() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "HIPPOCRATE")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "aa0b7c89-bbdb-45d2-b768-227526e16ddd.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "5123bbc9-a697-43e5-935c-e2c3a6e4f38b.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(1, subtitleFiles.size());
		Assert.assertEquals(TimeUtils.editRateToMillis(144, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	@Test
	public void testGenerateSubtitle3Coeurs1() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "3Coeurs")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "ebb913cc-087f-4a18-974d-f7d4ccdc799a.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "43ceb8f1-1412-47cc-a157-5bb85e1561ae.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(6, subtitleFiles.size());
		Assert.assertEquals(TimeUtils.editRateToMillis(510, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	@Test
	public void testGenerateSubtitle3Coeurs2() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "3Coeurs")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "ebb913cc-087f-4a18-974d-f7d4ccdc799a.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "c58f5f37-35c7-4384-b5db-23e5827662b5.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(6, subtitleFiles.size());
		Assert.assertEquals(TimeUtils.editRateToMillis(198, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	@Test
	public void testGenerateSubtitleAuFilDAriane() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "AuFilDAriane")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "ab2b23d6-c55f-4991-8f26-fdd97baf2e70.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "c289d867-1fae-45f1-aee8-c5cd088a5a2f.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(5, subtitleFiles.size());
		Assert.assertEquals(TimeUtils.editRateToMillis(0, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	@Test
	public void testGenerateSubtitleJimmysHall() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "JimmysHall")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "25e819a3-8958-4d5a-a553-c94f33626f13.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "c17e4146-61bb-4f3e-b9bb-2aaa5052883d.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(6, subtitleFiles.size());
		Assert.assertEquals(TimeUtils.editRateToMillis(514, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	@Test
	public void testGenerateSubtitleMazeRunner() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "MazeRunner")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "CPL_34f12ade-f1fc-41a0-8d84-dd3c94dde08a.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "CPL_e1d1ee2b-cb67-43cb-bc13-e7a76eff56ac.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(8, subtitleFiles.size());
		Assert.assertEquals(TimeUtils.editRateToMillis(-23, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	@Test
	public void testGenerateSubtitleAsterix() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp",
		        "AsterixLeDomainedesDieux").getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "CPL_4101c295-496c-43ab-bef9-4ea6e2ba043e.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "CPL_20ee1f1c-48f8-4a46-b9dd-393325de4113.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);

		Assert.assertNull(subtitleInfos);
	}

	@Test
	public void testGenerateSubtitle20AnsEcart() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "20-ANS-D-ECART")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "CPL_ce6faa99-1d72-e211-908a-00259001e9bd.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory + "CPL_standard.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertNull(subtitleInfos);
	}

	@Test
	public void testGenerateSubtitleAlice() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "AliceInWonderl")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "CPL_AliceInWonderl_FTR_F-133_FR-FR_FR-AA_51_DI_20130813_DSS_INT_VF.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl, subCpl);

		Assert.assertNull(subtitleInfos);
	}

	@Test
	public void testGenerateSubtitleUnbrokenDUB() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "Unbroken")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "CPL_bf4272f8-18a1-43c3-b8c5-a8e531bb3bbd.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "CPL_f481331c-1834-433a-b390-4ed6e7065199.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);

		Assert.assertNull(subtitleInfos);
	}

	@Test
	public void testGenerateSubtitleUnbroken() {
		String directory = FileUtils.getFile("src", "test", "resources", "dcp", "Unbroken")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist subCpl = CplUtils.parseFromFile(directory
		        + "CPL_f481331c-1834-433a-b390-4ed6e7065199.xml");
		CompositionPlaylist currentCpl = CplUtils.parseFromFile(directory
		        + "CPL_bf4272f8-18a1-43c3-b8c5-a8e531bb3bbd.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, subCpl,
		        currentCpl);
		Assert.assertEquals(1, subtitleInfos.size());
		SubtitleInfo subtitleInfo = subtitleInfos.get(0);

		Assert.assertNotNull(subtitleInfo);
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		Assert.assertEquals(8, subtitleFiles.size());
		Assert.assertEquals(TimeUtils.editRateToMillis(0, 24),
		        subtitleInfo.getPreMovieDurationMillis());
	}

	@Test
	public void testGenerateSubtitleSkyfallCcap() {
		String directory = FileUtils.getFile("src", "test", "resources", "ccap", "SKYFALL")
		        .getPath();
		directory += directory.endsWith("/") ? "" : "/";
		CompositionPlaylist cpl = CplUtils.parseFromFile(directory + "CPL_test_multireels.xml");

		List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(directory, cpl, cpl);
		Assert.assertEquals(6, subtitleInfos.size());
		int[] startMillisArray = new int[] { 0, 3560, 3560 * 2, 3560 * 3, 3560 * 4 };

		for (SubtitleInfo subtitleInfo : subtitleInfos) {
			List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
			int size = subtitleFiles.size();
			Assert.assertEquals(5, size);
			for (int i = 0; i < size; i++) {
				SubtitleFile subtitleFile = subtitleFiles.get(i);
				Assert.assertEquals(TimeUtils.editRateToMillis(startMillisArray[i], 24),
				        subtitleFile.getRelativeStartMillis());
			}
		}
	}
}
