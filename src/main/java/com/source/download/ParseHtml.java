package com.source.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.source.util.ListUtil;

/**
 * httpClient的方式访问页面
 * 将html页面保存下来
 * 采用正则表达式，获取链接
 * */
public class ParseHtml {
	//用来存储要解析对应网站的网址资源链接
	List<String> parseList = new ArrayList<String>();
	
	public List<String> getZipSource(String remoteUrl,String proxyIp,int proxyPort) throws Exception {
		//1.初始化url
		URL url=new URL(remoteUrl);
		//2.设置代理
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp,proxyPort)); 
		//3.建立连接
		URLConnection Conn=url.openConnection(proxy);
		
		BufferedReader bufin=new BufferedReader(new InputStreamReader(Conn.getInputStream())); //transform the buffered
		//4.开始解析
		String line = null;
		String regex = "\\b(?<=\">)(.*)\\b(zip(?=<))";  
		Pattern pattern = Pattern.compile(regex);
		while((line=bufin.readLine())!=null){
			 Matcher m=pattern.matcher(line);
			 while(m.find()){
				 parseList.add(m.group());
			 }
		 }
		return ListUtil.removeDuplicate(parseList);
	}
	
}
