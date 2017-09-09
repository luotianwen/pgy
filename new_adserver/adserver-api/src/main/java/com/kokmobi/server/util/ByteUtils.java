package com.kokmobi.server.util;

import java.io.UnsupportedEncodingException;

public class ByteUtils {

	public static final int BYTE_MODE_HH = 1;
	public static final int BYTE_MODE_LH = 2;

	private static char[] HEX_CODE = "0123456789ABCDEF".toCharArray();

	private int byteMode;
	private String charset;

	public ByteUtils(int byteMode, String charset) {
		this.byteMode = byteMode;
		this.charset = charset;
	}

	public static byte[] str2UTF8Bytes(String str) {
		return str2Bytes(str, "UTF-8");
	}

	public static byte[] str2GBKBytes(String str) {
		return str2Bytes(str, "GBK");
	}

	public static byte[] str2UTF16LE(String str) {
		return str2Bytes(str, "UTF16-LE");
	}

	public static byte[] str2UTF16BE(String str) {
		return str2Bytes(str, "UTF16-BE");
	}

	private static byte[] str2Bytes(String str, String charset) {
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public byte[] getBytes(String str) {
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.toString());
		}
	}

	public byte[] getBytes(short number) {
		byte[] bytes = new byte[2];

		for (int i = 0; i < bytes.length; i++) {
			int offset = (bytes.length - 1 - i) * 8;
			if (byteMode == ByteUtils.BYTE_MODE_LH) {
				offset = i * 8;
			}
			bytes[i] = (byte) ((number >> offset) & 0xFF);
		}

		return bytes;
	}

	public byte[] getBytes(int number) {
		byte[] bytes = new byte[4];

		for (int i = 0; i < bytes.length; i++) {
			int offset = (bytes.length - 1 - i) * 8;
			if (byteMode == ByteUtils.BYTE_MODE_LH) {
				offset = i * 8;
			}
			bytes[i] = (byte) ((number >> offset) & 0xFF);
		}

		return bytes;
	}

	public byte[] getBytes(long number) {
		byte[] bytes = new byte[8];

		for (int i = 0; i < bytes.length; i++) {
			int offset = (bytes.length - 1 - i) * 8;
			if (byteMode == ByteUtils.BYTE_MODE_LH) {
				offset = i * 8;
			}
			bytes[i] = (byte) ((number >> offset) & 0xFF);
		}

		return bytes;
	}

	public short toShort(byte[] data) {
		return toShort(data, 0, data.length);
	}

	public short toShort(byte[] data, int start, int length) {
		short value = 0;
		int vaildLength = length > 2 ? 2 : length;

		for (int i = 0; i < vaildLength; i++) {
			int shift = (vaildLength - 1 - i) * 8;
			if (byteMode == ByteUtils.BYTE_MODE_LH) {
				shift = i * 8;
			}
			value += (data[i + start] & 0x000000FF) << shift;
		}
		return value;
	}

	public int toInteger(byte[] data) {
		return toInteger(data, 0, data.length);
	}

	public int toInteger(byte[] data, int start, int length) {
		int value = 0;
		int vaildLength = length > 4 ? 4 : length;

		for (int i = 0; i < vaildLength; i++) {
			int shift = (vaildLength - 1 - i) * 8;
			if (byteMode == ByteUtils.BYTE_MODE_LH) {
				shift = i * 8;
			}
			value += (data[i + start] & 0x000000FF) << shift;
		}
		return value;
	}

	public long toLong(byte[] data) {
		return toLong(data, 0, data.length);
	}

	public long toLong(byte[] data, int start, int length) {
		long value = 0L;
		int vaildLength = length > 8 ? 8 : length;

		for (int i = 0; i < vaildLength; i++) {
			int shift = (vaildLength - 1 - i) * 8;
			if (byteMode == ByteUtils.BYTE_MODE_LH) {
				shift = i * 8;
			}
			value += (long) (data[i + start] & 0x000000FF) << shift;
		}
		return value;
	}

	public byte[] reverse(byte[] data) {
		byte[] result = new byte[data.length];

		for (int i = 0; i < data.length; i++) {
			result[i] = data[data.length - 1 - i];
		}

		return result;
	}

	public static String toHexString(byte b) {
		char[] cs = new char[2];
		cs[0] = HEX_CODE[b >>> 4 & 0x0F];
		cs[1] = HEX_CODE[b & 0x0F];
		return new String(cs);
	}

	public static String toHexString(byte[] data) {
		return toHexString(data, 0, data.length);
	}

	public static String toHexString(byte[] data, int offset, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(toHexString(data[i + offset]));
		}
		return sb.toString();
	}

	public static byte[] hexSring2bytes(String str) {
		if (str.length() % 2 == 1) {
			str += 'F';
		}

		byte[] ret = new byte[str.length() / 2];

		for (int i = 0; i < ret.length; i++) {
			String bs = str.substring(2 * i, 2 * i + 2);
			ret[i] = (byte) Integer.parseInt(bs, 16);
		}

		return ret;
	}

	public static void main(String[] args) {
		String s = "E8C8C44901830378";
		byte[] lb = ByteUtils.hexSring2bytes(s);
		ByteUtils bu = new ByteUtils(1, "gb2312");
		long l = bu.toLong(lb);
		System.out.println(l);
		System.out.println(new String(lb));

	}
}
