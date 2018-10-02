package com.cineapps.mediablock.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import com.cineapps.mediablock.core.logging.ErrorCommunicationLogger;

public class Utils {
	public static final int FRAME_PER_SEC = 25;

	public static Logger logger = ErrorCommunicationLogger.getLogger(Utils.class);

	/**
	 * This class is a utility class ( hold only static method ) instantiation
	 * should not be a option
	 */
	private Utils() {
		/* empty on purpose */
	}

	public static String byteArrayToString(byte[] a, String separator) {
		if (a == null) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		if (a.length > 0) {
			result.append(a[0]);
			for (int i = 1; i < a.length; i++) {
				result.append(separator);
				result.append(Integer.toHexString(a[i] & 0xff));
			}
		}
		return result.toString();
	}

	public static String getHourStringFromInt(int val, boolean frame) {
		String ret = "";
		if (frame) {
			val = val / FRAME_PER_SEC;
		}
		int hour = val / 3600;
		val = val - (3600 * hour);
		int min = val / 60;
		val = val - (60 * min);

		ret = String.format("%02d:%02d:%02d", hour, min, val);

		return ret;
	}

	public static byte[] cloneByteArray(byte b[]) {
		byte[] tmp;

		if (b == null) {
			return null;
		}

		tmp = new byte[b.length];
		System.arraycopy(b, 0, tmp, 0, b.length);

		return tmp;
	}

	public static String getBytesAsHexaString(byte[] b) {
		return getBytesAsHexaString(b, null);
	}

