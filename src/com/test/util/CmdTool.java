package com.test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/***
 * cmd����ִ����
 * @author xusai
 *packagename��com.xstore.sevenfresh
 */
public class CmdTool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CmdTool cmdTool=new CmdTool();
		List<String> list=cmdTool.getListByCmd("adb shell dumpsys meminfo|findstr Foreground");
		for(String s:list){
			System.out.println(s+"****");
		}

	}
	/***
	 * ִ��cmd�����ȡ����ֵ
	 * @param cmdString cmd����
	 */
	public static void singleCmd(String cmdString){
		Runtime r=Runtime.getRuntime();
		try {
			Process process=r.exec("cmd /c "+cmdString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/***
	 * ִ��cmd������������list����
	 * @param cmdString cmd����
	 * @return
	 */
	public static List<String> getListByCmd(String cmdString){
		List<String> list=new ArrayList<String>();
		try {
			Process process=Runtime.getRuntime().exec("cmd /c "+cmdString);
			InputStream in=process.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(in));
			String lineString=null;
			while (StringTool.replaceBlank((lineString=reader.readLine()))!=null) {
				list.add(lineString.trim());
			}
			process.waitFor();
			process.destroy();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//ִ��cmd��������������string
}
