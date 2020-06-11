package com.pm.main;

import java.awt.*;
import java.util.ResourceBundle;

import com.pm.page.ChartPanel;
import com.pm.perfordata.DeviceAndPack;
import com.pm.util.AppInfo;
import com.pm.util.ExcelDeal;

import javax.swing.*;

/***
 * ��ʼ���ܼ�����ѭ����ȡ��������
 * @author xusaisai
 *
 */
public class StartMonitor implements Runnable {
	private static int hang;
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
	private ExcelDeal excelDeal;
	public StartMonitor(JFrame jFrame,int x, int y, int width, int height){
		excelDeal = new ExcelDeal(ResourceBundle.getBundle("config").getString("performancePath"));
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
			double cpu = AppInfo.getAppInfo().getAPPCPU();
			int mem = AppInfo.getAppInfo().getAPPMem();
			double fps = AppInfo.getAppInfo().getAPPCPU();
			int data = AppInfo.getAppInfo().getData();
			if (flag){
				cpuJPanel = cpuChart.getPanel(cpu);
				memJPanel = memChart.getPanel(mem);
				fpsJPanel = fpsChart.getPanel(fps);
				dataJPanel = dataChart.getPanel(data);
				perJPanel.add(cpuJPanel);
				perJPanel.add(memJPanel);
				perJPanel.add(fpsJPanel);
				perJPanel.add(dataJPanel);
				jFrame.add(perJPanel);
				jFrame.setVisible(true);
				flag = false;
			}else{
				cpuChart.rePaint(cpu);
				memChart.rePaint(mem);
				fpsChart.rePaint(fps);
				dataChart.rePaint(data);
				//�ж��Ƿ���Ҫд��excel
				if (DeviceAndPack.isWriteExcel){
					writeExcel(hang, cpu, mem, fps, data);
					hang++;
				}
			}
			try {
				Thread.sleep(Long.parseLong(ResourceBundle.getBundle("config").getString("monitorTime")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void writeExcel(int hang, double cpu, int mem, double fps, int data){
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
	public static void setHang(){
		hang = 0;
	}
}

