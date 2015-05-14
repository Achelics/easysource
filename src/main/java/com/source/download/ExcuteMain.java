package com.source.download;

import java.util.List;

import org.junit.Test;

import com.source.data.SourceURL;
import com.source.search.ShowFileName;
import com.source.util.ListUtil;

public class ExcuteMain {
	//�����г��ļ�Ŀ¼���б���
	public ShowFileName fileName = new ShowFileName();
	//��������htmlҳ�����
	public ParseHtml parseHtml = new ParseHtml();
	//���������ļ��б�ҳ
	public SaveFile saveFile = new SaveFile();
	//����������ҳ�������list
	List<String> parseList;
	//�����������Ŀ¼�������list
	List<String> fileList;
	//��������������ӵ�URL��ַ
	List<String> listFinal;
	
	/**��������events�е�����*/
	@Test
	public void DownLoadEvents(){
		try {
			parseList = parseHtml.getZipSource(SourceURL.remoteEvents, SourceURL.proxyIP,SourceURL.proxyPort);
		} catch (Exception e) {
			System.out.println("--->������ҳʧ�ܣ���鿴�����Ƿ���ȷ");
		}
		
		try {
			fileList = fileName.getFileName(SourceURL.EventsPath);
		} catch (Exception e) {
			System.out.println("--->��ȡ�����ļ�Ŀ¼ʧ��");
		}
		listFinal = ListUtil.unionList(parseList, fileList);
		if(listFinal.size() == 0){
			System.out.println("�б��Ѿ�Ϊ���£����ø��£�");
			return;
		}
		for(int i=0; i<listFinal.size(); i++){
			saveFile.addItem(SourceURL.EventsSource+listFinal.get(i),SourceURL.EventsPath+listFinal.get(i));
 			System.out.println(listFinal.get(i));
 		}
		saveFile.downLoadByList(SourceURL.proxyIP, SourceURL.proxyPort);
	}
	
	/**��������gkg�е�����*/
	@Test
	public void DownLoadGKG(){
		try {
			parseList = parseHtml.getZipSource(SourceURL.remouteGKG, SourceURL.proxyIP,SourceURL.proxyPort);
		} catch (Exception e) {
			System.out.println("--->������ҳʧ�ܣ���鿴�����Ƿ���ȷ");
		}
		
		try {
			fileList = fileName.getFileName(SourceURL.GKGPath);
		} catch (Exception e) {
			System.out.println("--->��ȡ�����ļ�Ŀ¼ʧ��");
		}
		listFinal = ListUtil.unionList(parseList, fileList);
		if(listFinal.size() == 0){
			System.out.println("�б��Ѿ�Ϊ���£����ø��£�");
			return;
		}
		for(int i=0; i<listFinal.size(); i++){
			saveFile.addItem(SourceURL.GKGSource+listFinal.get(i),SourceURL.GKGPath+listFinal.get(i));
 			System.out.println(listFinal.get(i));
 		}
		saveFile.downLoadByList(SourceURL.proxyIP, SourceURL.proxyPort);
	}
}
