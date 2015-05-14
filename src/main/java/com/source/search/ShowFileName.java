package com.source.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.source.data.SourceURL;

/**
 * 此类用来实现遍历已下载文件的当前目录
 * 返回当前目录下的文件值
 * */
public class ShowFileName {
	public ShowFileName(){
		super();
	}
	
	 /*返回当前目录下的已存在文档*/
	public List<String> getFileName(String localPath) {
		//定义List，用来返回文件名称的值
		List<String> fileNameList = new ArrayList<String>();
		//定义list的索引
		int index = 0;
		
		String path = localPath; //the route of the file 
		File file = new File(path);
		if (!file.exists()) {
            System.out.println(path + " not exists");
            return fileNameList;
        }
		//开始遍历对应目录下的文件
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
