package com.kkgame.feeop.util;

public class CRC {
	
	private static final int CHAR_BIT = 8;

	//private static final int CRCPOLY1 = 0x04C11DB7;
	private static final int CRCPOLY2 = 0xEDB88320;
	
	public static int getCrc(byte[] data, int start, int len){
		int crc = 0xFFFFFFFF; // initial contents of LFBSR 
		
		for(int i=0; i<len; i++){
			int temp = (crc ^ data[start+i]) & 0xff; 

			// read 8 bits one at a time 
			for (int j = 0; j < CHAR_BIT; j++) { 
				if ((temp & 1) == 1)
					temp = (temp >>> 1) ^ CRCPOLY2;
				else 
					temp = (temp >>> 1); 
			} 
			
			crc = (crc >>>CHAR_BIT) ^ temp; 
		}
		// flip bits 
		return crc ^ 0xffffffff; 
	}

	public static int getCrc(byte[] data) {

		return getCrc(data, 0, data.length);
	}
	
}
