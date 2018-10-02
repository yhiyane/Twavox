/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UdpSample {

	public static void main(String[] args) throws IOException {
		String broadcastIp = "192.168.0.255";
		int port = 17152;
		String content = "Ok send the " + new Date();

		DatagramSocket socket = new DatagramSocket();
		InetAddress ipAddress = InetAddress.getByName(broadcastIp);
		byte[] sendData = content.getBytes("UTF-8");

		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
		socket.send(sendPacket);
		socket.close();
		System.out.println("sent");
	}
}
