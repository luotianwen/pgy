package com.kkgame.feeop.util;

import java.io.UnsupportedEncodingException;

public class BytesParser {

	private byte[] data;
	private ByteUtils2 bu;
	private String charset;
	
	private int pos;
	
	public BytesParser(byte[] data, int byteMode, String charset){
		this.data =data;
		this.charset = charset;
		this.bu = new ByteUtils2(byteMode, charset);
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getPos(){
		return this.pos;
	}
	
	public void setPos(int pos){
		this.pos = pos;
	}
	
	public byte getByte(){
		return data[pos++];
	}
	
	public short getShort(){
		short ret = bu.toShort(data, pos, 2);
		pos += 2;
		
		return ret;
	}
	
	public int getInt(){
		int ret = bu.toInteger(data, pos, 4);
		pos += 4;
		
		return ret;
	}
	
	public long getLong(){
		long ret = bu.toLong(data, pos, 8);
		pos += 8;
		
		return ret;
	}
	
	public String getString(int len){
		try {
			String ret = new String(data, pos, len, charset);
			pos += len;
			
			return ret;
		} 
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.toString());
		}
	}
	
	public byte[] getBytes(int size){
		byte[] ret = new byte[size];
		System.arraycopy(this.data, pos, ret, 0, size);
		pos += size;
		
		return ret;
	}
	
}
