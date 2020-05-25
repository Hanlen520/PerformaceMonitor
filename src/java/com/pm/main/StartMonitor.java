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
	public StartMonitor(JFrame jFrame,int x, int y, int width, int height){
		this.jFrame = jFrame;
		perJPanel = new JPanel();
		perJPanel.setSize(width, height);
		perJPanel.setLocation(x, y);
		perJPanel.setLayout(new GridLayout(2,2));
		cpuChart=new ChartPanel(jFrame,"","cpu(%)",(int)(width*0.5), (int)(height*0.5),100);
		memChart = new ChartPanel(jFrame,"", "memory(mb)",(int)(width*0.5), (int)(height*0.5), 100);
		fpsChart = new ChartPanel(jFrame, "", "fps",(int)(width*0.5), (int)(height*0.5), 100);
		dataChart = new ChartPanel(jFrame, "", "data(kb)",(int)(width*0.5), (int)(height*0.5), 100);
	}

	public void run() {
		boolean flag = true;
		running=true;
		//ѭ����ȡ�������ݣ�ֱ���߳�ֹͣ��ÿ�θ���ȡһ��ֵ����ӵ�������
		while (running) {
			if (flag){
				cpuJPanel = cpuChart.getPanel(AppInfo.getAppInfo().getAPPCPU());
				memJPanel = memChart.getPanel(AppInfo.getAppInfo().getAPPMem());
				fpsJPanel = fpsChart.getPanel(AppInfo.getAppInfo().getAPPCPU());
				dataJPanel = dataChart.getPanel(AppInfo.getAppInfo().getData());
				perJPanel.add(cpuJPanel);
				perJPanel.add(memJPanel);
				perJPanel.add(fpsJPanel);
				perJPanel.add(dataJPanel);
				jFrame.add(perJPanel);
				jFrame.setVisible(true);
				flag = false;
			}else{
				cpuChart.rePaint(AppInfo.getAppInfo().getAPPCPU());
				memChart.rePaint(AppInfo.getAppInfo().getAPPMem());
				fpsChart.rePaint(AppInfo.getAppInfo().getAPPCPU());
				dataChart.rePaint(AppInfo.getAppInfo().getData());
			}
			try {
				Thread.sleep(Long.parseLong(ResourceBundle.getBundle("config").getString("monitorTime")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

