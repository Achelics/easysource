package com.source.data;

public class SourceURL {
	/*要抓取网页的地址 */
	public static final String remouteGKG = "http://data.gdeltproject.org/gkg/index.html";
	public static final String remoteEvents = "http://data.gdeltproject.org/events/index.html";
	/*要拼接下载网网络资源的地址*/
	public static final String GKGSource = "http://data.gdeltproject.org/gkg/";
	public static final String EventsSource = "http://data.gdeltproject.org/events/";
	/*保存文件在本地的路径*/
	public static final String EventsPath = "E:/eventsdata/";
	public static final String GKGPath = "E:/gkgdata/";
	/*使用的代理服务器的ip地址*/
	public static final String proxyIP = "127.0.0.1";
	/*使用的代理服务器的端口号*/
	public static final int proxyPort = 8087;
}
