package com.test.main;

import java.util.List;

import com.test.perfordata.MonitorData;
import com.test.perforentity.CPUInfo;

/***
 * �������ݴ���
 * @author xusai
 *
 */
public class DealData {
	
	/**
	 * �����̣߳������߳���30s�������߳�
	 * ֻ���߳̽�������ܻ��������Ϣ��û��ʵʱ�ԣ���Ҫ�ﵽ�߻�ȡ���������ʽ��Ŀǰ��sleep��Ϊ�������߳���ͣ��������ӵĻ����߳̽��������߳�Ҳ�ͻ�ȡ�����ˣ����漶Ӧ�����̲߳������
	 * push
	 * @param args
	 */
	public static void main(String[] args) {
		String packName="com.jingdong.app.mall";
		String device="Q5S5T19529000632";

		com.test.main.StartMonitor monitor=new com.test.main.StartMonitor(packName,device);
		Thread t1=new Thread(monitor);
		t1.start();
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.stop();
		// TODO Auto-generated method stub
		//��ӡ��ȡ����cpu����
//		List<CPUInfo> cpuInfos=MonitorData.getCPUinfolist();
//		System.out.println(cpuInfos.size()+"cpuuuuuuuuuuuuuuu");
//		for(CPUInfo info:cpuInfos){
//			System.out.println(info.cpuValue());
//		}
	}

}
