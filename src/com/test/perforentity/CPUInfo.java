package com.test.perforentity;
/***
 * ����cpu˲ʱ��ʱ���ֵ
 * @author xusaisai
 *
 */
public class CPUInfo {
	private String time;
	private int cpuValue;
	
	public CPUInfo(String time,int cpuValue) {
		// TODO Auto-generated constructor stub
		this.time=time;
		this.cpuValue=cpuValue;
	}
	
	public String time(){
		return time;
	}
	public int cpuValue(){
		return cpuValue;
	}
	
}
