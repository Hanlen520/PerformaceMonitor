package com.pm.perfordata;

import java.util.ArrayList;
import java.util.List;

import com.pm.perforentity.CPUInfo;
import com.pm.perforentity.MemInfo;

public class MonitorData {
	/***
	 * �洢cpu���ݵļ���
	 * @return
	 */
	public static List<CPUInfo> cpuInfos = new ArrayList<CPUInfo>();
	public static List<MemInfo> memInfos = new ArrayList<MemInfo>();

	public static List<CPUInfo> getCPUinfolist(){
		return cpuInfos;
	}
	/***
	 * �洢mem���ݵļ���
	 * @return
	 */
	public static List<MemInfo> getMeminfolist(){
		return memInfos;
	}
	
}
