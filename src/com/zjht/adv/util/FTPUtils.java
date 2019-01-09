package com.zjht.adv.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.hssf.record.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.zjht.adv.entity.BillConfig;

@Service
@Transactional
public class FTPUtils implements FTPMethodForBiz{
	private static final Logger log=LoggerFactory.getLogger(FTPUtils.class);
	//下载目录
	private static final String dowanloadDir="/home/abc/";  
	//sftp端口
	private static final int FTP_PORT=22;
	//获取并设置SFTP服务器IP地址为172.16.101.210
	private static final String SERVER_ADDRESS = "172.16.101.210";
	private static FTPClient ftp;
	
	//FTP上放文件的目录(每10分钟)
    private static final String ftpFileDir="/opt/site/zjhtshop/deploy/ftp/pos/";
    
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		try {
			FTPUtils ftp=new FTPUtils();
			//ftp.loadSFtp();
			//System.out.println(sftp.connectFtp("121.14.62.204","dsdata","sS8jfsj#"));
//			ftp.loadAndDeal("121.14.62.204","dsdata","sS8jfsj#");
			InputStream is=ftp.createFile("2015031821.txt","UTF-8");
			if (is!=null) {
				System.out.println(ftp.uploadFile("121.14.62.204", "21", "dsdata","sS8jfsj#", "/", "test2015318.txt", is));
			}else{
				System.err.println("写入文件失败！ ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long cost=System.currentTimeMillis()-startTime; //共计秒数
		log.info("系统执行【ftp服务器上拿文件，与本地进行同步】监控，时长："+cost+"ms");
	}
	
	/**
	 * 利用JSch包实现SFTP下载、上传文件
	 * @param ip 主机IP
	 * @param user 主机登陆用户名
	 * @param psw  主机登陆密码
	 * @param port 主机ssh2登陆端口，如果取默认值，传-1
	 */
	public static String sshSftp(String ip, String user, String psw ,int port) throws Exception{
	    Session session = null;
	    Channel channel = null;
	    JSch jsch = new JSch();
	    if(port <=0){
	        //连接服务器，采用默认端口
	        session = jsch.getSession(user, ip);
	    }else{
	        //采用指定的端口连接服务器
	        session = jsch.getSession(user, ip ,port);
	    }
	    //如果服务器连接不上，则抛出异常
	    if (session == null) {
	        throw new Exception("session is null");
	    }
	    //设置登陆主机的密码
	    session.setPassword(psw);//设置密码  
	    //设置第一次登陆的时候提示，可选值：(ask | yes | no)
	    session.setConfig("StrictHostKeyChecking", "no");
	    //设置登陆超时时间  
	    session.connect(30000);
	    try {
	        //创建sftp通信通道
	        channel = (Channel) session.openChannel("sftp");
	        channel.connect(1000);
	        ChannelSftp sftp = (ChannelSftp) channel;
	        //进入服务器指定的文件夹
	        sftp.cd(dowanloadDir);
	        //列出服务器指定的文件列表
	        @SuppressWarnings("rawtypes")
			Vector v = sftp.ls("*.txt");
	        for(int i=0;i<v.size();i++){
	            System.out.println(v.get(i));
	        }
	        //以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
//	        OutputStream outstream = sftp.put("1.txt");
//	        InputStream instream = new FileInputStream(new File("c:/pos.txt"));
//	         
//	        byte b[] = new byte[1024];
//	        int n;
//	        while ((n = instream.read(b)) != -1) {
//	            outstream.write(b, 0, n);
//	        }
//	        outstream.flush();
//	        outstream.close();
//	        instream.close();
	        
	        //下载
	        OutputStream out = new FileOutputStream(FTPUtils.class.getResource("/").getPath()+"/download.txt");
	        InputStream is = sftp.get("1.txt");
            byte[] buff = new byte[1024 * 2];
            int read;
            if (is != null) {
                System.out.println("Start to read input stream");
                do {
                    read = is.read(buff, 0, buff.length);
                    if (read > 0) {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
                System.out.println("input stream read done.");
            }
    		//InputStream fis = new FileInputStream(url.getPath());
//            List<String> list=FileUtils.readLines(new File(url.getPath()), "UTF-8");
//            for (int i = 1; i < list.size(); i++) {
//				System.out.println(list.get(i));
//			}
            return "download.txt";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.disconnect();
	        channel.disconnect();
	    }
	}
	
	public void loadSFtp() throws Exception{
		String filePath=FTPUtils.sshSftp(SERVER_ADDRESS,"root","zjht-123",FTP_PORT);
		URL url = this.getClass().getClassLoader().getResource(filePath);
		File file=new File(url.getFile());
		@SuppressWarnings("unchecked")
		List<String> list=FileUtils.readLines(file);
		if (list!=null&&list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
	/***
	 * 
	 * @description: <连接ftp服务器，成功则返回，最多连接3次>
	 * @param:
	 * @return:
	 * @throws:
	 */
	public boolean connectFtp(String address,String user,String password){		
		try{
			
			log.info("向服务器" + address + "发起连接请求...");
			System.out.println("向服务器" + address + "发起连接请求...");
			ftp = new FTPClient();
//			FTPClientConfig ftpClientConfig = new FTPClientConfig();  
//	        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());  
//	        ftp.setControlEncoding("GBK");
			int i=3;
			while(i>0){
				/**
				 * 2010/08/24 added
				 * 通过服务器IP地址最多尝试连接三次
				 * 
				 */
				ftp.connect(address);
				ftp.login(user, password);
				boolean isOk = ftp.isConnected();
				log.info(i + " 连接状态 " + isOk);
				if(isOk){
					ftp.enterLocalPassiveMode();
					ftp.setDataTimeout(60 * 1000);
					return true;
				}else{
					i--;
					log.info("尝试向服务器" + SERVER_ADDRESS + "再次发起连接请求...");
				}
				i--;
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
			System.out.println(ex.getMessage());
			return false;
		}
		return false;
	}
	
	/**
	 * <连接ftp服务器，成功则返回，最多连接3次>
	 * @param address ip地址
	 * @param port 端口号
	 * @param user 账户名
	 * @param password 密码
	 * @return
	 */
	public boolean connectFtp(String address,String port,String user,String password){		
		try{
			
			log.info("向服务器" + address + "发起连接请求...");
			System.out.println("向服务器" + address + "发起连接请求...");
			ftp = new FTPClient();
//			FTPClientConfig ftpClientConfig = new FTPClientConfig();  
//	        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());  
//	        ftp.setControlEncoding("GBK");
			int i=3;
			while(i>0){
				/**
				 * 2010/08/24 added
				 * 通过服务器IP地址最多尝试连接三次
				 * 
				 */
				ftp.connect(address,Integer.parseInt(port));
				ftp.login(user, password);
				boolean isOk = ftp.isConnected();
				log.info(i + " 连接状态 " + isOk);
				if(isOk){
					ftp.enterLocalPassiveMode();
					ftp.setDataTimeout(60 * 1000);
					return true;
				}else{
					i--;
					log.info("尝试向服务器" + SERVER_ADDRESS + "再次发起连接请求...");
				}
				i--;
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
			System.out.println(ex.getMessage());
			return false;
		}
		return false;
	}
	@Override
	public List<T> loadAndDeal(String address,String user,String password){
		List<T> listData=null;
		try{
			boolean flag=connectFtp(address, user, password);
			//下载文件
			String [] names = ftp.listNames();
			log.info("names"+names);
			if(flag){
				if(names!=null&&names.length>0){
					String dealFileName="YFT"+DateTimeUtils.getDate(-1, "yyyyMMdd")+".txt";
					log.info("dealFileName"+dealFileName);
//					String dealFileName="YFT20150520.txt";
					boolean foundForDeal=false;
					for (String fileName : names) {
						if (!dealFileName.equals(fileName)) {
							continue;
						}
						if (!foundForDeal) {
							listData=new ArrayList<T>();
						}
						foundForDeal=true;
						try{
							log.info("开始下载文件" + fileName + "...");
							System.out.println("开始下载文件" + fileName + "...");
							File file = new File(ftpFileDir + fileName);             // 生产机
//							File file = new File("/" + fileName);                    // 本地
							FileOutputStream fos = new FileOutputStream(file);
							ftp.retrieveFile(fileName, fos);
							fos.close();
							log.info(fileName + "文件下载完毕,大小" + file.length() + "bytes"); 
							System.out.println(fileName + "文件下载完毕,大小" + file.length() + "bytes");
							@SuppressWarnings("unchecked")
							List<String> list=FileUtils.readLines(file,"GBK");
							if (list!=null&&list.size()>0) {
								List<String> orderNos=Lists.newArrayList();
								int nums=1;
								while (list.size()>=nums) {
									String orderNo=DateTimeUtils.genOrderNo(24);
									if (!orderNos.contains(orderNo)) {
										orderNos.add(orderNo);
										nums++;
									}
								}
								for (int i = 0; i < list.size(); i++) {
									String line=list.get(i);
									System.out.println(DateTimeUtils.getDate(0, "yyyy-MM-dd HH:mm:ss")+"【"+(i+1)+"】："+line);
									if (StringUtils.isBlank(line)) {
										continue;
									}
//									String[] arr=line.split("\\|");
									try {
										T po=new T();
										listData.add(po);
									} catch (Exception e) {
										//日志打印
										log.error("封装pos文件数据发生未知异常，异常详情："+e.getMessage());
										continue;
									}
								}
							}
						}catch(Exception ex){
							log.info("FTP下载过程发生异常:"+ex.getMessage()+"!");
							System.out.println("FTP下载过程发生异常:"+ex.getMessage()+"!");
							return null;
						}
					}
					if (!foundForDeal) {
						log.info("找不到"+dealFileName+"需要处理文件！");
						System.out.println("找不到"+dealFileName+"需要处理文件！");
					}
				}else{
					log.info("下载目录下找不到指定文件，服务器响应代码："+ftp.getReplyCode()+";详细信息："+ftp.getReplyString());
					System.out.println("下载目录下找不到指定文件，服务器响应代码："+ftp.getReplyCode()+";详细信息："+ftp.getReplyString());
				}
				ftp.logout();
				ftp.disconnect();
			}else{
				log.info("没有连接上ftp服务器!");
				System.out.println("没有连接上ftp服务器!");
			}
			return listData;
		}catch(Exception ex){
			System.out.println("连接ftp服务器异常!异常信息："+ex.getMessage());
			return null;
		}
	}
	
	@Override
	public List<T> loadAndDeal(String address,String user,String password,String dealFileName){
		List<T> listData=null;
		try{
			boolean flag=connectFtp(address, user, password);
			//下载文件
			String [] names = ftp.listNames();
			log.info("names"+names);
			if(flag){
				if(names!=null&&names.length>0){
//					String dealFileName="YFT"+DateTimeUtils.getDate(-1, "yyyyMMdd")+".txt";
//					log.info("dealFileName"+dealFileName);
//					String dealFileName="YFT20150520.txt";
					boolean foundForDeal=false;
					for (String fileName : names) {
						if (!dealFileName.equals(fileName)) {
							continue;
						}
						if (!foundForDeal) {
							listData=new ArrayList<T>();
						}
						foundForDeal=true;
						try{
							log.info("开始下载文件" + fileName + "...");
							System.out.println("开始下载文件" + fileName + "...");
							File file = new File(ftpFileDir + fileName);             // 生产机
//							File file = new File("/" + fileName);                    // 本地
							FileOutputStream fos = new FileOutputStream(file);
							ftp.retrieveFile(fileName, fos);
							fos.close();
							log.info(fileName + "文件下载完毕,大小" + file.length() + "bytes"); 
							System.out.println(fileName + "文件下载完毕,大小" + file.length() + "bytes");
							@SuppressWarnings("unchecked")
							List<String> list=FileUtils.readLines(file,"GBK");
							if (list!=null&&list.size()>0) {
								List<String> orderNos=Lists.newArrayList();
								int nums=1;
								while (list.size()>=nums) {
									String orderNo=DateTimeUtils.genOrderNo(24);
									if (!orderNos.contains(orderNo)) {
										orderNos.add(orderNo);
										nums++;
									}
								}
								for (int i = 0; i < list.size(); i++) {
									String line=list.get(i);
									System.out.println(DateTimeUtils.getDate(0, "yyyy-MM-dd HH:mm:ss")+"【"+(i+1)+"】："+line);
									if (StringUtils.isBlank(line)) {
										continue;
									}
//									String[] arr=line.split("\\|");
									try {
										T po=new T();
										listData.add(po);
									} catch (Exception e) {
										//日志打印
										log.error("封装pos文件数据发生未知异常，异常详情："+e.getMessage());
										continue;
									}
								}
							}
						}catch(Exception ex){
							log.info("FTP下载过程发生异常:"+ex.getMessage()+"!");
							System.out.println("FTP下载过程发生异常:"+ex.getMessage()+"!");
							return null;
						}
					}
					if (!foundForDeal) {
						log.info("找不到"+dealFileName+"需要处理文件！");
						System.out.println("找不到"+dealFileName+"需要处理文件！");
					}
				}else{
					log.info("下载目录下找不到指定文件，服务器响应代码："+ftp.getReplyCode()+";详细信息："+ftp.getReplyString());
					System.out.println("下载目录下找不到指定文件，服务器响应代码："+ftp.getReplyCode()+";详细信息："+ftp.getReplyString());
				}
				ftp.logout();
				ftp.disconnect();
			}else{
				log.info("没有连接上ftp服务器!");
				System.out.println("没有连接上ftp服务器!");
			}
			return listData;
		}catch(Exception ex){
			System.out.println("连接ftp服务器异常!异常信息："+ex.getMessage());
			return null;
		}
	}
	
	/**
	 * 生成消费对账文件
	 * @param encoding
	 * @return
	 */
	@Override
	public InputStream createFile(String fileName,String encoding) {
		log.info("生成文件begin...");
		try {
			FileWriter fw = null;
			File ff = null;
			try {
				String path=FTPUtils.class.getResource("/").getPath()+"excel/";
				File filePath=new File(path);
				if (!filePath.exists()) {
					filePath.mkdir();
				}
				path+=fileName;
				ff=new File(path);
				if (ff.exists()) {
					log.error("对账文件已经存在，跳过生成！");
					return null;
				}
				ff.createNewFile();
				fw = new FileWriter(path, true);
				// 每读写完一行数据回车换行
				try {
					RWPropertityUtil rwpUtil=RWPropertityUtil.getInstance();
					BillConfig config = rwpUtil.getMobilePropetity();
					Random r= new Random();
					r.nextInt(100);
					StringBuffer sBuffer=new StringBuffer();
					int buildSize=10;
					if (config.getBuildSize()!=null&&config.getBuildSize().intValue()>0) {
						buildSize=config.getBuildSize();
					}
					for (int i = 0; i < buildSize; i++) {
						sBuffer.append(config.getOrderPrefix()).append(DateTimeUtils.genOrderNo(24)).append("|");//订单号
						String mobile=null;
						String merchant=null;
						sBuffer.append(mobile).append("|");//消费号码
						int min=Integer.parseInt(config.getMinMoney());
						int max=Integer.parseInt(config.getMaxMoney());
						int money=min;
						boolean flag=false;
						do{
							money+=r.nextInt(max-min);
						}while(flag);
						sBuffer.append(money*100).append("|");//支付金额
						if (StringUtils.isBlank(merchant)) {
							merchant=config.getMerchantPrefix()+String.valueOf(r.nextInt(100000));
							if (merchant.length()<6) {
								int need=6-merchant.length();
								for (int j = 0; j < need; j++) {
									merchant=merchant+r.nextInt(9);
								}
							}
						}
						sBuffer=sBuffer.append(merchant).append("|");//交易商户
						Date date=new Date();
						long time=date.getTime()+r.nextInt(config.getTime().intValue());
						Date d=new Date(time);
						sBuffer.append(DateTimeUtils.formatDateStr(d, "yyyy-MM-dd HH:mm:ss")).append("|");//支付时间
						int status=0;//默认为成功状态
						if (r.nextInt(100)<10) {
							status=1;//百分之十的记录为失败状态
						}
						sBuffer.append(status).append("\r\n");//状态
					}
					fw.write(sBuffer.toString());
					InputStream fis=new FileInputStream(ff);
					return fis;
				} catch (Exception e3) {
					log.error("生成对账文件发生未知异常，异常详情："+e3.getMessage());
					// 若读写过程中发生异常，则将本记录写入数据库
					return null;
				}
			} catch (IOException e1) {
				log.error("读写对账文件异常，异常详情："+e1.getMessage());
				return null;
			} finally {
				try {
					if (fw != null) {
						fw.close();
					}
				} catch (Exception e2) {
					log.error("关闭读写对账文件流异常，异常详情："+e2.getMessage());
					return null;
				}
			}
			//FileUtils.copyFileToDirectory(ff, new File(genDir));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("处理对账文件异常：" + e.getMessage());
			return null;
		} finally{
			log.info("生成对账文件end...");
		}
	}
	
	/**
     * Description: 向FTP服务器上传文件
     * @param url FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path FTP服务器保存目录
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
	@Override
    public boolean uploadFile(String address, String port, String username, String password, String path,
            String filename, InputStream input) {
        boolean returnValue = false;
        try {
        	boolean flag=connectFtp(address,port, username, password);
			if(flag){
				if (StringUtils.isBlank(path)) {
					ftp.changeWorkingDirectory(ftpFileDir);
				}else{
					ftp.changeWorkingDirectory(path);
				}
	            ftp.storeFile(filename, input);
	            input.close();
	            ftp.logout();
	            returnValue = true;
			}
        } catch (IOException e) {
            log.error("文件上传异常，异常详情:" + e.getMessage());
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                	log.error("关闭FTP异常，异常详情："+ioe.getMessage());
                }
            }
        }
        return returnValue;
    }
}
