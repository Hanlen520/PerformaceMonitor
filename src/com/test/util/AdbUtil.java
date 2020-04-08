package com.test.util;

import java.util.ArrayList;
import java.util.List;

/**
 * ����adbִ��ʱ���״�����������������Ϣ��ʹ��adbʱ��Ҫ��������Ϣ���˵�
 * @author sai
 *
 */
public class AdbUtil {
	private CmdTool cmdUtil = new CmdTool();
	
	/**
	 * ִ��adbָ�����������Ϣ,����list����
	 * @param zhiling
	 * @return
	 */
	public List<String> getListByADB(String zhiling){
		List<String> adblist=new ArrayList<String>();
		for(String s:cmdUtil.getListByCmd(zhiling)){
			if (s.contains("daemon")) {
				continue;
			}else {
				adblist.add(s);
			}
		}
		return adblist;
	}
	/**
	 * ��ȡ��ǰ·����ʹ�õ�ǰ·���µ�adb
	 * @return
	 */
	final public String getAdbPath(){
		String adbpath = System.getProperty("user.dir")+"\\adb.exe";
		return adbpath;
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		AdbUtil adbUtil=new AdbUtil();
//		for(String s:adbUtil.getListByADB("adb devices")){
//			System.out.println(s+"6666");
//		}
//	}

}
