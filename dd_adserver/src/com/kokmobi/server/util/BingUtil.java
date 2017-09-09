package com.kokmobi.server.util;

import java.util.Random;

import com.memetix.mst.translate.Translate;

public class BingUtil {

	public static String[] bing(String [] src, String to) throws Exception {
		int a=new Random().nextInt(5);
		//System.out.println(a);
		String c1="a003";
		String s1="5TTHwRqaagfqky5r8Wvgq2W7xAEvVMfcox2GaQJxyDU=";
		 if(a==0){
			  c1="a001";
			  s1="IJoX0C4r3S9wFact+isy1zJhO7ZpDi3ewjdY3zSRPmc=";
		}
		else if(a==1){ 
			  c1="a003";
			  s1="5TTHwRqaagfqky5r8Wvgq2W7xAEvVMfcox2GaQJxyDU=";
		 }
		else if(a==2){ 
			  c1="a004";
			  s1="yziCj/caucG4FEl1uMuQ3sqIgVJNqI9znWW2OR6iH+M=";
		 }
		else if(a==3){ 
			  c1="a005";
			  s1="PdcvLrlISxaycwbPnX9zALxpF84uDzpKmgCPCUSbpDA=";
		 }
		else if(a==4){ 
			  c1="callmeback";
			  s1="cPNna9fmQ0bhqQ7GMftouDbsVPFd8KCFCId3oy0QSS4=";
		 }
		 
		 Translate.setClientId(c1);
		 Translate.setClientSecret(s1);
		 String[] translatedText =Translate.execute(new String[]{src[0],src[1]},  to);
			String[] s=new String[3];
			s[0]=translatedText[0];
			s[1]=translatedText[1];
			s[2]="";
			return s;
	}

	public static void main(String[] args) throws Exception {
		 
		String[] translatedText =bing(
				new String[] {"Kiss Browser","事实上",""},
				"en");
	    for (String string : translatedText) {
	    	System.out.println(string);
		} 
		 
		
	}
}
