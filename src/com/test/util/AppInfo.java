package com.test.util;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.List;

/***
 * ��ȡָ��Ӧ�õ�����ռ������
 * @author xusai
 *
 */
public class AppInfo {
	private String packName;
	private String device;
	private AdbUtil adbUtil=new AdbUtil();

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
		List<String> result = adbUtil.getListByADB("adb -s "+device+" shell top -o ARGS -o %CPU -d 0.5 -n 1|findstr "+packName);
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
		List<String> result = adbUtil.getListByADB("adb -s "+device+" shell dumpsys meminfo -s "+packName);
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

//	public static void main(String[] args) {
//		AppInfo appInfo=new AppInfo("com.jingdong.app.mall", "Q5S5T19529000632");
//		int cpuValue = appInfo.getAPPCPU();
//		System.out.println(cpuValue);
//	}
}
