package com.test.perforentity;
/***
 * ����cpu˲ʱ��ʱ���ֵ
 * @author xusaisai
 *
 */
public class CPUInfo {
	private String time;
	private double cpuValue;
	
	public CPUInfo(String time,double cpuValue) {
		// TODO Auto-generated constructor stub
		this.time=time;
		this.cpuValue=cpuValue;
	}
	
	public String time(){
		return time;
	}
	public double cpuValue(){
		return cpuValue;
	}
	
}
