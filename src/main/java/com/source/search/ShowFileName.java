package com.source.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.source.data.SourceURL;

/**
 * ��������ʵ�ֱ����������ļ��ĵ�ǰĿ¼
 * ���ص�ǰĿ¼�µ��ļ�ֵ
 * */
public class ShowFileName {
	public ShowFileName(){
		super();
	}
	
	 /*���ص�ǰĿ¼�µ��Ѵ����ĵ�*/
	public List<String> getFileName(String localPath) {
		//����List�����������ļ����Ƶ�ֵ
		List<String> fileNameList = new ArrayList<String>();
		//����list������
		int index = 0;
		
		String path = localPath; //the route of the file 
		File file = new File(path);
		if (!file.exists()) {
            System.out.println(path + " not exists");
            return fileNameList;
        }
		//��ʼ������ӦĿ¼�µ��ļ�
		File fa[] = file.listFiles();
		
		for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (!fs.isDirectory()) {
            	fileNameList.add(index, fs.getName());
            	index++;
            }
        }
		return fileNameList;
	}
	@Test
	public void test(){
		ShowFileName sh = new ShowFileName();
		List<String> list =  sh.getFileName(SourceURL.EventsPath);
		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
}
