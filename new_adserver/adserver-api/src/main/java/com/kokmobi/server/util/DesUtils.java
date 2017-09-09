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
		String text = "jyY59buuO*uKGrgxfffkPKakN7JsflI5OyIAi8B5QDPiNa/5wesLOadzxOZG Egv35unuseJSIQaXHDoHEr5/ecZS1LPxRjJnGRBHCzEo*xYPA2dMSdCEIFI2 I5kMSWUp6nkN07QB7axuKFOGBlGzxeIgCUiyM9blcfKkfHtGRMmmZrW8EVOp Y50hlo14heKziWP0RGWZ3GLZt0YVYata**D5yNMepE7aiCqrW8SrjmChRVq4 wQMnpi9O/QEKB3vsZqqRBZN4RrGyZNZUcESfOQdMuqS8VScU7EnP*Jpb*kDa Ml6zUhNRupEQ387b8kgyCseR1mWLeTN*ZpkArimq0gXHYF1s2UgLax0S88y5 ZPGtY5kx5hKBWlYWOsTzLZxHI5dXhbcSx2X8UmhXMgZP4QinoXg8Kz75h4qw s8an6LWDI1OAQ*gq1uygygjxDLw/2eAUzAGExLxB92L4En/YoEVdA8gMTV0y NVVSI3UyUdNWOKOkSdzOc*sHVrzuwPWxf4B9Zl7LL1uh2EnMYD69gFcsIuwN yST*1CULfAEMt1AKB8J/lOJw7WiW3zr6PfUAJ1atZmGscDlLuwqJYSO*bwdG Asg2HVMCpVkJ41BSKagcAFLZdNLjA2r2is/7uXrReTGzGU8oPzTeqtwpbYzj XpnFE2O9SU*k1XLfZu2Iv6LNiUH1JSrS2Hx5wfGQPF1t1EYEDaEz08Zp9nzu eP6toRNTqFuJw95NXAijspcxZPbETIZ1wcN2nfivtMRGbixlAW*IhYNOge32 pqM00lije*k035SPdLnG8535sBDaGYouQqLA9MQc11cna3RYp1hcBPTdQbRX TfbbnxDsOuazSg8QyBHGOohx9WtgDYSzf4TpWKJryWrQiqLdKUnUP0Nk9STb nqDfzDr2MG0*gI0ZQ3ZtBxViDyL10TeujdZrLAiNuy/01E897IAnbVvtVhnu loWY3GTLlGfE8IKDf7qE9cnMy7rORwcgPLIozewznLPY*o3p17D8xmxuY6B* HxU3vxFFKdrjZH4B58eJUMg0rpJTepwg0Kdjht90G2SVsV6OF7WEfyEd/5Md rGXXNXk/JNdNjydmsisGTGwS8h7vxp3dfDvpE968JuMT4sqSDY/8mC*p671A pBmts6Pug3nJ5nZRr1WlYGvWZO1BKHPu89CIRXt5mIkLrKGh/Q2vqfHlBrSm HnPnZCHCbGkCz5JheKiiPadHUjiuMvPiLEuI5LoQwQJ75DUOuc8m7Kb2MZK8 lcCfmTcMcQ9HIjWDmnndxjpCfs1cJREPRggqoI6xJbCtMtwBR05jb1H7f7DH q3O31eZnTnwIf93BSHB9NDLWv*oj881cEtcfID4EZEhyQdFZiuXgRl4KL1*B 4NJEEHMeDXSItUB9sqSZz8F*meM2fgwPyyjE18T39AUFPoMdR20jL*EypECh t05xTcegMEQqxXHxHkzC1wx77EoKw/gMpCihmefJdF0h1MLP2UMHDF*rWB97 GY*s2EUn8BbhiPA2x7GG6/gRjngaiKDoOvM3yctA796tNGqecZuHeWU0WOBH G2A3au/iPmqU*rM*cALOVhQqTYCVTFDVJkzHOzyYl7PXYvTncFeIhltjGhkg QbkpAx*ldpNKlFsxj7bPX3UYO0CwwUqwWAbgAlnnHtOn*C5ROGCzKZr7YXOx pMFCRBtXiCUBOxF3oqbRaKrlvB7nlVz7qFsG15vyfckqwlmUZr9d5XKcpOYE BclamxRpwesIN15eMAVqBBNv7sFfQm0Y4L6kiq4ao5NW/CbZTIUxy/f1Fvch xdQB*Xsnc2RzXKRy4e*ag6jUR7xkEZDqxCPMmIbaz8IjthL65iGU8sI6/ZMT YmIUeQjm1CrI1t8pZYv7Rt6fKwheJjzdjVENtfTtVKvTuinlE*dfb3NFF5w3 HJuGHbv0eJ7qLeJ0ur1I6em*MxKTAB92Xe3pQHakU6GWB7stdRGsx5Ie/3tU cHW2gBRlr7Ryy/eZn*Qq1fsxLMRwd3FBI7T4cJveZRVAQJpQbVY1wFhdQ*0d aNAkL4ntzpGOHR7pQfobVrOooZDglzbVzteil93hxlhCSg5pGXDa7V6x9rDO GDbcLp/pYhQyGfS97Y3ERs0xf9Hi2NvXsMcPPZaOK4o5bYEtZLHmVmVM29im iyocFtIhQate6EmABu6wlEHdqpNbu0Kz8qMef1x2UuuvukMn9rpllkbLq1Cm TRPSw3pVV4j0Y18GVHq6aOnIltkF*Lf2gC6WZRpYjQ1NAjIvN6UelB23pZgi ZRUrEtL/YVQ/95pWoxGgxOcCVIgrUyobGdYvcoQVw/yaZ0svnEyUCjWAQqhJ L0UheOMfNkQtzR3Nm4sGqPMF/mFOMksRjF25toFZnuTZWp3vRgjd5V*0pjtL 5zv/KGcmT15XrKX0TgJpx87Beonqz*3Vzh7dKhlC2hhWa*hILK2Slw@@";
		String result1 = "123";
		// String w2="nX*R3V*4Go8@".replaceAll("\\*", "\\+");
		try {
			 long begin=System.currentTimeMillis(); 
			result1 = DesUtils.encryptDES(text);
			long end=System.currentTimeMillis();
			System.out.println(end-begin);
			// result1=result1.replaceAll("\\+", "*");
			String result2 = DesUtils.decryptDES(text);
			 end=System.currentTimeMillis();
			 System.out.println(end-begin);
			System.out.println("result1............"+result1);
			System.out.println(result2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
