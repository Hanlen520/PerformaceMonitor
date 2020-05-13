package com.test.util;

import com.test.log.LogDemo;
import com.test.perfordata.DeviceAndPack;
import org.apache.log4j.Logger;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
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
	private String deviceid=DeviceAndPack.deivceid;
	private String packagename = DeviceAndPack.packagename;

	/***
	 * ��ȡָ���豸��ָ��Ӧ�õ�cpu˲ʱռ�����
	 * @return
	 * adb -s Q5S5T19529000632 shell dumpsys cpuinfo|findstr com.jingdong.app.mall
	 */
	public double getAPPCPU(){
		double cpu=0;
		List<String> result = adbUtil.getListByADB("adb -s "+ deviceid +" shell top -o ARGS -o %CPU -d 0.5 -n 1|findstr "+packagename);
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
		List<String> result = adbUtil.getListByADB("adb -s "+deviceid+" shell dumpsys meminfo -s "+packagename);
		//���ܻ�ȡ���Ľ��Ϊ�գ�����̲�����
		if (result.size()!=0&&result!=null){
			for (String ss:result){
				if (ss.contains("TOTAL")){
					mem = Integer.parseInt(ss.split("\\s+")[1]);
					System.out.println(mem);
				}
			}
		}
		return mem;
	}
	public void runMonkey(boolean isIgnoreCrashes, boolean isIgnoreTimeouts,int throttle, int count) throws IOException {
		if (deviceid!=null&&packagename!=null) {
			String monkeyCommand = "adb -s " + deviceid + " shell monkey -p " + packagename + " -v -v --throttle "+throttle+(isIgnoreCrashes?" --ignore-crashes ":" ")+(isIgnoreTimeouts?" --ignore-timeouts ":" ")+count;
			logger.info(monkeyCommand);
			BufferedReader br = new CmdTool().getBRByCmd(monkeyCommand);
			while (br.readLine() != null) {
				logger.info(br.readLine());
			}
		}else {
			logger.info("��ѡ���豸�������������");
		}
	}
	/**
	 * ���apk����
	 */
	public void clearAPK(){
		String command = "adb -s "+deviceid+" shell pm clear "+packagename;
		logger.info("adb command:"+command);
		adbUtil.runADB(command);
//		runADB("adb -s "+DeviceAndPack.deivceid+" shell pm clear "+packagename);
	}
	public void uninstallAPK(){
		String command = "adb -s "+DeviceAndPack.deivceid+" uninstall "+packagename;
		logger.info("adb command:"+command);
		adbUtil.runADB(command);
//		runADB("adb -s "+DeviceAndPack.deivceid+" uninstall "+packagename);
	}

//	public static void main(String[] args) {
//		AppInfo appInfo=new AppInfo("com.jingdong.app.mall", "Q5S5T19529000632");
//		int cpuValue = appInfo.getAPPCPU();
//		System.out.println(cpuValue);
//	}
}
