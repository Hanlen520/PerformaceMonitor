package com.test.main;

import java.util.Date;
import java.util.List;

import com.test.perfordata.DeviceAndPack;
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
	private boolean running;

	public void run() {
		long time=System.currentTimeMillis();
		running=true;
		//ѭ����ȡ�������ݣ�ֱ���߳�ֹͣ��ÿ�θ���ȡһ��ֵ����ӵ�������
		while (running) {

			//��ȡcpu���洢����
			CPUInfo cpuInfo=new CPUInfo(String.valueOf(time), AppInfo.getAppInfo().getAPPCPU());
			MonitorData.getCPUinfolist().add(cpuInfo);
			//��ȡ�ڴ沢�洢����
			MemInfo memInfo=new MemInfo(String.valueOf(time), AppInfo.getAppInfo().getAPPMem());
			MonitorData.getMeminfolist().add(memInfo);
			//������ʱ
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

