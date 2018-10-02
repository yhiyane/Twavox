/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Assert;
import org.junit.Test;

public class BroadcastServiceTest {

	private BroadcastService service = new BroadcastService("192.168.0.255");

	@Test
	public void testStartProcess() throws IOException {
		DatagramSocket socket = null;
		InetAddress inetAddress = null;

		service.initWithParams(socket, inetAddress);
		service.startProcess();

		Assert.assertFalse(service.isNull());
	}

	@Test
	public void testBroadcast() throws IOException {
		DatagramSocket socket = mock(DatagramSocket.class);
		InetAddress inetAddress = mock(InetAddress.class);

		service.initWithParams(socket, inetAddress);
		service.broadcast("content", 1234);

		verify(socket, times(1)).send(any(DatagramPacket.class));
	}

	@Test
	public void testBroadcastWithCreation() throws IOException {
		DatagramSocket socket = null;
		InetAddress inetAddress = null;

		service.initWithParams(socket, inetAddress);
		service.broadcast("content", 1234);

		Assert.assertFalse(service.isNull());
	}

	@Test
	public void testStopProcess() throws IOException {
		DatagramSocket socket = mock(DatagramSocket.class);
		InetAddress inetAddress = mock(InetAddress.class);

		service.initWithParams(socket, inetAddress);
		Assert.assertFalse(service.isNull());

		service.stopProcess();

		Assert.assertTrue(service.isNull());
	}
}
