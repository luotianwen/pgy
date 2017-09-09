package com.kkgame.feeop.util;

import java.io.UnsupportedEncodingException;

public class ByteUtils {
	
	public static final int BYTE_MODE_HH = 1;
	public static final int BYTE_MODE_LH = 2;
	
	private static char[] HEX_CODE = "0123456789ABCDEF".toCharArray();
	
	/**
	 * ���ִ�ת����UTF-8������ֽ����
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] str2UTF8Bytes(String str){
		return str2Bytes(str, "UTF-8");
	}
	
	/**
	 * ���ִ�ת����GBK������ֽ����
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] str2GBKBytes(String str){
		return str2Bytes(str, "GBK");
	}
	
	/**
	 * ���ִ�ת����UTF16-LE������ֽ����
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] str2UTF16LE(String str){
		return str2Bytes(str, "UTF16-LE");
	}
	
	/**
	 * ���ִ�ת����UTF16-BE������ֽ����
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] str2UTF16BE(String str){
		return str2Bytes(str, "UTF16-BE");
	}
	
	private static byte[] str2Bytes(String str, String charset){
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/**
	 * ����ת��Ϊ�ֽ�����
	 * 
	 * @param number ��Ҫת��������
	 * @param byteMode	�ֽ���
	 * @return �ֽ�����
	 */
	public static byte[] int2Bytes(int number, int byteMode){
		byte[] bytes = new byte[4];
	
		for(int i=0; i<bytes.length; i++){
			int offset = (bytes.length-1-i) * 8;
			if(byteMode==ByteUtils.BYTE_MODE_LH){
				offset = i * 8;
			}
			bytes[i] = (byte)((number>>offset) & 0xFF);
		}
		
		return bytes;
	}
	
	/**
	 * Short������ת��Ϊ�ֽ�����
	 * 
	 * @param number
	 * @param byteMode
	 * @return
	 */
	public static byte[] short2Bytes(short number, int byteMode){
		byte[] bytes = new byte[2];
		
		for(int i=0; i<bytes.length; i++){
			int offset = (bytes.length-1-i) * 8;
			if(byteMode==ByteUtils.BYTE_MODE_LH){
				offset = i * 8;
			}
			bytes[i] = (byte)((number>>offset) & 0xFF);
		}
		
		return bytes;
	}
	
	/**
	 * 
	 * @param data
	 * @param byteMode
	 * @return
	 */
	public static int bytes2Int(byte[] data, int byteMode){
		return bytes2Int(data, 0, data.length, byteMode);
	}
	
	/**
	 * 
	 * @param data
	 * @param start
	 * @param length
	 * @param byteMode
	 * @return
	 */
	public static int bytes2Int(byte[] data, int start, int length, int byteMode){
		int value = 0;
		int vaildLength = length>4?4:length;
		
		for(int i=0; i<vaildLength; i++){
			int shift = (vaildLength -1 -i) * 8;
			if(byteMode==ByteUtils.BYTE_MODE_LH){
				shift = i * 8;
			}
			value += (data[i+start] & 0x000000FF) << shift;
		}
		return value;
	}
	
	/**
	 * 
	 * @param data
	 * @param byteMode
	 * @return
	 */
	public static short bytes2Short(byte[] data, int byteMode){
		return bytes2Short(data, 0, data.length, byteMode);
	}
	
	/**
	 * 
	 * 
	 * @param data
	 * @param start
	 * @param length
	 * @param byteMode
	 * @return
	 */
	public static short bytes2Short(byte[] data, int start, int length, int byteMode){
		short value = 0;
		int vaildLength = length>2?2:length;
		
		for(int i=0; i<vaildLength; i++){
			int shift = (vaildLength -1 -i) * 8;
			if(byteMode==ByteUtils.BYTE_MODE_LH){
				shift = i * 8;
			}
			value += (data[i+start] & 0x000000FF) << shift;
		}
		return value;
	}
	
	/**
	 * �ֽ�������λ��ת
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] reverse(byte[] data){
		byte[] result = new byte[data.length];
		
		for(int i=0; i<data.length; i++){
			result[i] = data[data.length - 1 - i];
		}
		
		return result;
	}
	
	/**
	 * ��һ���ֽ�ת����ʮ������ַ�
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2HexString(byte b){
		char[] cs = new char[2];
		cs[0] = HEX_CODE[b>>>4 & 0x0F];
		cs[1] = HEX_CODE[b & 0x0F];
		return new String(cs);
	}
	
	/**
	 * ��һ���ֽ�����ת����ʮ������ַ�
	 * 
	 * @param data
	 * @return
	 */
	public static String bytes2HexString(byte[] data){
		return bytes2HexString(data, 0, data.length);
	}
	
	/**
	 * ��һ���ֽ�����ָ������ת����ʮ������ַ�
	 * 
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	public static String bytes2HexString(byte[] data, int offset, int length){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<length; i++){
			sb.append(byte2HexString(data[i+offset]));
		}
		return sb.toString();
	}
	
	/**
	 * ��ʮ������ַ�ת�����ֽ�����
	 * 
	 * @param str
	 * @return
	 */
    public static byte[] hexSring2bytes(String str){
    	if(str.length()%2==1){
    		str += 'F';
    	}
    	
    	byte[] ret = new byte[str.length()/2];
    	
    	for(int i=0; i<ret.length; i++){
    		String bs = str.substring(2*i, 2*i+2);
    		ret[i] = (byte)Integer.parseInt(bs, 16);
    	}
    	
    	return ret;
    }
	
    public static String versionIntToString2(int version){

		int hightOrder = (version & 0XE000)>>13;
		int middleOrder = (version & 0X1F00)>>8;
		int lowerOrder = (version & 0XFF);
		
		return hightOrder + "." + middleOrder + "." + lowerOrder;
	}

    
	public static void main(String[] args){
		System.out.println(byte2HexString((byte)-12));
	}
}
