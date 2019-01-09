package com.zjht.adv.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Properties;

import com.zjht.adv.entity.BillConfig;

/**
 * 读写对账配置文件生成工具类
 * @author lijunjie
 *
 */
public class RWPropertityUtil {

	private static Object lock = new Object();
	private static RWPropertityUtil config = null;

	public static RWPropertityUtil getInstance() {
		synchronized (lock) {
			if (null == config) {
				config = new RWPropertityUtil();
			}
		}
		return (config);
	}
	
	public static String getProperty(String key){
		InputStream in = RWPropertityUtil.class.getResourceAsStream("/config/website.properties"); 
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}

	public static boolean getBooleanProperty(String key){
		boolean is = false;
		
		String str = getProperty(key);		
		is = (new Boolean(str)).booleanValue();		
		
		return is;
	}
	
	public static String getConfigDir(){
		String dir = "/test";
		if(getBooleanProperty("isProductDeploy")){
			dir = "/product";
		}
		return dir;
	}

	/**
	 * 设置属性文件值
	 * 
	 * @param mConfig
	 *            属性名
	 * @throws IOException
	 *             文件读写异常
	 */
	@SuppressWarnings("deprecation")
	public void setMobilePropetity(BillConfig config)
			throws IOException {
		Properties properties = new Properties();
		String fileName="config/pos/"+getConfigDir()+"/bill.properties";
		String filePath="/"+fileName;
		InputStream fis = this.getClass().getResourceAsStream(filePath);
		// 从输入流中读取属性列表（键和元素对）
		if (fis != null) {
			properties.load(fis);
		}
		
		// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		URL url = this.getClass().getResource(filePath);
		FileOutputStream fos = null;
		if (url != null) {
			String path=this.getClass().getResource(filePath).getPath();
			//处理文件夹空格
			path=URLDecoder.decode(path);
			fos = new FileOutputStream(path);
		} else {
			fos = new FileOutputStream(this.getClass().getResource("/")
					.getPath()+fileName);
		}
		
		properties.setProperty("build.size", String.valueOf(config.getBuildSize()));
		properties.setProperty("build.money.min", String.valueOf(config.getMinMoney()));
		properties.setProperty("build.money.max", String.valueOf(config.getMaxMoney()));
		properties.setProperty("build.time", String.valueOf(config.getTime()));
		properties.setProperty("build.order.prefix", config.getOrderPrefix());
		properties.setProperty("build.merchant.prefix", config.getMerchantPrefix());
		
		// 以适合使用 load 方法加载到 Properties 表中的格式，
		// 将此 Properties 表中的属性列表（键和元素对）写入输出流
		properties.store(fos, new Date().toString());
		fos.flush();
		fos.close();
	}
	/**
	 * 获取属性文件值
	 * 
	 * @throws IOException
	 *             文件读写异常
	 */
	public BillConfig getMobilePropetity()
			throws IOException {
		Properties properties = new Properties();
		String filePath="/config/pos/"+getConfigDir()+"/bill.properties";
		URL url=RWPropertityUtil.class.getClassLoader().getResource(filePath);
		InputStream fis = new FileInputStream(url.getPath());
		properties.load(fis);
		fis.close();
		BillConfig config=new BillConfig();
		config.setBuildSize(Integer.parseInt(properties.getProperty("build.size")));
		config.setMinMoney(properties.getProperty("build.money.min"));
		config.setMaxMoney(properties.getProperty("build.money.max"));
		config.setTime(Long.parseLong(properties.getProperty("build.time")));
		config.setOrderPrefix(properties.getProperty("build.order.prefix"));
		config.setMerchantPrefix(properties.getProperty("build.merchant.prefix"));
		return config;
	}
}
