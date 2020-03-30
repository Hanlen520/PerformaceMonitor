package com.test.main;

import java.util.List;

import com.test.perfordata.MonitorData;
import com.test.perforentity.CPUInfo;

/***
 * �������ݴ���
 * @author xusaisai
 *
 */
public class DealData {
	
	/**
	 * �����̣߳������߳���30s�������߳�
	 * ����ɶ���������ˡ���
	 * @param args
	 */
	public static void main(String[] args) {
		String packName="";
		String device="";

		StartMonitor monitor=new StartMonitor(packName,device);
		Thread t1=new Thread(monitor);
		t1.start();
		try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.stop();
		// TODO Auto-generated method stub
		//��ӡ��ȡ����cpu����
		List<CPUInfo> cpuInfos=MonitorData.getCPUinfolist();
		for(CPUInfo info:cpuInfos){
			System.out.println(info.cpuValue());
		}
	}

}
