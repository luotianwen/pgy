package com.kokmobi.server.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DesUtils {
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private final static String encryptKey = "kokddlio";
	private static IvParameterSpec zeroIv = new IvParameterSpec(iv);
	private static SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
	
 	public static boolean flag=true;	//true为需要加解密

	public static String encryptDES(String encryptString) {
		if(!flag)
			return encryptString;
		byte[] encryptedData;
		String s = "";
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			encryptedData = cipher.doFinal(encryptString.getBytes(encoding));
			s = encode(encryptedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	public static String decryptDES(String decryptString) throws Exception {
		if(!flag)
			return decryptString;
		byte[] byteMi = decode(decryptString.replaceAll("\\*", "\\+"));
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData, encoding);
	}

	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();

	/**
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encode(byte[] data) throws Exception {
		int start = 0;
		int len = data.length;
		StringBuffer buf = new StringBuffer(data.length * 3 / 2);

		int end = len - 3;
		int i = start;
		int n = 0;

		while (i <= end) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 0x0ff) << 8)
					| (((int) data[i + 2]) & 0x0ff);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);

			i += 3;

			if (n++ >= 14) {
				n = 0;
				buf.append(" ");
			}
		}

		if (i == start + len - 2) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 255) << 8);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("@");
		} else if (i == start + len - 1) {
			int d = (((int) data[i]) & 0x0ff) << 16;

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("@@");
		}

		return buf.toString().replaceAll("\\+", "*");
	}

	private static int decode(char c) {
		if (c >= 'A' && c <= 'Z')
			return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z')
			return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9')
			return ((int) c) - 48 + 26 + 26;
		else
			switch (c) {
			case '+':
				return 62;
			case '/':
				return 63;
			case '@':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
			}
	}

	/**
	 * Decodes the given Base64 encoded String to a new byte array. The byte
	 * array holding the decoded data is returned.
	 */

	public static byte[] decode(String s) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(s, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		try {
			bos.close();
			bos = null;
		} catch (IOException ex) {
			System.err.println("Error while decoding BASE64: " + ex.toString());
		}
		return decodedBytes;
	}

	private static void decode(String s, OutputStream os) throws IOException {
		int i = 0;

		int len = s.length();

		while (true) {
			while (i < len && s.charAt(i) <= ' ')
				i++;

			if (i == len)
				break;

			int tri = (decode(s.charAt(i)) << 18)
					+ (decode(s.charAt(i + 1)) << 12)
					+ (decode(s.charAt(i + 2)) << 6)
					+ (decode(s.charAt(i + 3)));

			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '@')
				break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '@')
				break;
			os.write(tri & 255);

			i += 4;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "hak6Vc2QVc9xt*VmXVV65A6aKShgnwERQPFman5Yein3CCBWH/tkIMifAR*y 8i1Fa7mFjU8wXyebX*Obr9TUGgk5Ag1Hmj/U9iDm7t8DmBpnf9dpWWjDgELP QbVQy*iW94XLlRR1kGFHnG9qu4zteVhZDx*UqBKy4C5URFs/BYBfe8heD*Tl Gt1CWw6i563JiCGqRLj0o2B7n6/NWAD8crASAd/xmG2lwJkv9gomsA6eZB1w pDUHbnEqtOUqJNIFNF6hxxAaWbJg9e12AgkazahM95iNivUgWNX90JfT37aN dqXMhOcGwX6Css6W6agyaj82VVmAyFWQPNpROzEk8IzC6WEW9s9VhUYD739N uXHX8DfThPeptaCn2ixLq08rJCVdB2rirMPNpaKjNml/Atf4wBwXIy4EqxFF ZP4TUHJWwF2obIKeg/ChlDf02PCR3epfwUIDaKoWhc6A3rIszn7HfKPQtQUU 1oE0KbGgrZH37YEBQ/jjjw2MHCv0ZPzBW1O3Irr9vprf9uERiMHNjrpkovWR 6BBXR/whNqz0pgIIWgfzF2VQvBCGJ0/ZCvMZ943Wc7BmzzA*uRuvdOg2bv*8 gZ1fGEbd9ztql2Jx//6T/fz2PsWtSiEszPyy2cjcB*69o7A70buKQLnlFLZU 9MMVP5eRw8IkPNJhh/9l05Vr4k8VeCpNspYJK3a*bRwyjCXXl1qPbkxIdqfk MZ2R0nfTzHjAZyfgBpTFxduvV5HGR5cZYnim/efF8qyqwZd3eH69XSnRL45R mSTk*aRmbGcxsmH7tWealRfCRBbEMw818U24f*DpN*FNHCFj4aj0mZMNnrGK vL6jBY5foqKtNUmwkbXdb/iGyyOAeOb/hH4zPelYuMyGWSvFcAOtAGyTPbDU oKHB6mNon9X5LW2fluG2dwlLuSfYDRWs*HuVZJpxapDTjhiSBqirwewbLX0y U1CfQsamGAxI2KjQZagvTgSjoQKVV1n78C*jySs8Y7ovZzWvYWWcf7Cp7xrs IBSL2xXNWPgewkaMsykz*ztP7MtLhQGUsmcX1D0/KNBwXqsHM3rCFnurp7jW dgGSqSk59eobIwvC6dEkwfjG4YpmDyTtA48Pg1sa7D4fkrpg4P*aRzUozUTU r6rhXHRYRxClEeWmDuF2MtqaUS/GHW1*wNI8gLuHn/jO5e9XTvQRa6APJk7n bihzOXvUESSCNzn9jxUNwhzQQ5KVjEvUK/rq4x29WkAwVW0VqFY/abksLzjL GHlQD8HKCPHU5Wm2abLL6K*gQwQw4fPWfdLX0PIPZgjWvSsHRLcyZ*6ET7hF o6svXAtN72Z/CdPerEDSIEkCFlaHkZo*9lW404kiq4q7E*BTtJrmRxbnOnu/ tb6fjqwr3Hki2pJsO1FJzXfnGNV2nnaERiT1TS6e9LLFRdfFumaQo6EGZHhX 3rAJwEvHYGTp2wCzQBWgGkOdiH5wHwMDzX0wUDfRZmAzNfVIZK1/Gzbn5oDo G5HcR*XR2GPLn5Qz13ZURLI7DEPjoZdXq810fMqV0yAcU2VPvI3Q4WGBTOWT /P5Xm*pET3RSLInyYi9QtMXSitZYQt7jRjszeywQ7mGYacXyPhfGPyGgWiT2 /V9AisRzItrbDDvYk/BuYvJx0bTbqNimrvxx6QuVP18M3za2qr75dklmholo yrPeeOdAVHfsbML1fP7GMwjh3q810E/vXRwuOD/8CSb9*3jqQDyKMZawZ9UN m1za5FuGS*sOX8nEXWy5O/qPaT8bQIMR*FhbV/HZ65owKMACE1plYiMGfdOL 3c5p4fdr0XLUogSC/9tN0OWyyQyJWesVZvbMtCrMijGZC/hu3H4PJpWuFGRS uG/gvvQ4uyjRJ8eO7ufcc3meNna3Y2x/Nr/edYDKv8dVxegM3ncD23bJnnkK gXVYySZU3fRXbwHYiUoA*AtyFuyZFPh9tHMJUbTXgS0J1UrddSBImzz6RIvl bwZHIjwyDRxxx9ggOp8862pwWHPRoqTqZLX6hrx0atrfR0ARZcMSjbCgKW64 u3S8kGUPuAOT1aL9kK/KjcDDiXqemTdaEDKp4E5j9cJSFRtggI/qJFWXiDem FJ200hn7lpvl4k9*yb09ZId2vlgrWLCXawLsryMZQMpLm1pIswJ*T/hDjtaM tknVHJiq3ii8xazOdO4k8LTOP0X17IUdDHO14nMByi8OJB3hmRvG/X/Gy*Xi o7WE9tYBdupEMXCDYhq//NmJtztDRfd/PrDKRJ5mmhfVoHbLLYhhoT17rhNH ZYV1xYi7kVvArWIWaRaosee*wJr*Y0i0dTtwnXWAsrfpUF08dYwklN6OCcnG AwoQvbiTvVdD3fkeqL2x2JWdOIXF8cBaVjHwWdIZscp4uoxxGIX//4oN6lhy 7r/VuTEXLZJOZ*oWBxqHxjJ*JxbhBUDVouyXZwGWfb6OW2G79AeD*6bnHKrT c9nvQF7sIqwmgZI9ulbehvkpxTlxX02A6CdI2kcSFt5VAegUB1i8EmgL9eSj g3s73Aq7BlMiUG7uhrKP6zqXmVHI1f/Am4fZraVHpQJI*s1TAzQin0r0oMkn 7cW9WGK1FRP/US6XmZ9DwOvZWlFSgWj/zyKltgfaxtlX/YO*RQt6p/Z4018a MBxiaLU1Pvqcl0KBPY8rwQTATEhV0hxM2POu2FiseAXFn8E31Hh5HJY0Gfc0 F2X4dNhExq0byVv*IvVnm7pklv4XTVEIL*1YXFaH6mKrtM3ODNkyJqzmMy5a B4FQhCXEDv7mqYBTfmh0j56ibiziGPowmqY8qSH6a853OcxV3heT2qvR17wQ eySjlWZ/Y9*AfWBKTOP1xW7k0hQIThGRrrcajYc4eY5JHpOhivtmcx7evzrS n7v1QX9rsYlNgjd9tcuH3PNfTeQfxZEQ8qoo2wB0Dib2w3R9YKfRSso2CKAn Jp5R/OPQDNaAfp7l2x*bCxAORF*6rvWIqayHnnyVzmCeaIWDbMJSV9bQAbHJ 7bNdkf/QgMl*LEvfYMAybRi8A4idCEBl6BcWwkT/8YUsKAedYV*8mWEGVseG SrQySbN7XdOauNLD8mMJ*EqjfoEnfLALmGzkexzIJGErn3reRDsp1oPZhgvl *PYKn8jhNXsT4hHD*AAMoZ72p91ZGNROrIIXWpxhzzatsEjPCQnfLWoIuBHI xoFclPn8LIhYJCCNJ0XIG5G6sSHXj3k3Y79iCTkhsZ4/VO*iihmMcZZqbDsh R21RJfzw00xFWLwBGqyXSP61Nhhh2i5FSYY3iyVcWxq/DQrrIbIfOCELa2Zt x5OQz7oxz7mtMPp3yO6URtqmO1f2xvXkRVp1kd6mCoaMa3fCStfr7SAAupon 6kHlWG*DrpF7G2Hv3AqQ1rbHu7WSSJAI6CmQuNGwxcmiTy4PmZ7471smw1au gL*niSvjmf9E2J/Vxa0Lt7bZjObK7W2*28wSVJnYnAQXObrAz7nsgDwvhlws HsYi6WkcdxJDSUYsDJrh9etqmmtWoCYaina2oiAePpSAqDJSJVI4tblHoGva 7dHbFA0hrbupEkH/*R8iSVRnWUPyiUwItzaNjYvIoDmpewpbr1oecFz/FGGA v/GBC0i19q5XXxKvHNrXCcwkoeUPytBuYa9AqtgImTQeG5jQDJ*Th5BlYjYQ hfelPKpOdlWMfeBLXMi0da1jwFOJjkk2oUyZfPDswlY1UgStL0Okh4Ix5ziF BdP3i6rQDhMwT8MJLmE4Fp6x0ctXxaJsSxx7RasPV0MU9NvLoLphEGBMLt4r 4XZt0iQgwNKG562udgmP7*FIq5Xaw/MJcWdnGgorXf*3/MZLz0HnwSVlZgA9 FaXdXBeWOu2wIEe*is14AYYoYjowITiukRfDemDoXSfrphtHB6Llb2kD/ThS l8xbRIZdUTMej/nqBEcHR8IKIf4P9zEDzPmOSrduGU1Nnok8craxkxurqVGr pAhxNI/dCPo1taeu8Y9GDF9J73bCNT5DsUS5*7fNtZGJS*VxWAhHfjmhsr9J 177telfe6dtGj4VwrNcjai/hoVl28F4EMUPX2d0/fYFB0vMSiCWWvceSU/JG mSXmxVWUKNT5cN9nen*nW4LDZH7vl6xHG05Ay2rmO9NNZFJRf6xaQBYSkxT9 G3TK1WKAqrM37pUJLZnliH9rT7oThbXXcsiw9xaJmKdJtZox8RcIs1w2*QrH ftGQti0ego65sZLqDaUkV8JOdWBBmSgpirbFwzZXFDxEFARIDk88ESDBs4iA /tiJykB61tDOwJAHA0VJmsL6NyrV5NZeDqJpP4ujQ4Mqv7l5bW/yHQruvzEr g2v7KIYNYYbcqGxmvzRsaZgT6j3gCZLE*DYS7r7MZV18IwYZS7Crc3bSTQZZ tCd9ZjV899cpwHE6qS8aSksHa3s/PLsGwb4T6dDRfvhxBr6QEHdbVnBEEAXr GjvoBbykoINMP*VNGtwoRCNH4el*q7/2frvFb3dREejzENQN8sykB7igtGQl CcGNOgIuq3NhLgYMG3LquvV74l/IeC3EL6WWGGl3geFTr0ZcYRf5GPDMn6je q38xbaJZjEXJYw87FQCXrAeaqtwu1aQBzCjs1JmI7hjmeJJjO9u1d8fxGRTT 0KbTkiJyb/NjCxmoaQRjFUmQYsJyIZUUiRctPBtNfKMaMrZSs0NEVelg6au7 CMpjjwi5nv/yzFha*2*3Wf7/8/qaDINCmhM5A3XpuNVulQl0ADaJvnbbqJAp *rlAn13iLf*qLAH9AOieQ67RwA2sVVItyNaa3XaHEoBTmuD6b28VxpQuhe1V c729EIf4ohdaF7RkRtg1rE1GhyEdcOQkRbegv5SBUHDslGDITHOlPdOlkejv 5NtSTBMevXpmTd8JgOXFv2UTed5wfPcxyP9m7B/KJj05aLJ8TG5NU*iPO0XU t/Ly4gMxMTDMkxAHif4y8LYB6FmwQ8xoAkZcqYAdf04POlJcXOBhQfdIhHAL JYOcMQVJ*xIx1pKzIAOUyxxarl63FTexQdTpHGYKqnASrDImy8hTVpurT48x uysKxFU7tS6pcJJCwpOi0PjZsfPhhqypfC4nOLR2P53pfngyOMkQ8PPg0jOq ybQJwis3ociV6j00NnOSbxff8zNDbpeDcPQTXhC45inhl35m8nROcEJxnGaD ba5V7w/rVub2TN4lG2wmVEWnuR9h*NqCro1Ql/UieSrks2nKaZGL9IuEDU98 MK2vQvt0SymvyjKOW9DFvaOE9fFhUE*klv0fy/RSKMmw0RFAz*/GcoiqJjnq nqzzCZlIrnz9AaC//QVclfD9pCn*obbrqOWaRVcD6759sgZ28wNtDkwbqjdQ *dcNCp5a2IPwAijsmA0XVF9j5OzyyyBvg2c4mEyIZu2r7bL49OBUZ9a4UPB/ Aj9rKkVMi0fNTarqR43oeMInuPdfl*F1fSzNl7PPKI5shHNVeSx4VOAm7JXX 0li3DCS0MrePtKdLVHJ7NMRKqDos55pb5n8ElOG67xQc3KkSNRgOr7bVJMiL 2EoO6vLRf9dWSapsWmIc7TJRAc0tMsA0a5GEMEgudJYcJXYe/ikz6S9n*Jr/ GgddHd3AbJ5*GAuPlJPhC3INHHQ2Pd2rA*uWo3t1qEJGcOBeZC54ahSiQ5Jg s/svqxO1tLHZkShUlMPZy/wAoxl358lUxpchEi7uuCFK/HaDs6W40ZweZuAx DQlySu14eCwkM6z7tSTPs6ePB8vwz/qYGFfBND0Yg14toA2NdSSn1wJWjdWn N*I4Ybo81dgKcK/h1aaIm6k2vDlm1csGbxLjm9GSvU*QnqvQKvKra9VeyObJ MWWM4*2nIbLcv01dEOTh4e9r1xz5YsbnIiLcLlXdIVRuuiSLmI/8Kt2HB*py dghec*IKWK6GR0osueoYPi4vJSQEglMBOsR/gPVEPXxEafgIwlNqg7otbala lx2*zgk99mRLJadXIxF9kqpNOaT9F6ImZuERmVdDarJR6lUfnKO2V1puHaFe HtvhE*paRL5nZYgUebxecKvJj2ylfYsjtE0xAi7Whb9WBs2t*JktqPuuWgyy MSG2cekRGdsFDmgLi38z8NKGpDvzXAu/2X9BXUgBYg4Ghsl5CnigfaaAxAb4 w00xWpz15IQkbva7pcGe/Ugux9Cwt/pVB8qoslfL2e70BloWINFppNzL/Qrw UIGxXjtjawCfgZu3YTuPICPFZKAgerGAPnYbS9vafqYlTUqi6bU7qFxM7LMw bhLDVYLmz6ymNnggbcORogGoKI/xDzvLov7qL*5BfkJFTcbC6CB7Qq/X29Fi TmcsDC1kT4njsbXWd1gfb4Kn4jtJyTHw7saeVWP4lzVYdPsdnr6r2qE9UTrP kSJrzCk8zqgFh4CW11YKEKitVnX9ZKum3e5dievWGvtRNRUTGZUrXpLHFWN0 ugnB4vBKw6fTR3MThfuoHF2LfouMMnr1s8*GW6sp*ChvImY6xZ6lCZZgSr1h /jW3J87KPeu0iVU4MoZf*hHXgGz1A/Iv9kcWIiQA6p6HDlZE1Kozd/DAjQFT YlO7opur1rCpjiNmnh9enmCyRXBRhjBVDWSYEx9gIJ/l1oWFVlRvNd3tmmrW K01dMkcOOVh26sGpcGPNPDGKOMLA6onXzg*EzOHnD8jjAFdK6Xv8tsS09QXT 8CpMYmblCcvKhNIYv6ybw21p/oGXhN0enj3hgEmui4v70MILDeTc02jJ/rSp VBiWAeRnqIMn86VcB5C1amN7ux43gLdImI3qj4fDjcm9y4bw677ETu9MGHOp zt8YTND8mqEA1fhwnmtuN6txnPKgIlc0hkpiwdqqN8bjtT620yyBWZGhLaWM AGuYB*Q91FwraUlL9FwTvcrH5dN9QOM8szal*vVCTzTzZ7h54/yCbMME2LWI TL3Kk9w2b*NhLO05WyaTFId8R6juZHS70D32EG1L2hRbdHAOjlUga7ApYRR/ hcDU*QNdZ0O*uW*fduDbmlXBODxWSyQMyWKwIL3NYAVLl3/bt6qks/7PyQdv S6DXNMl1gVQNQIteTlZ5sKwJyOT2Kp1K9AdjXBpIy6f6sYyM6iq4LAFOoiWc cxws01*lQjv3C5B*fgXPucLjKd4CmQAG7IlSvstNp*HELE44eog8GR9M1Jm* U2NQ*a4LWKfb2f4EBYn1DrPPZJIeSc6BjhVISodL1hGrmtE5WT6jueWFAAHj mY4h9jB2jhI4W5Z6u4TsrCG5eiFQ2uB3nSPoN1BEmBU7UyWHODt/0PXxOeUs REHqd/GIvrjEYZNdYMCEK4YHmqhwaRj31QEwQKfA*7GYs7OP90501CXtPpF7 rcOwqddtYLIbQ9NQY4QwNAIsZSz8tgGjTnUqQbZqoigFXUmByz50uSINX7bm /XuNZRzGnJsJfq479dHcdbfitLZufbIlnl0LNnfk*k7o7ifAd*ZPin7rdpgb bcmcqqXUrcUqiVQptgCw3PXsKF7njzrNhXdKbGfDk*keHBsUpVnwlic3Xox6 gqr8pT0JIb1fg2uQrwwHnKAwk2t/WmCV0hEj3LJSk14ggeP/aO1jwuQtgV9c BSOKgZWDH00kea2PH38i8WUzXTz7b7RFq4Dl1c/cR0LJF6M0AH0yDpmkWGp4 Q4AhKuOCbbqQuJqIFJ1lvJlxX6niweT0qnIZJqx44/0wkn*RyfJOVIw5Tdnl bCx3oNFIQQ4hpJbg*wUuemzZt28eb0L7AEDZVDaihWdSVkWM9So9I/UTKb8d sf33iS7Kw0ej5F5M0fAO0d5FV7zntWQfIgekwTPFki0iTP15sB*Mzoto5X5V *dFnnL5nhRELEZL8BSRnEtxuuyDZA70MX/Rnq/KQ1HMDbgiDzSGZyOVnJBFF drjrivOFugsPUieMVtCKxB/qzRmz0tu9RMdvVBZE8iWeS/oytljPXB1frS7v lP9m0lVwueud0c*yzRTGYV3ePTdLGFQLpsMIHPSBPBRvWPgWhXD9ZrrE2sdW AYCrX2jMc4Q6kADTkoKsDHv22nZArAg9MLensVyAcaHpAezWdmZ6h/MrgJSB REjlpLXOrTGI4l*clZ02eJ58x1wc*HoeQItQrgVoRvWVjVZ7aK4iWl/GrCcz erVktOMsh3z20atFjclDIaUcbhlMbXDRixBch2IkkQxVyajOFgFtnUOa1DMd K/I*XIRKyWIdgWHhmLNkHcOgX9vrZJ0kAN9DyTT*X*XOjDHpQIC5rMRozN*n mYsewgBSDGyhqYHNPUPRdW7SQJzyTV3iMCPIaAelihl3Pe550Y4BoACD*8Tk mgOcju8zRlftJZMzYMLjTLWyunOKwjnx2KbCpUhi4VEjWZ7Gv3FSsODKpSam sn/OsH1ALweDLaEYjNJMuRaKpxDdvyThcZJIwC/yHHmFSddTOWqw7mfk1Iv4 nWr6lKuapM5M4VtBuXPbNChPMI9/5cIdBWvWqvSZtMnl5yK6rTlUfdSv82gN btlH76sHdo6GE2F3zJEIcScye0hfFNaxZkMb4k404TWBpfv5EDo7XhkY2MHx yhtJ/wHSoUIuTtGfQCqIpH9julBhMvc/*iByOAQT853/ZyBAVAC3FUGAZUeV dkyW5AXjaq8pKIXwuw3AcKQp1PWYFFIgFpXo7NU3nzDClH*DLCF4uvECSYlK xmPEQMuGOPhoVuxFTVmdhmCN/v3vyNlamQuKLkPxeIwUxE3mXcfvm3KSn/Q3 gd8P3w9axds9nFhzTPP53davRIb14yAf7tqaS3phy6D1VHYPUmciXUDLJVhh Fsj2KzMZY6GUkJ5QJbTtYzQFXbHMrxksP5CERdY4Ta7lYKMP6bX4rGOaLK9/ PglMaMdeHc57fB2DXSZAM753M0ZM65Ijo1V3CR5uWORd7dwVGfgtLcE89oP/ *irUGEqgeGTVYi8u9j7gymEpwk7fVL/YwWtwXe/2VCeSeTmEvMzTrsqi3Jqg YHEdY8tOddqc9Tu2918SsyB9uKgEaepM0chPhH3gs2x7mC0Ha3VjxOQPrcHt K0MZDGeU*YsWmsEdvJAMAlgKw82WN6btsO/BsENOhTAKaMCmFRWiaZ5Ti54t 9hBQbpcOlJmCG3rRh4ErWme1r75xMEVXkNDZoBMzCz*F1RvYhdJz9wvlyyIT vQnXzbCD7BiGfb2/Nz9Wau9tuK6CceYed4VbUD5R1la6BlhSFypJ3tqU2BsR 2I5OXqBGvLaU7J5YKG1n9qsrb39GcbAqIJ1f1W5IrjKW*foPHJ2yf7GDMYgC 1MTFhMu0HDIAAmH7V4Rr4UvGrHEtOLgk3fMh1F5aUkGee1B1rM2fARroWQ6A *9yiLqgyLmk9kIpneckT1OfjYJc5j*V3yMbD9Kt2pmZEp5UScIQC6ZdTb4xB YweUsrXWuu*k4MPeZx2OYp2dncS41FXHeE1rIPSEDzgXevGuRYcCPVb/qurg q6qxaVlQyKCM204QCpkPMCs2qZhRluLmUeLpZPNOnckiHH7ekqGRU4zOm7Bg MBC1wYTXa9esdnSubB68R1WZpDz0gbh3qeoRzEfg/cF1W8BAbguc0LE9tlBM /UKiPkElD*1BO3BXkhNfw*3mlo0dn9hPRvYRGdV1MlSZyaeGyACWz38emn4x U/D2p03rSUwZyul3SxbTsvSjOYwSL8Bd2SRE75VW48vaGXmFpHFR*6MlIcLW w8szlB0fXPPfyNCg5JOcKcZTl48nJwTVHiab2sB3u*VFt2WuUbemBgEgmeLe wIfPIS*FTfCE/F4oedPHdR5H2ioj3Vx3rdF73DUfeNSblKjjL4PS2jC8WltK Leo2DVETaeuqyUMvE8XWod9NGg3a2r7In0W7GXPDSSdzuGsHUdT*9xlKYHdd cQVvLHZsu3vlLjdl5r75LIz/jd92Uh4KX*pyegEwmxME5v70yPkAQTllZF2z 59Vj5P3Mj/uSZgRFft/TB8yIYnpl3gRWzANFUHLLl*Ej98xvcfYu1FdbGDAg G62dbXJkpD8KAyIA2zXS15LrnZ0jHPxQn4EWAd/ybQQICnBNn8JXAAZdWYPV DpxV2ONvcmSi934UT8b3SF7Lq2sCCJhs1CEXgW2uTy5INcOeeryIaW/9UKrk kSNmkwjoc8iEuyVtgm0O5W2t1oOxPh5tKCCRAD4LfYCP7eBklMLLAjKPsXHS PuSzKP*1V1ElJr/Ze1zGA87y58MMv6KMPYNm/liVwJ770BJi1g*cVnL6YIQ7 87Q0/0Uj7jRTZXIYbjuOENi3gHeEXB6lARxqD9R9rfKj8Ki/TajAWvVUvu6u RQ6vPp*BFJlvmrOvSkZ/IuyiG3GsMp0OjIIA1B*/1mNCwY3U*O/AnsdboW*I YefaCkWf5XHuLc*kc*V7wXdq7uELha9WarhK0JEae/yOJO/z3dQkqmFNPaqi Vl/GRFxZdNofypl09bA8OSxOLbW7ONyTW0ngR7IHnrJ/mlu/U8ky7JS1X6qM wty8Okhq9c4yRnQobsJvyHkRu/0UySAIbMIS*fGFgt7wyPVf9JtV9Y6b6Gd8 2bJz2qMPVdni3I71cXl2cEK4Z*dbolTrN6gMgy6CDlTvS4APBjp2RGxWql// noJbR6Q82kFjiyJvoaibrdRHgPIIU*76UeByHT5xq4ohv4mwQIi*TVzeNQ1L anxYX9gE9duRop7wGeCDavoNjsSvjWbrDdtdzdl6i/4Mdqt1EunCNAHuOcTg BYo73vhV7MC7OwG1Cc9v6wd6U/oLq8L9uQhQ2L7SbO9r8ZYPyxJtwEVKIWOs fA7QL*lKwX2geFe2OIWU79z7zj3HQlqRrKiczv1DsfQLhMtTCTbO6VTp9Eh* C7tUD9r0/c4hNpW1QRavbGq51vrwCVWM22U6QTdMwRUP2o0K*Hqb9GIcMqvP ChvDohrepyI884kxkPgPwcrvRlX7ncqPi4AQaKz6OVVpRd7DtUaTYutts5zo mWTj65*fR9gHBOtdwrCQnX8trAwZfRMwzf3nWtm0d2FTi471q4MfRmz0CiQ/ E*uRna*vIO*mr5i6X7YnwDMZZURIpTqccGTQ6vK7XLHgxvQ2q4xDDT6QmU9v Hw9Nr2oHe5Vv6bKdWTAr/a5huNSsI03w62lbqfscfqqOQ9oxkkRWQYLEF6je Bqj7B77DjnvsP0MgEIInU4QwZHjhc2UXxn6RKKsAqaGsqrXHsdxJfe136mtQ pHtM6bU9K8jUG*eHEArYKJos3LYJzqG/XRNB3Fl1ajraz2OkDQ3TMwboNaTo io1K*rTvqMUG5Bfhs1Nch*FmxfGAGr0Frn7C2ZUJSCbbHh7XvzREe6ExsUpr hkwq*6lpZNtHr4PbhbzpBeDHAhplQ4xhZIcuJhlAWKwzEoD4h/5e07SN/*RL r28DACpx6LhLF6ncdy6InIK1cfTcf55tjeY8WbG9gWcWMVnzRmYxY0XQAj*O 1TsH2N7FHDMS0yMXIARCsE82as4KFhhT1112Tk8Dxss/d1wBM1nE2WPiNeZ7 3LI4n0SB8axDSzpzBZ4ukDlo7B565hXjNBu*0tmBTXjWNEErnHpsAV8J3p6s GCNYGkvD5bpn6e/mWovM5tQb0KuDh0mEeUf68*FRVcl6ASUHHeLpY5tBLwDQ Qkt123qEPnkOTq5N4NOhia7UZT6I3qSBasOzmQCUgN6V6QiH915SkmMRJYFT X6DAvoB3U4Vwvha8equGT5oGuQZaMfvrTWEFMT5GnqexLzWiZQwLwKcKUkyP NnxL8l3EfLGmJ51EgoApe25GWg1/EZpmY3TlOukyHuSJtRe9pzqsR/m3cWDW Lknny58O0W/GI2kqFNsXr4pKIbeA8KfdiGUVcbw9qH41Xy1fB*ZEv0X14ydc T8LZwUr2nOSWMCvSk0W2byFqibCetAJx3eJS6w*9dDOyiw5XRKyVaj2NZGdy 0ZE@";
		String result1 = "123";
		// String w2="nX*R3V*4Go8@".replaceAll("\\*", "\\+");
		try {
			 long begin=System.currentTimeMillis(); 
			//result1 = getInstance().encryptDES(text);
			long end=System.currentTimeMillis();
			System.out.println(end-begin);
			// result1=result1.replaceAll("\\+", "*");
			String result2 = DesUtils.decryptDES(result1);
			 end=System.currentTimeMillis();
			 System.out.println(end-begin);
			//System.out.println(result1);
			System.out.println(result2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
