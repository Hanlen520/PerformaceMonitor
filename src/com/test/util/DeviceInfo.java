package com.test.util;

import java.util.List;

public class DeviceInfo {
	private AdbUtil adbUtil = new AdbUtil();
	/***
	 * ��ȡ�豸�Ĳ�Ʒ�ͺ�
	 * @param deviceId
	 * @return
	 */
	public String getOs(String deviceId){
		String os="";
		if (deviceId!=null&&deviceId!="") {
			
			CmdTool cmd=new CmdTool();
			List<String> list=cmd.getListByCmd(adbUtil.getAdbPath()+" -s "+deviceId+" shell getprop ro.product.model");
			for(String s:list){
				if (s!=null&&s!="") {
					if (s.contains("=")) {
						String[] osSplit=s.split("=");
						os=osSplit[1];
						break;
					}
				}
			}
		}
		if (os.equals("")||os==null) {
			os="sorry��û��ȡ������";
		}
		return os;
	}
}
