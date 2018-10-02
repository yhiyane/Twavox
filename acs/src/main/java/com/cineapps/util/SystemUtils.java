/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class SystemUtils {

	// eth0 for Linux, en0 for Mac
	private final static List<String> interfaces = Arrays.asList("en0", "eth0");

	// @formatter:off
	/**
	 * Returns the MAC address of the machine like "64-B9-E8-B9-16-DE" 
	 * or null if no interface is activated.
	 */
	// @formatter:on
	public static String getMacAddress() throws SocketException {
		Enumeration<NetworkInterface> is = NetworkInterface.getNetworkInterfaces();
		while (is.hasMoreElements()) {
			NetworkInterface networkInterface = is.nextElement();
			if (interfaces.contains(networkInterface.getDisplayName())) {
				byte[] mac = networkInterface.getHardwareAddress();
				if (mac != null) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], i < (mac.length - 1) ? "-" : ""));
					}
					return sb.toString();
				}
			}
		}
		return null;
	}

	// @formatter:off
	/**
	 * Returns true if the MAC parameter is the MAC address of the machine,
	 * false otherwise.
	 * 
	 * @param macParam
	 * @return
	 * @throws SocketException
	 */
	// @formatter:on
	public static boolean isMacAddressValid(String macParam) throws SocketException {
		if (macParam == null) {
			return false;
		}
		String mac = getMacAddress();
		if (mac == null) {
			return false;
		}
		return mac.equals(macParam);
	}

}
