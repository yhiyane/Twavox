/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cineapps.model.mediablock.MediablockSpl;
import com.cineapps.model.mediablock.MediablockState;
import com.dvidea.core.Rate;
import com.dvidea.core.mediablock.plugin.CPLInfos;
import com.dvidea.core.mediablock.plugin.IPlugin;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLElementInfos;
import com.dvidea.core.mediablock.plugin.SPLInfos;

public class MediablockServiceTest {

	private MediablockService service = new MediablockService();

	@Before
	public void init() {
		service.setPlugin(null);
	}

	@Test
	public void testGetPlugin() {
		// Spy
		MediablockService spyService = spy(service);
		// Stub
		doNothing().when(spyService).initPlugin(anyString(), anyInt(), anyString(), anyString(),
		        anyString());
		// Call
		String host = "host";
		int port = 1234;
		String login = "login";
		String password = "password";
		String type = "type";
		spyService.connect(host, port, login, password, type);
		// Verify
		verify(spyService).initPlugin(eq(host), eq(port), eq(login), eq(password), eq(type));
	}

	@Test
	public void testGetPluginInitialized() {
		// Mock
		IPlugin expected = mock(IPlugin.class);
		// Spy
		MediablockService spyService = spy(service);
		// Stub
		doNothing().when(spyService).initPlugin(anyString(), anyInt(), anyString(), anyString(),
		        anyString());
		spyService.setPlugin(expected);
		// Call
		String host = "host";
		int port = 1234;
		String login = "login";
		String password = "password";
		String type = "type";
		spyService.connect(host, port, login, password, type);
		IPlugin actual = spyService.getPlugin();
		// Verify
		verify(spyService, times(0)).initPlugin(anyString(), anyInt(), anyString(), anyString(),
		        anyString());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetMediablockState() {
		MediablockState mediablockState = service.getMediablockState();
		Assert.assertNotNull(mediablockState);
	}

	@Test
	public void testGetCurrentPlaylist() {
		// Value
		UUID plId = UUID.randomUUID();
		String plName = "PLAYLIST_FTR_FR-XX_51_20140410_OV";
		Rate rate = new Rate(24, 1);

		// Mock
		// Spls
		SPLInfos spl = mock(SPLInfos.class);
		when(spl.getUuid()).thenReturn(UUID.randomUUID());
		// Cpls
		CPLInfos adv1 = new CPLInfos();
		adv1.setId(UUID.randomUUID());
		adv1.setName("ADV1");
		adv1.setEditableUnitDuration(240); // 10 s
		adv1.setEditRate(rate);
		CPLInfos adv2 = new CPLInfos();
		adv2.setId(UUID.randomUUID());
		adv2.setName("ADV2");
		adv2.setEditableUnitDuration(192); // 8 s
		adv2.setEditRate(rate);
		CPLInfos cpl = new CPLInfos();
		cpl.setId(UUID.randomUUID());
		cpl.setName(plName);
		cpl.setEditableUnitDuration(24 * 60 * 60);
		cpl.setEditRate(rate);
		List<CPLInfos> cpls = Arrays.asList(adv1, adv2, cpl);
		// SplElementInfo
		SPLElementInfos element1 = new SPLElementInfos();
		element1.setContent(adv1);
		SPLElementInfos element2 = new SPLElementInfos();
		element2.setContent(adv2);
		SPLElementInfos element3 = new SPLElementInfos();
		element3.setContent(cpl);
		List<SPLElementInfos> splElementInfos = Arrays.asList(element1, element2, element3);

		// Stub spl
		when(spl.getCpls()).thenReturn(cpls);
		when(spl.getContentList()).thenReturn(splElementInfos);

		// Plugin
		IPlugin plugin = mock(IPlugin.class);
		PluginStatus pluginStatus = mock(PluginStatus.class);
		when(plugin.getStatus()).thenReturn(pluginStatus);
		when(plugin.getSPL(eq(plId))).thenReturn(spl);
		when(plugin.getCPLs()).thenReturn(cpls);
		when(pluginStatus.getPlId()).thenReturn(plId);

		// Call
		service.setPlugin(plugin);
		MediablockSpl mediablockSpl = service.getCurrentPlaylist();

		// Verify
		Assert.assertNotNull(mediablockSpl);
		Assert.assertEquals(plName, mediablockSpl.getFeatureName());
		Assert.assertEquals(18000, mediablockSpl.getPreshowDuration());
	}

	@Test
	public void testGetCurrentPlaylistSplWithoutCpl() {
		// Value
		UUID plId = UUID.randomUUID();
		// Mock
		// Spls
		SPLInfos spl1 = mock(SPLInfos.class);
		when(spl1.getUuid()).thenReturn(UUID.randomUUID());
		SPLInfos spl2 = mock(SPLInfos.class);
		when(spl2.getUuid()).thenReturn(plId);
		SPLInfos spl3 = mock(SPLInfos.class);
		when(spl3.getUuid()).thenReturn(UUID.randomUUID());
		List<SPLInfos> spls = Arrays.asList(spl1, spl2, spl3);
		// Cpls
		List<CPLInfos> cpls = Collections.emptyList();
		when(spl2.getCpls()).thenReturn(cpls);
		// Plugin
		IPlugin plugin = mock(IPlugin.class);
		PluginStatus pluginStatus = mock(PluginStatus.class);
		when(plugin.getStatus()).thenReturn(pluginStatus);
		when(plugin.getSPLs()).thenReturn(spls);
		when(pluginStatus.getPlId()).thenReturn(plId);

		// Call
		service.setPlugin(plugin);
		MediablockSpl mediablockSpl = service.getCurrentPlaylist();

		// Verify
		Assert.assertNull(mediablockSpl);
	}

	@Test
	public void testGetCurrentPlaylistNotFound() {
		// Value
		UUID plId = UUID.randomUUID();
		// Mock
		// Spls
		SPLInfos spl1 = mock(SPLInfos.class);
		when(spl1.getUuid()).thenReturn(UUID.randomUUID());
		SPLInfos spl2 = mock(SPLInfos.class);
		when(spl2.getUuid()).thenReturn(UUID.randomUUID());
		SPLInfos spl3 = mock(SPLInfos.class);
		when(spl3.getUuid()).thenReturn(UUID.randomUUID());
		List<SPLInfos> spls = Arrays.asList(spl1, spl2, spl3);
		// Plugin
		IPlugin plugin = mock(IPlugin.class);
		PluginStatus pluginStatus = mock(PluginStatus.class);
		when(plugin.getStatus()).thenReturn(pluginStatus);
		when(plugin.getSPLs()).thenReturn(spls);
		when(pluginStatus.getPlId()).thenReturn(plId);

		// Call
		service.setPlugin(plugin);
		MediablockSpl mediablockSpl = service.getCurrentPlaylist();

		// Verify
		Assert.assertNull(mediablockSpl);
	}

	@Test
	public void testGetCurrentPlaylistFtr() {
		// Value
		UUID plId = UUID.randomUUID();
		String plName = "PLAYLIST_FTR_FR-XX_51_20140410_OV";
		Rate rate = new Rate(24, 1);

		// Mock
		// Spls
		SPLInfos spl = mock(SPLInfos.class);
		when(spl.getUuid()).thenReturn(UUID.randomUUID());
		// Cpls
		CPLInfos cpl = new CPLInfos();
		cpl.setId(UUID.randomUUID());
		cpl.setName(plName);
		cpl.setEditableUnitDuration(24 * 60 * 60);
		cpl.setEditRate(rate);
		List<CPLInfos> cpls = Arrays.asList(cpl);
		// SplElementInfo
		SPLElementInfos element = new SPLElementInfos();
		element.setContent(cpl);
		List<SPLElementInfos> splElementInfos = Arrays.asList(element);

		// Stub spl
		when(spl.getCpls()).thenReturn(cpls);
		when(spl.getContentList()).thenReturn(splElementInfos);

		// Plugin
		IPlugin plugin = mock(IPlugin.class);
		PluginStatus pluginStatus = mock(PluginStatus.class);
		when(plugin.getStatus()).thenReturn(pluginStatus);
		when(plugin.getSPL(eq(plId))).thenReturn(spl);
		when(pluginStatus.getPlId()).thenReturn(plId);

		// Call
		service.setPlugin(plugin);
		MediablockSpl mediablockSpl = service.getCurrentPlaylist();

		// Verify
		Assert.assertNotNull(mediablockSpl);
		Assert.assertEquals(plName, mediablockSpl.getFeatureName());
	}

	@Test
	public void testGetCurrentPlaylistMultipleFtr() {
		// Value
		UUID plId = UUID.randomUUID();
		String plName = "PLAYLIST_FTR_FR-XX_51_20140410_OV";
		Rate rate = new Rate(24, 1);

		// Mock
		// Spls
		SPLInfos spl = mock(SPLInfos.class);
		when(spl.getUuid()).thenReturn(UUID.randomUUID());
		// Cpls
		CPLInfos adv1 = new CPLInfos();
		adv1.setId(UUID.randomUUID());
		adv1.setName("TEST_FTR_FR-XX_51_20140410_OV");
		adv1.setEditableUnitDuration(240); // 10 s
		adv1.setEditRate(rate);
		CPLInfos adv2 = new CPLInfos();
		adv2.setId(UUID.randomUUID());
		adv2.setName("TEST2_FTR_FR-XX_51_20140410_OV");
		adv2.setEditableUnitDuration(192); // 8 s
		adv2.setEditRate(rate);
		CPLInfos cpl = new CPLInfos();
		cpl.setId(UUID.randomUUID());
		cpl.setName(plName);
		cpl.setEditableUnitDuration(24 * 60 * 60);
		cpl.setEditRate(rate);
		List<CPLInfos> cpls = Arrays.asList(adv1, adv2, cpl);
		// SplElementInfo
		SPLElementInfos element1 = new SPLElementInfos();
		element1.setContent(adv1);
		SPLElementInfos element2 = new SPLElementInfos();
		element2.setContent(adv2);
		SPLElementInfos element3 = new SPLElementInfos();
		element3.setContent(cpl);
		List<SPLElementInfos> splElementInfos = Arrays.asList(element1, element2, element3);

		// Stub spl
		when(spl.getCpls()).thenReturn(cpls);
		when(spl.getContentList()).thenReturn(splElementInfos);

		// Plugin
		IPlugin plugin = mock(IPlugin.class);
		PluginStatus pluginStatus = mock(PluginStatus.class);
		when(plugin.getStatus()).thenReturn(pluginStatus);
		when(plugin.getSPL(eq(plId))).thenReturn(spl);
		when(plugin.getCPLs()).thenReturn(cpls);
		when(pluginStatus.getPlId()).thenReturn(plId);

		// Call
		service.setPlugin(plugin);
		MediablockSpl mediablockSpl = service.getCurrentPlaylist();

		// Verify
		Assert.assertNotNull(mediablockSpl);
		Assert.assertEquals(plName, mediablockSpl.getFeatureName());
		Assert.assertEquals(18000, mediablockSpl.getPreshowDuration());
	}

	@Test
	public void testGetCurrentPlaylistNotAccepted() {
		// Value
		UUID plId = UUID.randomUUID();
		String plName = "PLAYLIST_TLR_FR-XX_51_20140410_OV";
		Rate rate = new Rate(24, 1);

		// Mock
		// Spls
		SPLInfos spl = mock(SPLInfos.class);
		when(spl.getUuid()).thenReturn(UUID.randomUUID());

		// Cpls
		CPLInfos cpl = new CPLInfos();
		cpl.setId(UUID.randomUUID());
		cpl.setName(plName);
		cpl.setEditableUnitDuration(240); // 10 s
		cpl.setEditRate(rate);
		List<CPLInfos> cpls = Arrays.asList(cpl);
		// SplElementInfo
		SPLElementInfos element = new SPLElementInfos();
		element.setContent(cpl);
		List<SPLElementInfos> splElementInfos = Arrays.asList(element);

		// Stub spl
		when(spl.getCpls()).thenReturn(cpls);
		when(spl.getContentList()).thenReturn(splElementInfos);

		// Plugin
		IPlugin plugin = mock(IPlugin.class);
		PluginStatus pluginStatus = mock(PluginStatus.class);
		when(plugin.getStatus()).thenReturn(pluginStatus);
		when(plugin.getSPL(eq(plId))).thenReturn(spl);
		when(pluginStatus.getPlId()).thenReturn(plId);

		// Call
		service.setPlugin(plugin);
		MediablockSpl mediablockSpl = service.getCurrentPlaylist();

		// Verify
		Assert.assertNull(mediablockSpl);
	}

	@Test
	public void testInitPluginWrongType() {
		String wrongClass = "com.cineapps.wrong.InextantClass.class";
		service.initPlugin("ip", 1234, "login", "password", wrongClass);
		Assert.assertNull(service.getPlugin());
	}
}
