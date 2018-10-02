package com.cineapps.doremi.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.cineapps.mediablock.core.utils.Utils;

public class KLVDoremi extends KLV {

	protected byte[] makeLengthField(LengthEncoding lengthEncoding, int payloadLength) {
		byte[] bytes = new byte[] { (byte) 0x83, (byte) (payloadLength >> 16),
		        (byte) (payloadLength >> 8), (byte) payloadLength };

		return bytes;
	}

	public static byte[] getShortAsBytes(short i) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte[] ret = new byte[2];
		Arrays.fill(ret, (byte) 0);
		try {
			dos.writeShort(i);
			ret = Utils.cloneByteArray(bos.toByteArray());
		} catch (Exception ex) {
			return ret;
		}

		return ret;
	}

	public static byte[] getIntAsBytes(int i) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte[] ret = new byte[4];
		Arrays.fill(ret, (byte) 0);
		try {
			dos.writeInt(i);
			ret = Utils.cloneByteArray(bos.toByteArray());
		} catch (Exception ex) {
			return ret;
		}

		return ret;
	}

	public static byte[] getLongAsBytes(long i) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte[] ret = new byte[8];
		Arrays.fill(ret, (byte) 0);
		try {
			dos.writeLong(i);
			ret = Utils.cloneByteArray(bos.toByteArray());
		} catch (Exception ex) {
			return ret;
		}

		return ret;
	}

	public static int getLengthAsInt(byte[] b) {
		if (b == null) {
			return 0;
		}
		int len = 0;
		for (int i = 1; i < b.length; i++) {
			int itmp = b[i];
			if (itmp < 0) {
				itmp = 256 + itmp;
			}
			len |= (itmp << (8 * (b.length - 1 - i)));
		}

		return len;
	}

	public static int getByteAsInt(byte b) {
		byte[] bb = new byte[1];
		bb[0] = b;
		return getBytesAsInt(bb);
	}

	public static int getBytesAsInt(byte[] b) {
		if (b == null) {
			return 0;
		}
		int len = 0;
		for (int i = 0; i < b.length; i++) {
			int itmp = b[i];
			if (itmp < 0) {
				itmp = 256 + itmp;
			}
			len |= (itmp) << (8 * (b.length - 1 - i));
		}

		return len;

	}

	public static long getBytesAsLong(byte[] b) {
		if (b == null) {
			return 0;
		}
		long len = 0;
		for (int i = 0; i < b.length; i++) {
			long itmp = (long) b[i];
			if (itmp < 0L) {
				itmp = 256L + itmp;
			}
			long decal = (8 * (b.length - 1 - i));
			len |= (itmp) << decal;
		}
		return len;
	}

	public static long[] getBytesAsLongs(byte[] b) {
		return getBytesAsLongs(b, false);
	}

	/**
	 * Convert Array of bytes into array of longs
	 * 
	 * @param b
	 * @param reverse
	 *            if true, reverse the LSB->MSB or MSB->LSB
	 * @return array of longs
	 */
	public static long[] getBytesAsLongs(byte[] b, boolean reverse) {
		if (reverse) {
			b = reverse(b);
		}

		long[] longs = new long[b.length / 8];

		byte[] temp;
		for (int i = 0; i <= b.length - 8; i += 8) {
			temp = new byte[8];
			System.arraycopy(b, i, temp, 0, 8);
			longs[i / 8] = KLVDoremi.getBytesAsLong(temp);
		}
		return longs;
	}

	/**
	 * Find the index of the value element, if value not found, returns the
	 * length of the array
	 * 
	 * @param tab
	 *            the array in which we want to find the value
	 * @param value
	 *            the value to find
	 * @param deb
	 *            the start index of the array to check
	 * @param fin
	 *            the end index of the array to check
	 * @return the index of the value element, if value not found, returns the
	 *         length of the array
	 */
	private static int searchByteDichotomic(byte[] tab, int value, int deb, int fin) {
		if (deb <= fin) {
			int milieu = (deb + fin) / 2;
			if (tab[milieu] == value) {
				return searchByteDichotomic(tab, value, deb, milieu - 1);
			} else {
				return searchByteDichotomic(tab, value, milieu + 1, fin);
			}
		} else {
			return fin + 1;
		}
	}

	public static byte[] removeUselessBytes(byte[] b) {
		if (b == null || b.length == 0) {
			return new byte[0];
		}
		// Split the array keeping only the part before the 0.
		// Test before if we contain 0 in case that the array is already minimal
		if (b[b.length - 1] == 0) {
			int i = searchByteDichotomic(b, 0, 0, b.length - 1);
			byte[] data = new byte[i];
			System.arraycopy(b, 0, data, 0, i);
			return data;
		} else {
			return b;
		}
	}

	public static String getBytesAsDoremiString(byte[] b) {
		return new String(removeUselessBytes(b));
	}

	public static String getBytestoPrint(byte[] b) {
		StringBuffer sb = new StringBuffer();
		if (b == null) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			Byte bb = new Byte(b[i]);
			sb.append(String.format("%02x", bb));
			sb.append(" ");
		}
		return sb.toString();
	}

	public static byte[] getDoremiStringAsBytes(String s) {
		return getDoremiStringAsBytes(s, -1);
	}

	public static byte[] getDoremiStringAsBytes(String s, int len) {
		if ((len != -1) && (s.length() > len)) {
			return null;
		}
		byte[] b = s.getBytes();
		if (len == -1) {
			len = b.length + 1;
		}
		byte[] out = new byte[len];
		Arrays.fill(out, (byte) 0);
		for (int i = 0; i < b.length; i++) {
			out[i] = b[i];
		}
		return out;
	}

	public static String IPFromInt(int val, boolean littleEndian) {
		StringBuffer sb = new StringBuffer();
		if (littleEndian) {
			sb.append("" + (0x000000FF & val) + ".");
			sb.append("" + ((val >> 8) & 0x000000FF) + ".");
			sb.append("" + ((val >> 16) & 0x000000FF) + ".");
			sb.append("" + ((val >> 24) & 0x000000FF));
		} else {
			sb.append("" + ((val >> 24) & 0xFF000000));
			sb.append("" + ((val >> 16) & 0x00FF0000));
			sb.append("" + ((val >> 8) & 0x0000FF00));
			sb.append("" + (0x000000FF & val) + ".");
		}

		return sb.toString();
	}

	public static byte[] intFromIP(String ip, boolean littleEndian) {
		byte[] vals = new byte[4];
		StringTokenizer st = new StringTokenizer(ip, ".");
		if (st.countTokens() != vals.length) {
			return vals;
		}
		String s;
		int i = 0;
		while (st.hasMoreTokens()) {
			s = st.nextToken();
			try {
				vals[i] = (byte) Integer.parseInt(s);
				if ((int) vals[i] < 0) {
					vals[i] = (byte) (256 + (int) vals[i]);
				}
			} catch (Exception ex) {
				return vals;
			}
			i++;
		}
		if (!littleEndian) {
			return vals;
		}
		byte[] ret = new byte[vals.length];
		for (i = 0; i < vals.length; i++) {
			ret[vals.length - 1 - i] = vals[i];
		}
		return ret;
	}

	/**
	 * Reverse an array of bytes in order to get LSB->MSB or MSB->LSB
	 * 
	 * @param b
	 * @return array of bytes reversed
	 */
	public static byte[] reverse(byte[] b) {
		for (int left = 0, right = b.length - 1; left < right; left++, right--) {
			// exchange the first and last
			byte temp = b[left];
			b[left] = b[right];
			b[right] = temp;
		}
		return b;
	}
}
