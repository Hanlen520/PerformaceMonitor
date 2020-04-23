package com.test.util;

import java.util.List;

/***
 * ��ȡָ��Ӧ�õ�����ռ������
 * @author xusai
 *
 */
public class AppInfo {
	private String packName;
	private String device;

	public AppInfo(String packName,String device) {
		this.packName=packName;
		this.device=device;
	}
	/***
	 * ��ȡָ���豸��ָ��Ӧ�õ�cpu˲ʱռ�����
	 * @return
	 * adb -s Q5S5T19529000632 shell dumpsys cpuinfo|findstr com.jingdong.app.mall
	 */
	public double getAPPCPU(){
		double cpu=0;
		AdbUtil adbUtil = new AdbUtil();
		List<String> result = adbUtil.getListByADB("adb -s "+device+" shell dumpsys cpuinfo|findstr "+packName);
		//�����пգ����ܻ�ȡ���Ľ��Ϊ��
		if (result.size()!=0&&result!=null){
			if (!result.get(0).equals("")&&result.get(0)!=null){
				String cpuValueString = result.get(0);//ȡ����һ��13% 16464/com.jingdong.app.mall: 9.2% user + 4% kernel / faults: 10176 minor 15 major
				//���н���������%�ָ�
				cpu = Double.valueOf(cpuValueString.split("%")[0]);//�õ�13%��תΪint����
			}
		}
		System.out.println("cpu:"+cpu);
		return cpu;
	}
	/***
	 * ��ȡָ��Ӧ�õ�men˲ʱռ�����
	 * @return
	 */
	public static int getAPPMem(){
		int mem=0;
		
		return mem;
	}

//	public static void main(String[] args) {
//		AppInfo appInfo=new AppInfo("com.jingdong.app.mall", "Q5S5T19529000632");
//		int cpuValue = appInfo.getAPPCPU();
//		System.out.println(cpuValue);
//	}
}
