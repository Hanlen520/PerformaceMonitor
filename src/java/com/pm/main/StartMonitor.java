package com.pm.main;

import java.awt.*;
import java.util.ResourceBundle;

import com.pm.page.ChartPanel;
import com.pm.util.AppInfo;

import javax.swing.*;

/***
 * ��ʼ���ܼ�����ѭ����ȡ��������
 * @author xusaisai
 *
 */
public class StartMonitor implements Runnable {
	private boolean running;
	private JFrame jFrame;
	private JPanel perJPanel;
	private ChartPanel cpuChart;
	private ChartPanel memChart;
	private ChartPanel fpsChart;
	private ChartPanel dataChart;
	private JPanel cpuJPanel;
	private JPanel memJPanel;
	private JPanel fpsJPanel;
	private JPanel dataJPanel;
	private int x;
	private int y;
	private int width;
	private int height;
	public StartMonitor(JFrame jFrame,int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.jFrame = jFrame;
		perJPanel = new JPanel();
		perJPanel.setSize(width, height);
		perJPanel.setLocation(x, y);
		perJPanel.setLayout(new GridLayout(2,2));
		cpuChart=new ChartPanel(jFrame,"","cpu",(int)(width*0.5), (int)(height*0.5),100);
		memChart = new ChartPanel(jFrame,"", "memory",(int)(width*0.5), (int)(height*0.5), 100);
		fpsChart = new ChartPanel(jFrame, "", "fps",(int)(width*0.5), (int)(height*0.5), 100);
		dataChart = new ChartPanel(jFrame, "", "data",(int)(width*0.5), (int)(height*0.5), 100);
	}

	public void run() {
		long time=System.currentTimeMillis();
		boolean flag = true;
		running=true;
		//ѭ����ȡ�������ݣ�ֱ���߳�ֹͣ��ÿ�θ���ȡһ��ֵ����ӵ�������
		while (running) {
			if (flag){
				cpuJPanel = cpuChart.getPanel(AppInfo.getAppInfo().getAPPCPU());
				memJPanel = memChart.getPanel(AppInfo.getAppInfo().getAPPMem());
				fpsJPanel = fpsChart.getPanel(AppInfo.getAppInfo().getAPPCPU());
				dataJPanel = dataChart.getPanel(AppInfo.getAppInfo().getAPPMem());
				perJPanel.add(cpuJPanel);
				perJPanel.add(memJPanel);
				perJPanel.add(fpsJPanel);
				perJPanel.add(dataJPanel);
				jFrame.add(perJPanel);
//				jFrame.pack();
				jFrame.setVisible(true);
				flag = false;
			}else{
				cpuChart.rePaint(AppInfo.getAppInfo().getAPPCPU());
				memChart.rePaint(AppInfo.getAppInfo().getAPPMem());
				fpsChart.rePaint(AppInfo.getAppInfo().getAPPCPU());
				dataChart.rePaint(AppInfo.getAppInfo().getAPPMem());
			}
//			//��ȡcpu���洢����
//			CPUInfo cpuInfo=new CPUInfo(String.valueOf(time), AppInfo.getAppInfo().getAPPCPU());
//			MonitorData.getCPUinfolist().add(cpuInfo);
//			//��ȡ�ڴ沢�洢����
//			MemInfo memInfo=new MemInfo(String.valueOf(time), AppInfo.getAppInfo().getAPPMem());
//			MonitorData.getMeminfolist().add(memInfo);
//			double cpu = AppInfo.getAppInfo().getAPPCPU();
//			System.out.println(cpu);
//			cpuChart.xchart(cpu);
//			cpuJPanel = cpuChart.getXchartPanel(cpu);

//			double mem = AppInfo.getAppInfo().getAPPMem();
//			memChart.xchart(mem);
//			memJPanel = memChart.getXchartPanel(cpu);

//			double fps = AppInfo.getAppInfo().getAPPCPU();
//			fpsChart.xchart(fps);
//			fpsJPanel = fpsChart.getXchartPanel(cpu);

//			double data = AppInfo.getAppInfo().getAPPMem();
//			dataChart.xchart(data);

//			jFrame.add(cpuJPanel);
//			jFrame.add(memJPanel);
//			jFrame.add(fpsJPanel);

			//������ʱ
			try {
				Thread.sleep(Long.parseLong(ResourceBundle.getBundle("config").getString("monitorTime")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

