package com.test.util;
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
	 */
	public static int getAPPCPU(){
		int cpu=0;

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

	public static void main(String[] args) {
		int cpuValue = AppInfo.getAPPCPU();
		System.out.println(cpuValue);
	}
}
