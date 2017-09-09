package com.kokmobi.server.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Test {

	
	public static void main(String[] args) {
		int ua = 200204;
		String uas = "200201,200202,200203";
		if(!uas.contains(ua+"")) {
			System.out.println("not support your browser");
		}
		else{
			System.out.println("support your browser");
		}
		
	}
}
