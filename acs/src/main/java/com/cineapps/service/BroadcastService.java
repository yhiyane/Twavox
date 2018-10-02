/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * Service used to broadcast a content throught the wi-fi
 */
public class BroadcastService implements IService {

	private static final Logger logger = Logger.getLogger(BroadcastService.class);
	private static final String JOB_GROUP = "BroadcastJobGroup";
	private DatagramSocket socket;
	private InetAddress ipAddress;
	private String broadcastIp;

	public BroadcastService(String broadcastIp) {
		this.broadcastIp = broadcastIp;
	}

	/**
	 * Broadcast the text to the selected port.
	 * 
	 * @param content
	 * @param port
	 */
	public void broadcast(String content, int port) {
		if (socket == null) {
			startProcess();
		}

		try {
			byte[] sendData = content.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress,
			        port);
			socket.send(sendPacket);
		} catch (UnsupportedEncodingException e) {
			logger.error("Cannot encode text to UTF-8");
			logger.error(e);
		} catch (IOException e) {
			logger.error("Cannot send the packet");
			logger.error(e);
		}
	}

	@Override
	public void startProcess() {
		try {
			socket = new DatagramSocket();
			ipAddress = InetAddress.getByName(broadcastIp);
		} catch (SocketException | UnknownHostException e) {
			logger.error(e);
			stopProcess();
		}
	}

	@Override
	public void stopProcess() {
		if (socket != null) {
			socket.close();
		}
		socket = null;
		ipAddress = null;
	}

	@Override
	public String getJobGroup() {
		return JOB_GROUP;
	}

	// For test purpose

	protected void initWithParams(DatagramSocket socket, InetAddress ipAddress) {
		this.socket = socket;
		this.ipAddress = ipAddress;
	}

	protected boolean isNull() {
		return socket == null && ipAddress == null;
	}
}
