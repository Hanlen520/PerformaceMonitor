package com.pm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class StringTool {
	/***
	 * 去掉字符串中的空格，回车，换行符，制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str){
		String dest="";
		if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
	}
	public static boolean stringIsNotNull(String result){
		if (result!=null && !result.equals("")){
			return true;
		}
		return false;
	}
}
