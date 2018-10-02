/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.utils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class SystemUtils {

	/**
	 * Returns one MAC address of the machine like "64-B9-E8-B9-16-DE"
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static String getMacAddress() throws SocketException {
		Enumeration<NetworkInterface> is = NetworkInterface.getNetworkInterfaces();
		while (is.hasMoreElements()) {
			NetworkInterface networkInterface = is.nextElement();
			byte[] mac = networkInterface.getHardwareAddress();
			if (mac != null) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				}
				return sb.toString();
			}
		}
		return null;
	}

	/**
	 * Returns true if the MAC parameter is one of the MAC address of the
	 * machine, false otherwise.
	 * 
	 * @param macParam
	 * @return
	 * @throws SocketException
	 */
	public static boolean isMacAddressValid(String macParam) throws SocketException {
		Enumeration<NetworkInterface> is = NetworkInterface.getNetworkInterfaces();
		while (is.hasMoreElements()) {
			NetworkInterface networkInterface = is.nextElement();
			byte[] mac = networkInterface.getHardwareAddress();
			if (mac != null) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				}
				if (sb.toString().equals(macParam)) {
					return true;
				}
			}
		}
		return false;
	}

}
