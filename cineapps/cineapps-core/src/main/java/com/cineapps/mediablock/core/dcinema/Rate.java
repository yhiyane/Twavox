/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.dcinema;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rate implements Serializable {

	private static final long serialVersionUID = 8674260982578463790L;
	private final int numerator;
	private final int denominator;

	public Rate() {
		this.numerator = 0;
		this.denominator = 1;
	}

	public Rate(int value) {
		this.numerator = value;
		this.denominator = 1;
	}

	public Rate(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public Rate(String rate) throws IllegalArgumentException {
		Pattern pattern = Pattern.compile("\\s*([0-9]+)\\s*([0-9]+)\\s*");
		Matcher matcher = pattern.matcher(rate);

		if (matcher.matches()) {
			this.numerator = Integer.parseInt(matcher.group(1));
			this.denominator = Integer.parseInt(matcher.group(2));
		} else {
			throw new IllegalArgumentException("Invalid input for a rate : " + rate);
		}
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

}
