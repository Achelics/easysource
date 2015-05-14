package com.source.util;

import java.util.List;
/**
 * 对应集合文件的帮助类 
 * */
public class ListUtil {
	
	public static List<String> unionList(List<String> parseList,List<String> fileList) {
		
		for(int i=0; i<fileList.size(); i++) {
			String name=fileList.get(i);
			parseList.remove(name);
		}
		return parseList;
	}
	
	public static List<String> removeDuplicate(List<String> list) {
		   for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
		     for ( int j = list.size() - 1 ; j > i; j -- ) {
		       if (list.get(j).equals(list.get(i))) {
		         list.remove(j);
		       } 
		      } 
		    } 
		   return list;
	} 
}
