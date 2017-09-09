package com.kkgame.feeop.util;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReader {

	@SuppressWarnings("unchecked")
	public static void main(String arge[]) { 
		long lasting = System.currentTimeMillis();
		try {
			File f = new File("G:\\work\\workplatform\\src\\project.xml");
			SAXReader reader = new SAXReader(); 
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;
			for (Iterator i = root.elementIterator("message"); i.hasNext();) {
				foo = (Element) i.next();
				System.out.print("客户ID:" + foo.elementText("clientId"));
				System.out.println("客户名称:" + foo.elementText("clientName"));
				System.out.print("项目ID:" + foo.elementText("projectId"));
				System.out.println("项目名称ID:" + foo.elementText("projectName"));
				}
			} catch (Exception e) {
			e.printStackTrace();
			}
			System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) + " 毫秒");
		} 
}
