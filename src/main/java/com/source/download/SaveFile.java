package com.source.download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Vector;

public class SaveFile {
	public final static boolean DEBUG = true; // 调试用
	private static int BUFFER_SIZE = 10240; // 缓冲区大小(缓冲区越大下载的越快,但是要根据自己的服务器配置)
	private Vector<String> vDownLoad = new Vector(); // URL列表
	private Vector<String> vFileList = new Vector(); // 下载后的保存文件名列表

	/**
	 * 构造方法
	 */
	public SaveFile() {
		
	}

	/**
	 * 清除下载列表
	 */
	public void resetList() {
		vDownLoad.clear();
		vFileList.clear();
	}

	/**
	 * 增加下载列表项
	 * 
	 * @param url
	 *            String
	 * @param filename
	 *            String
	 */

	public void addItem(String url, String filename) {
		vDownLoad.add(url);
		vFileList.add(filename);
	}
	/**
	 * 根据列表下载资源
	 */
	public void downLoadByList(String proxyIp,int port) {
		String url = null;
		String filename = null;

		// 按列表顺序保存资源
		for (int i = 0; i < vDownLoad.size(); i++) {
			url = (String) vDownLoad.get(i);
			filename = (String) vFileList.get(i);

			try {
				saveToFile(url, filename, proxyIp, port);
			} catch (IOException err) {
				if (DEBUG) {
					System.out.println("资源[" + url + "]下载失败!!!");
				}
			}
		}

		if (DEBUG) {
			System.out.println("下载完成!!!");
		}
	}

	/**
	 * 将HTTP资源另存为文件
	 * 
	 * @param destUrl
	 *            String
	 * @param fileName
	 *            String
	 * @throws Exception
	 */
	public void saveToFile(String destUrl, String fileName,String proxyIp,int port) throws IOException {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp,port)); 
		// 建立链接
		url = new URL(destUrl);
		httpUrl = (HttpURLConnection) url.openConnection(proxy);
		// 连接指定的资源
		httpUrl.connect();
		// 获取网络输入流
		bis = new BufferedInputStream(httpUrl.getInputStream());
		// 建立文件
		fos = new FileOutputStream(fileName);

		if (this.DEBUG)
			System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件["
					+ fileName + "]");
		// 保存文件
		while ((size = bis.read(buf)) != -1)
			fos.write(buf, 0, size);

		fos.close();
		bis.close();
		httpUrl.disconnect();
	}

	/**
	 * 设置代理服务器
	 * 
	 * @param proxy
	 *            String
	 * @param proxyPort
	 *            String
	 */
	public static void setProxyServer(String proxy, String proxyPort) {
		// 设置代理服务器
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("proxyHost", proxy);
		System.getProperties().put("proxyPort", proxyPort);
	}
}
