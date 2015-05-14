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
	public final static boolean DEBUG = true; // ������
	private static int BUFFER_SIZE = 10240; // ��������С(������Խ�����ص�Խ��,����Ҫ�����Լ��ķ���������)
	private Vector<String> vDownLoad = new Vector(); // URL�б�
	private Vector<String> vFileList = new Vector(); // ���غ�ı����ļ����б�

	/**
	 * ���췽��
	 */
	public SaveFile() {
		
	}

	/**
	 * ��������б�
	 */
	public void resetList() {
		vDownLoad.clear();
		vFileList.clear();
	}

	/**
	 * ���������б���
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
	 * �����б�������Դ
	 */
	public void downLoadByList(String proxyIp,int port) {
		String url = null;
		String filename = null;

		// ���б�˳�򱣴���Դ
		for (int i = 0; i < vDownLoad.size(); i++) {
			url = (String) vDownLoad.get(i);
			filename = (String) vFileList.get(i);

			try {
				saveToFile(url, filename, proxyIp, port);
			} catch (IOException err) {
				if (DEBUG) {
					System.out.println("��Դ[" + url + "]����ʧ��!!!");
				}
			}
		}

		if (DEBUG) {
			System.out.println("�������!!!");
		}
	}

	/**
	 * ��HTTP��Դ���Ϊ�ļ�
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
		// ��������
		url = new URL(destUrl);
		httpUrl = (HttpURLConnection) url.openConnection(proxy);
		// ����ָ������Դ
		httpUrl.connect();
		// ��ȡ����������
		bis = new BufferedInputStream(httpUrl.getInputStream());
		// �����ļ�
		fos = new FileOutputStream(fileName);

		if (this.DEBUG)
			System.out.println("���ڻ�ȡ����[" + destUrl + "]������...\n���䱣��Ϊ�ļ�["
					+ fileName + "]");
		// �����ļ�
		while ((size = bis.read(buf)) != -1)
			fos.write(buf, 0, size);

		fos.close();
		bis.close();
		httpUrl.disconnect();
	}

	/**
	 * ���ô��������
	 * 
	 * @param proxy
	 *            String
	 * @param proxyPort
	 *            String
	 */
	public static void setProxyServer(String proxy, String proxyPort) {
		// ���ô��������
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("proxyHost", proxy);
		System.getProperties().put("proxyPort", proxyPort);
	}
}
