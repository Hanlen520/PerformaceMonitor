package com.test.util;

import com.test.perfordata.DeviceAndPack;

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
			if (s!=null&&!s.equals("")) {//ȥ�����У��е��ֻ��Ὣ���з���
				if (s.contains("daemon")) {
					continue;
				} else {
					adblist.add(s.trim());
				}
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

	/**
	 * ִ��adb�������������
	 * @param zhiling
	 */
	public void runADB(String zhiling){
		cmdUtil.getListByCmd(zhiling);
	}

	/**
	 * ���apk����
	 * @param packagename
	 */
	public void clearAPK(String packagename){
		runADB("adb -s "+DeviceAndPack.deivceid+" shell pm clear "+packagename);
	}
	/**
	 * ��װapk
	 * @param path
	 */
	public void installAPK(String path){
		runADB("adb -s "+ DeviceAndPack.deivceid+" install -r "+path);
	}
	public void uninstallAPK(String packagename){runADB("adb -s "+DeviceAndPack.deivceid+" uninstall "+packagename);}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		AdbUtil adbUtil=new AdbUtil();
//		for(String s:adbUtil.getListByADB("adb devices")){
//			System.out.println(s+"6666");
//		}
//	}

}