	public static String getBytesAsHexaString(byte[] b, String separator) {
		StringBuilder sb = new StringBuilder();
		if (b == null) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			if (separator != null && i > 0) {
				sb.append(separator);
			}
			Byte bb = new Byte(b[i]);
			sb.append(String.format("%02x", bb));
		}
		return sb.toString();
	}

	// TODO ugly, because we have to go through the whole array, only to convert
	// it from Byte[] to byte[]
	public static String byteListToString(List<Byte> l) {
		if (l == null) {
			return "";
		}
		byte[] array = new byte[l.size()];
		int i = 0;
		for (Byte current : l) {
			array[i] = current;
			i++;
		}
		return new String(array);
	}

	public static boolean[] octetTobit(Byte b) {
		if (b == null) {
			throw new IllegalArgumentException("The byte to explode was null.");
		}

		boolean[] octet = new boolean[8];
		int bitSetIndex = 7;
		for (int mask = 0x01; mask != 0x100; mask <<= 1) {
			boolean value = (b & mask) != 0;
			octet[bitSetIndex] = value;
			bitSetIndex--;
		}
		if (octet.length != 8) {
			throw new IllegalArgumentException("Un octet fait 8 bit");
		}
		return octet;
	}

	public static int getBytesFromHexaString(byte[] out, int offset, String s) throws Exception {
		int debut = 0;
		int bIndex = 0;
		while (true) {
			if (debut >= s.length()) {
				break;
			}
			String ss = s.substring(debut, debut + 2);
			int i = Integer.parseInt(ss, 16);
			out[offset + bIndex] = (byte) i;
			debut += 2;
			bIndex++;
		}
		return bIndex;
	}

	/**
	 * Indicates whether the given string is representing a number in
	 * hexadecimal mode or not (ie all the characters are numbers or letters
	 * between A and F)
	 * 
	 * @param stringToCheck
	 *            the string to check
	 * @return true if the string is an hexadecimal number, false if not
	 */
	public static boolean checkIfHexaDecimal(String stringTocheck) {
		if (null == stringTocheck) {
			return false;
		}
		Pattern hexaPattern = Pattern.compile("[0-9A-Fa-f]+");
		Matcher hexaMatcher = hexaPattern.matcher(stringTocheck);
		return hexaMatcher.matches();
	}

	/**
	 * Indicates whether the given string is representing a number in binary
	 * mode or not (ie all the characters are 0 and 1)
	 * 
	 * @param stringToCheck
	 *            the string to check
	 * @return true if the string is a binary number, false if not
	 */
	public static boolean checkIfBinary(String stringToCheck) {
		if (null == stringToCheck) {
			return false;
		}
		Pattern binPattern = Pattern.compile("[01]+");
		Matcher binMatcher = binPattern.matcher(stringToCheck);
		return binMatcher.matches();
	}

	/** return the given String, by in the inverse order of character */
	public static String reverseString(String str) {
		if (str == null) {
			return "";
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * Convert Date to format: yyyy-MM-dd'T'HH:mm:ssZ with Z = -xx:xx
	 * 
	 * @param date
	 *            the date to format
	 * @param backSlash0
	 *            , if true put "\0" at the end
	 * @return date formated
	 */
	public static String formatDateAsDoremiDate(Date date, boolean backSlash0) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		String myDate = sdf.format(date);
		String beginDate = myDate.substring(0, 22);
		String endDate = myDate.substring(22);
		return backSlash0 ? beginDate + ":" + endDate + "\0" : beginDate + ":" + endDate;
	}

	/**
	 * Convert Date in long to format: yyyy-MM-dd'T'HH:mm:ssZ with Z = -xx:xx
	 * 
	 * @param dateTime
	 *            the date in long to format
	 * @param backSlash0
	 *            , if true put "\0" at the end
	 * @return date formated
	 */
	public static String formatDateAsDoremiDate(Long dateTime, boolean backSlash0) {
		Date date = new Date(dateTime);
		return formatDateAsDoremiDate(date, backSlash0);
	}

	/**
	 * Convert Doremi String Date format to Date
	 * 
	 * @param doremiDate
	 *            the String yyyy-MM-dd'T'HH:mm:ssZ with Z = -xx:xx
	 * @param backSlash0
	 *            if true: escape "\0"
	 * @return Date parsed
	 * @throws ParseException
	 */
	public static Date parseDateFromDoremiDate(String doremiDate, boolean backSlash0)
	        throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		String beginDate = doremiDate.substring(0, 22);
		String endDate = backSlash0 ? doremiDate.substring(23, doremiDate.length() - 2)
		        : doremiDate.substring(23);
		return sdf.parse(beginDate + endDate);
	}

	/**
	 * Convert a hexa encoded {@link String} into a decimal encoded
	 * {@link String}
	 * 
	 * @return the decimal version of s
	 */
	public static String hexToString(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i = i + 2) {
			String toConvert = s.substring(i, i + 2);
			int parsedInt = Integer.parseInt(toConvert, 16);
			sb.append((char) parsedInt);
		}
		return sb.toString();
	}

	/**
	 * Convert a decimal encoded {@link String} into a hexa encoded
	 * {@link String}
	 * 
	 * @return the hexa version of s
	 */
	public static String stringToHex(String str) {
		return stringToHex(str, null);
	}

	/**
	 * Converts a string into the hexadecimal codes of each character composing
	 * the string.
	 * 
	 * @param stringToConvert
	 *            the string to convert
	 * @param separator
	 *            the separator to include between each hexadecimal code (can be
	 *            set to null if no separator is needed)
	 * @return the hexadecimal codes of the characters
	 */
	public static String stringToHex(String stringToConvert, String separator) {
		char[] chars = stringToConvert.toCharArray();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			if (separator != null && i != 0) {
				result.append(separator);
			}
			char toConvert = chars[i];
			result.append(Integer.toHexString(toConvert));
		}
		return result.toString();
	}

	public static byte[] hexStringToByteArray(String s) {
		// if we do not have an even length, we add a zero to correct that
		// that way, we can divide it into a number of 0xYY bytes
		if (s.length() % 2 == 1) {
			s = "0" + s;
		}
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(
			        s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * Convert a String into the corresponding hexa byte arrays. each character
	 * of the {@link String} will be converted in hexa, then {@link Byte}
	 * parseByte() is called.
	 * 
	 * @return a byte[] or lenght str.lenght , each character is in hexadecimal
	 */
	public static byte[] stringToHexByteArray(String str) {
		return hexStringToByteArray(stringToHex(str));
	}

	/**
	 * merge two or more byte arrays, the first param will be the beginning of
	 * the merged array, then the second... skip the array if null
	 */
	// TODO j'arrive pas a le rentre generiqueuh, ça serait bien que ça merge
	// autre chose que des byte !
	public static byte[] merge(byte[]... parms) {
		int sizeOfMerged = 0;
		for (byte[] array : parms) {
			if (array != null) {
				sizeOfMerged += array.length;
			}
		}

		byte[] mergedArrayToReturn = new byte[sizeOfMerged];
		int j = 0;
		for (byte[] array : parms) {
			if (array != null) {
				for (byte s : array) {
					mergedArrayToReturn[j++] = s;
				}
			}
		}
		return mergedArrayToReturn;
	}

	public static <T> T[] merge(T[]... params) {
		if (params != null) {
			int indexOfFirstNonNull = -1;
			List<T> mergedList = new ArrayList<T>();
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null) {
					mergedList.addAll(Arrays.asList(params[i]));
					if (indexOfFirstNonNull == -1) {
						indexOfFirstNonNull = i;
					}
				}
			}
			if (indexOfFirstNonNull != -1) {
				return mergedList.toArray(Arrays.copyOf(params[indexOfFirstNonNull], 0));
			}
		}
		return null;
	}

	/**
	 * Converts an array of bytes into a decimal value
	 * 
	 * @param b
	 *            the array of bytes to convert
	 * @return the decimal value converted
	 */
	public static int bytesToInt(byte[] b) {
		int val = 0;
		for (int i = b.length - 1, j = 0; i >= 0; i--, j++) {
			val += (b[i] & 0xff) << (8 * j);
		}
		return val;
	}

	/**
	 * Converts an integer into an array of bytes
	 * 
	 * @param value
	 *            the integer to convert
	 * @param arrayLength
	 *            the length of the array to get
	 * @param littleEndian
	 *            true if little endian form is needed, false if big endian form
	 *            is needed
	 * @return an array of bytes representing the given value
	 */
	public static byte[] intToBytes(int value, int arrayLength, boolean littleEndian) {
		byte[] b = new byte[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			int offset = (b.length - 1 - i) * 8;
			if (littleEndian) {
				b[arrayLength - 1 - i] = (byte) ((value >>> offset) & 0xFF);// little
																			// endian
																			// form
			} else {
				b[i] = (byte) ((value >>> offset) & 0xFF);// big endian form
			}
		}
		return b;
	}

	/**
	 * Remove accent on a String.
	 * 
	 * @param s
	 *            {@link String} a string where to remove accent.
	 * @return String without accent
	 **/
	public static String removeAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	/** Return a indented XML, return original XML if fail */
	public static String formatXml(final String originalXml) {
		try {
			Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			// Choose to put 2 indentation
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(
			        originalXml.getBytes())));
			StreamResult res = new StreamResult(new ByteArrayOutputStream());
			serializer.transform(xmlSource, res);
			return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
		} catch (Exception e) {
			logger.error("Fail to indent xml file, it's okay, we return the original");
			return originalXml;
		}
	}

}
