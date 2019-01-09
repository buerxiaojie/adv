package com.zjht.adv.util;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

public interface FTPMethodForBiz {
	/**
	 * 下载并处理ftp文件，返回订单列表
	 * @param address ftp ip 地址
	 * @param user ftp 账号
	 * @param password ftp 密码
	 * @return
	 */
	public List<T> loadAndDeal(String address,String user,String password);
	/**
	 * 下载指定ftp文件并处理，返回订单列表
	 * @param address ftp ip 地址
	 * @param user ftp 账号
	 * @param password ftp 密码
	 * @param dealFileName 指定ftp文件
	 * @return
	 */
	public List<T> loadAndDeal(String address,String user,String password,String dealFileName);
	/**
	 * 生成对账文件
	 * @param encoding
	 * @return
	 */
	public InputStream createFile(String fileName,String encoding);
	/**
     * Description: 向FTP服务器上传文件
     * @param url FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path FTP服务器保存目录(若参数留空，则使用默认路径)
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
    */
	public boolean uploadFile(String address, String port, String username, String password, String path,
            String filename, InputStream input);
}
