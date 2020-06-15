package com.pm.main;

import com.pm.util.ExcelDeal;

import java.util.ResourceBundle;

/***
 * �������ݴ���
 * @author xusai
 *
 */
public class DealData {
	private static DealData dealData= new DealData();
	public static DealData getInstance(){
		return dealData;
	}
	private ExcelDeal excelDeal=new ExcelDeal(ResourceBundle.getBundle("config").getString("performancePath"));
	public void writeExcel(int hang, double cpu, int mem, double fps, int data){
		if (excelDeal.isExitFile()){
			write(hang,cpu,mem,fps,data);
		}else {
			try {
				excelDeal.createXlsx();
				write(hang,cpu,mem,fps,data);
			}catch (Exception e){}
		}
	}
	private void write(int hang,double cpu, int mem, double fps, int data){
		String time = excelDeal.getStringDate();
		try {
			excelDeal.writeToXLSX(hang, 0, time);
			excelDeal.writeToXLSX(hang, 1, cpu);
			excelDeal.writeToXLSX(hang, 2, mem);
			excelDeal.writeToXLSX(hang, 3, fps);
			excelDeal.writeToXLSX(hang, 4, data);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * �����̣߳������߳���30s�������߳�
	 * ֻ���߳̽�������ܻ��������Ϣ��û��ʵʱ�ԣ���Ҫ�ﵽ�߻�ȡ���������ʽ��Ŀǰ��sleep��Ϊ�������߳���ͣ��������ӵĻ����߳̽��������߳�Ҳ�ͻ�ȡ�����ˣ����漶Ӧ�����̲߳������
	 * push
	 * @param args
	 */
	public static void main(String[] args) {
		String packName="com.jingdong.app.mall";
		String device="Q5S5T19529000632";

//		com.pm.main.StartMonitor monitor=new com.pm.main.StartMonitor();
//		Thread t1=new Thread(monitor);
//		t1.start();
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		t1.stop();
		// TODO Auto-generated method stub
		//��ӡ��ȡ����cpu����
//		List<CPUInfo> cpuInfos=MonitorData.getCPUinfolist();
//		System.out.println(cpuInfos.size()+"cpuuuuuuuuuuuuuuu");
//		for(CPUInfo info:cpuInfos){
//			System.out.println(info.cpuValue());
//		}
	}

}
