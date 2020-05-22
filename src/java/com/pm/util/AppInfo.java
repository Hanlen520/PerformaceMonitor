package com.pm.util;

import com.pm.log.LogDemo;
import com.pm.perfordata.DeviceAndPack;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/***
 * ��ȡָ��Ӧ�õ�����ռ������
 * @author xusai
 *
 */
public class AppInfo {
	private final static Logger logger = Logger.getLogger(LogDemo.class);
	private AdbUtil adbUtil=new AdbUtil();
	//���ӵ���ģʽ�����ٶ����ʼ��
	private static AppInfo appInfo = new AppInfo();
	public static AppInfo getAppInfo() {
		return appInfo;
	}
	/***
	 * ��ȡָ���豸��ָ��Ӧ�õ�cpu˲ʱռ�����
	 * @return
	 * adb -s Q5S5T19529000632 shell dumpsys cpuinfo|findstr com.jingdong.app.mall
	 */
	public double getAPPCPU(){
		double cpu=0;
		List<String> result = adbUtil.getListByADB("adb -s "+ DeviceAndPack.deivceid +" shell top -o ARGS -o %CPU -d 0.5 -n 1|findstr "+DeviceAndPack.packagename);
		//�����пգ����ܻ�ȡ���Ľ��Ϊ��
		if (result.size()!=0&&result!=null){
			if (!result.get(0).equals("")&&result.get(0)!=null){
				String cpuValueString = result.get(0);//ȡ����һ��1mcom.jingdong.app.mall       65.5
				//���н���������%�ָ�
				cpu = Double.valueOf(cpuValueString.split("\\s+")[1]);//�õ�13%��תΪint����
			}
		}
		return cpu;
	}
	/***
	 * ��ȡָ��Ӧ�õ�men˲ʱռ�����
	 * @return
	 */
	public int getAPPMem(){
		int mem=0;
		List<String> result = adbUtil.getListByADB("adb -s "+DeviceAndPack.deivceid+" shell dumpsys meminfo -s "+DeviceAndPack.packagename);
		//���ܻ�ȡ���Ľ��Ϊ�գ�����̲�����
		if (result.size()!=0&&result!=null){
			for (String ss:result){
				if (ss.contains("TOTAL")){
					mem = Integer.parseInt(ss.split("\\s+")[1])/1000;
//					System.out.println(mem);
				}
			}
		}
		return mem;
	}
	public void runMonkey(boolean isIgnoreCrashes, boolean isIgnoreTimeouts,int throttle, int count) throws IOException {
		if (DeviceAndPack.deivceid!=null&&DeviceAndPack.packagename!=null) {
			String monkeyCommand = "adb -s " + DeviceAndPack.deivceid + " shell monkey -p " + DeviceAndPack.packagename + " -v -v --throttle "+throttle+(isIgnoreCrashes?" --ignore-crashes ":" ")+(isIgnoreTimeouts?" --ignore-timeouts ":" ")+count;
			logger.info(monkeyCommand);
			adbUtil.runADBNoRequest(monkeyCommand);
//			BufferedReader br = new CmdTool().getBRByCmd(monkeyCommand);
//			while (br.readLine() != null) {
//				logger.info(br.readLine());
//			}
		}else {
			logger.info("��ѡ���豸�������������");
		}
	}
	/**
	 * ���apk����
	 */
	public void clearAPK(){
		String command = "adb -s "+DeviceAndPack.deivceid+" shell pm clear "+DeviceAndPack.packagename;
		logger.info("adb command:"+command);
		adbUtil.runADBNoRequest(command);
//		runADB("adb -s "+DeviceAndPack.deivceid+" shell pm clear "+packagename);
	}
	public void stopAPP(){
		String command = "adb -s "+DeviceAndPack.deivceid+" shell am force-stop "+DeviceAndPack.packagename;
		logger.info("adb command:"+command);
		adbUtil.runADBNoRequest(command);
	}
	public void uninstallAPK(){
		String command = "adb -s "+DeviceAndPack.deivceid+" uninstall "+DeviceAndPack.packagename;
		logger.info("adb command:"+command);
		adbUtil.runADBNoRequest(command);
//		runADB("adb -s "+DeviceAndPack.deivceid+" uninstall "+packagename);
	}

//	public static void main(String[] args) {
//		AppInfo appInfo=new AppInfo("com.jingdong.app.mall", "Q5S5T19529000632");
//		int cpuValue = appInfo.getAPPCPU();
//		System.out.println(cpuValue);
//	}
}
