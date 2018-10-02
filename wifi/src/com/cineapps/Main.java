/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps;

import java.io.IOException;
import java.net.InetAddress;

public class Main {

	public static void main(String[] args) throws IOException {
		InetAddress inet = InetAddress.getByName("192.168.0.1");
		boolean res = inet.isReachable(1000);
		System.out.println(res);
	}
}
