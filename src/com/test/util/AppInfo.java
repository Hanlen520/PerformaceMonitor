package com.test.util;
/***
 * ��ȡָ��Ӧ�õ�����ռ������
 * @author xusaisai
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
	 * @param packName ����
	 * @param device �豸udid
	 * @return
	 */
	public static int getAPPCPU(){
		int cpu=0;
		
		System.out.println();
		return cpu;
	}
	public static int getAPPMem(){
		int mem=0;
		
		return mem;
	}
}
