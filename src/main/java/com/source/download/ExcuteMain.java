package com.source.download;

import java.util.List;

import org.junit.Test;

import com.source.data.SourceURL;
import com.source.search.ShowFileName;
import com.source.util.ListUtil;

public class ExcuteMain {
	//创建列出文件目录的列表类
	public ShowFileName fileName = new ShowFileName();
	//创建解析html页面的类
	public ParseHtml parseHtml = new ParseHtml();
	//创建保存文件列表页
	public SaveFile saveFile = new SaveFile();
	//创建解析网页后产生的list
	List<String> parseList;
	//创建解析浏览目录后产生的list
	List<String> fileList;
	//最后用来下载链接的URL地址
	List<String> listFinal;
	
	/**用来下载events中的内容*/
	@Test
	public void DownLoadEvents(){
		try {
			parseList = parseHtml.getZipSource(SourceURL.remoteEvents, SourceURL.proxyIP,SourceURL.proxyPort);
		} catch (Exception e) {
			System.out.println("--->解析网页失败，请查看代理是否正确");
		}
		
		try {
			fileList = fileName.getFileName(SourceURL.EventsPath);
		} catch (Exception e) {
			System.out.println("--->读取本地文件目录失败");
		}
		listFinal = ListUtil.unionList(parseList, fileList);
		if(listFinal.size() == 0){
			System.out.println("列表已经为最新，不用更新！");
			return;
		}
		for(int i=0; i<listFinal.size(); i++){
			saveFile.addItem(SourceURL.EventsSource+listFinal.get(i),SourceURL.EventsPath+listFinal.get(i));
 			System.out.println(listFinal.get(i));
 		}
		saveFile.downLoadByList(SourceURL.proxyIP, SourceURL.proxyPort);
	}
	
	/**用来下载gkg中的内容*/
	@Test
	public void DownLoadGKG(){
		try {
			parseList = parseHtml.getZipSource(SourceURL.remouteGKG, SourceURL.proxyIP,SourceURL.proxyPort);
		} catch (Exception e) {
			System.out.println("--->解析网页失败，请查看代理是否正确");
		}
		
		try {
			fileList = fileName.getFileName(SourceURL.GKGPath);
		} catch (Exception e) {
			System.out.println("--->读取本地文件目录失败");
		}
		listFinal = ListUtil.unionList(parseList, fileList);
		if(listFinal.size() == 0){
			System.out.println("列表已经为最新，不用更新！");
			return;
		}
		for(int i=0; i<listFinal.size(); i++){
			saveFile.addItem(SourceURL.GKGSource+listFinal.get(i),SourceURL.GKGPath+listFinal.get(i));
 			System.out.println(listFinal.get(i));
 		}
		saveFile.downLoadByList(SourceURL.proxyIP, SourceURL.proxyPort);
	}
}
