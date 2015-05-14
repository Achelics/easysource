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
 * httpClient�ķ�ʽ����ҳ��
 * ��htmlҳ�汣������
 * ����������ʽ����ȡ����
 * */
public class ParseHtml {
	//�����洢Ҫ������Ӧ��վ����ַ��Դ����
	List<String> parseList = new ArrayList<String>();
	
	public List<String> getZipSource(String remoteUrl,String proxyIp,int proxyPort) throws Exception {
		//1.��ʼ��url
		URL url=new URL(remoteUrl);
		//2.���ô���
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp,proxyPort)); 
		//3.��������
		URLConnection Conn=url.openConnection(proxy);
		
		BufferedReader bufin=new BufferedReader(new InputStreamReader(Conn.getInputStream())); //transform the buffered
		//4.��ʼ����
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
