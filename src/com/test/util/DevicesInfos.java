package com.test.util;

import java.util.ArrayList;
import java.util.List;

public class DevicesInfos {

	/**
	 * ����������ӵ��豸id
	 * @return �����豸id������
	 */
	public List<String> getDevicesID(){
		List<String> deviceList=new ArrayList<String>();
		CmdTool cmd=new CmdTool();
		List<String> resultList=cmd.getListByCmd("adb devices");
		for(int i=1;i<resultList.size()-1;i++){
			if (resultList.get(i)!=null&&resultList.get(i)!="") {
				String[] result=resultList.get(i).split("\t");
				if (result[1].equals("device")) {
					String device=result[0];
					deviceList.add(device);
				}else if (result[1]=="offline") {
					System.out.println(result[0]+"�豸���ߣ������²�Σ�����");
				}else {
					System.out.println("�豸���������⣬�Լ����ɣ�����");
				}
			}
			else {
				System.out.println("û���豸������");
			}
		}
		return deviceList;
	}
}
