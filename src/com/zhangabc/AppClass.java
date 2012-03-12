package com.zhangabc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppClass {
	
	public static String[] getClassByParentName(String name){
		
		HashMap<String, String[]>   info   =   new   HashMap(); 
		info.put( "游戏",   new String[]{"休闲","益智","角色扮演","战略","动作","射击","经营","养成","冒险","网游","棋牌","模拟器","体育"}); 
		info.put( "应用",   new String[]{"浏览","娱乐","社交","实用","办公","系统","其他"}); 
		info.put( "装机必备",  new String[]{"浏览","娱乐","社交","实用","办公","系统","其他"}); 

		String[] result = (String[])info.get(name);
		if( result == null) {
			return null;
			
		}
		//String   name   =   (String)person.get( "name ");
		 
		return result;
	} 
}
