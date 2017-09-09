package com.kkgame.hz.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Timmy
 * @创建时间 2011-12-5下午04:31:42
 * @version 1.0
 */
public class ConfigUtils {

	private static String propertiesFile = "/application.properties";

	private static final Log LOG = LogFactory.getLog(ConfigUtils.class);

	private static Map<String, String> propertiesMap;

	static {
		initProperty();
	}

	private static void initProperty() {
		if (propertiesMap != null)
			return;
		InputStream ins = null;
		Properties properties = new Properties();
		try {
			ins = ConfigUtils.class.getResourceAsStream(propertiesFile);
			properties.load(ins);
			propertiesMap = new HashMap<String, String>();
			Set<Entry<Object, Object>> entrySet = properties.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				propertiesMap.put((String) entry.getKey(), ((String) entry
						.getValue()).trim());
			}
		} catch (FileNotFoundException e) {
			LOG.info("file not found." + propertiesFile, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOG.info(e);
			throw new RuntimeException(e);
		}
	}

	public static String getString(String proKey) {
		return propertiesMap.get(proKey);
	}

	/**
	 * 得到文件或目录的正式路径<br/> 如果文件以 "/"那么表示绝对路径，直接返回<br/> 否则表示相对于当前classpath的相对路径
	 * 
	 * @author jianguo.xu
	 * @param pathKey
	 * @return
	 */
	private static final String getRealPath(String pathKey) {
		String path = propertiesMap.get(pathKey);
		if (path.startsWith("/")) {
			return path;
		} else {
			URL url = ConfigUtils.class.getResource("/" + path);
			return url.getPath();
		}
	}

	private static final String getWebRoot() {
		URL url = ConfigUtils.class.getResource("/");
		String path = url.getPath();
		if (path.endsWith("/WEB-INF/classes/")) {
			int beginIndex = path.length() - "WEB-INF/classes/".length();
			return path.substring(0, beginIndex);
		}
		return path;
	}

	private static final int getInt(String key) {
		return Integer.parseInt(propertiesMap.get(key));
	}

	private static final boolean getBoolean(String key) {
		String str = getString(key);
		if (str.equalsIgnoreCase("true") || str.equals("1") || str.equals("是")
				|| str.equalsIgnoreCase("yes"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
