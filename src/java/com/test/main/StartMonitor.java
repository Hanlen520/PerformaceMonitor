package com.test.main;

import java.util.Date;
import java.util.List;

import com.test.perfordata.MonitorData;
import com.test.perforentity.CPUInfo;
import com.test.perforentity.MemInfo;
import com.test.util.AppInfo;
/***
 * ��ʼ���ܼ�����ѭ����ȡ��������
 * @author xusaisai
 *
 */
public class StartMonitor implements Runnable {
	private long sleepTime;
	private boolean running;
	private String packName;
	private String device;
	
	public StartMonitor(String packName,String device){
		this.packName=packName;
		this.device=device;
	}
	
	
	public void run() {
		long time=System.currentTimeMillis();
		running=true;
		//ѭ����ȡ�������ݣ�ֱ���߳�ֹͣ��ÿ�θ���ȡһ��ֵ����ӵ�������
		while (running) {

			AppInfo appInfo=new AppInfo(packName, device);
			//��ȡcpu���洢����
			CPUInfo cpuInfo=new CPUInfo(String.valueOf(time), appInfo.getAPPCPU());
			MonitorData.getCPUinfolist().add(cpuInfo);
			//��ȡ�ڴ沢�洢����
			MemInfo memInfo=new MemInfo(String.valueOf(time), appInfo.getAPPMem());
			MonitorData.getMeminfolist().add(memInfo);
			//������ʱ
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

