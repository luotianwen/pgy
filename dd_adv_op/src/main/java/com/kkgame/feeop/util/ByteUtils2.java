package com.kkgame.feeop.util;



import java.io.UnsupportedEncodingException;

public class ByteUtils2 {
	
	public static final int BYTE_MODE_HH = 1;
	public static final int BYTE_MODE_LH = 2;
	
	private static char[] HEX_CODE = "0123456789ABCDEF".toCharArray();
	
	private int byteMode;
	private String charset;
	
	public ByteUtils2(int byteMode, String charset){
		this.byteMode = byteMode;
		this.charset = charset;
	}
	
	public byte[] getBytes(String str){
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.toString());
		}
	}
	
	public byte[] getBytes(short number){
		byte[] bytes = new byte[2];
		
		for(int i=0; i<bytes.length; i++){
			int offset = (bytes.length-1-i) * 8;
			if(byteMode==ByteUtils2.BYTE_MODE_LH){
				offset = i * 8;
			}
			bytes[i] = (byte)((number>>offset) & 0xFF);
		}
		
		return bytes;
	}
	
	public byte[] getBytes(int number){
		byte[] bytes = new byte[4];
		
		for(int i=0; i<bytes.length; i++){
			int offset = (bytes.length-1-i) * 8;
			if(byteMode==ByteUtils2.BYTE_MODE_LH){
				offset = i * 8;
			}
			bytes[i] = (byte)((number>>offset) & 0xFF);
		}
		
		return bytes;
	}
	
	public byte[] getBytes(long number){
		byte[] bytes = new byte[8];
		
		for(int i=0; i<bytes.length; i++){
			int offset = (bytes.length-1-i) * 8;
			if(byteMode==ByteUtils2.BYTE_MODE_LH){
				offset = i * 8;
			}
			bytes[i] = (byte)((number>>offset) & 0xFF);
		}
		
		return bytes;
	}
	
	public short toShort(byte[] data){
		return toShort(data, 0, data.length);
	}
	
	public short toShort(byte[] data, int start, int length){
		short value = 0;
		int vaildLength = length>2?2:length;
		
		for(int i=0; i<vaildLength; i++){
			int shift = (vaildLength -1 -i) * 8;
			if(byteMode==ByteUtils2.BYTE_MODE_LH){
				shift = i * 8;
			}
			value += (data[i+start] & 0x000000FF) << shift;
		}
		return value;
	}
	
	public int toInteger(byte[] data){
		return toInteger(data, 0, data.length);
	}
	
	public int toInteger(byte[] data, int start, int length){
		int value = 0;
		int vaildLength = length>4?4:length;
		
		for(int i=0; i<vaildLength; i++){
			int shift = (vaildLength -1 -i) * 8;
			if(byteMode==ByteUtils2.BYTE_MODE_LH){
				shift = i * 8;
			}
			value += (data[i+start] & 0x000000FF) << shift;
		}
		return value;
	}
	
	public long toLong(byte[] data){
		return toLong(data, 0, data.length);
	}
	
	public long toLong(byte[] data, int start, int length){
		long value = 0;
		int vaildLength = length>8?8:length;
		
		for(int i=0; i<vaildLength; i++){
			int shift = (vaildLength -1 -i) * 8;
			if(byteMode==ByteUtils2.BYTE_MODE_LH){
				shift = i * 8;
			}
			value += (data[i+start] & 0x000000FF) << shift;
		}
		return value;
	}
	
	public byte[] reverse(byte[] data){
		byte[] result = new byte[data.length];
		
		for(int i=0; i<data.length; i++){
			result[i] = data[data.length - 1 - i];
		}
		
		return result;
	}
	
	public static String toHexString(byte b){
		char[] cs = new char[2];
		cs[0] = HEX_CODE[b>>>4 & 0x0F];
		cs[1] = HEX_CODE[b & 0x0F];
		return new String(cs);
	}
	
	public static String toHexString(byte[] data){
		return toHexString(data, 0, data.length);
	}
	
	public static String toHexString(byte[] data, int offset, int length){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<length; i++){
			sb.append(toHexString(data[i+offset]));
		}
		return sb.toString();
	}
	
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
    
    public static void main(String[] args){
    	
    	ByteUtils2 bu = new ByteUtils2(ByteUtils2.BYTE_MODE_LH, "GBK");
    	byte[] bytes = new byte[] {(byte)0x00,(byte)Integer.parseInt("1"),(byte)Integer.parseInt("0"),(byte)Integer.parseInt("3")};
    		     
    	System.out.println("version:"+bu.toInteger(bytes));
    	int version = bu.toInteger(bytes);
    	System.out.println(version);
    	System.out.println(Integer.MAX_VALUE);
    	byte[] bytes1 = bu.getBytes(50331904);
    	String ver ="";
    	for(int i=0;i<bytes1.length;i++) {
    		if(i==0) {				
			} else if(i==3) {
				ver += bytes1[i];
			} else {
				ver += bytes1[i]+".";
			}
    	}
    	System.out.println(ver);
    	
    }
}
