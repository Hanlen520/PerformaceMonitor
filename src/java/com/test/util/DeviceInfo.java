package com.test.util;

import com.test.perfordata.DeviceAndPack;

import java.util.List;

public class DeviceInfo {
	private AdbUtil adbUtil = new AdbUtil();
	private CmdTool cmdTool=new CmdTool();
	private String deviceId= DeviceAndPack.deivceid;

	/**
	 * ��ȡ�ֻ�Ʒ��
	 * @return
	 */
	public String getBrand() {
		String brand = "";
		if (deviceId != null && deviceId != "") {
			List<String> list = cmdTool.getListByCmd("adb -s " + deviceId + " shell getprop ro.product.brand");
			for (String s : list) {
				if (s != null && s != "") {
					brand = s;
					break;
				}
			}
		}
		if (brand.equals("") || brand == null) {
			brand = "sorry��û��ȡ������";
		}
		return brand;
	}
	/***
	 * ��ȡ�豸�Ĳ�Ʒ�ͺ�
	 * @return
	 */
	public String getModel(){
		String model="";
		if (deviceId!=null&&deviceId!="") {
			List<String> list=cmdTool.getListByCmd("adb -s "+deviceId+" shell getprop ro.product.model");
			for(String s:list){
				if (s!=null&&s!="") {
					model=s;
					break;

				}
			}
		}
		if (model.equals("")||model==null) {
			model="sorry��û��ȡ������";
		}
		return model;
	}
	/***
	 * ��ȡ�ֻ��ֱ���
	 * @return
	 */
	public String getDp(){
		String dp="";
		if (deviceId!=null&&deviceId!="") {
			List<String> list=cmdTool.getListByCmd("adb -s "+deviceId+" shell dumpsys window|findstr init");
			for(String s:list){
				if(s!=null&&s!=""){
					if (s.contains("init")) {
						String[] split=s.split(" ");
						for (int i = 0; i < split.length; i++) {
							if (split[i].contains("init")) {
								String[] init=split[i].split("=");
								dp=init[1];
								break;
							}
						}
						break;
					}
				}
			}
		}
		if (dp.equals("")||dp==null) {
			dp="sorry��û��ȡ������";
		}
		return dp;
	}
	/***
	 * ��ȡ�ֻ�ϵͳ�汾
	 * @return
	 */
	public String getOsVersionCode(){
		String versionCode="";
		if (deviceId!=null&&deviceId!="") {
			List<String> list=cmdTool.getListByCmd(adbUtil.getAdbPath()+" -s "+deviceId+" shell getprop ro.build.version.release");
			for(String s:list){
				if (s!=null&&s!="") {
					versionCode=s;
					break;
				}
			}
			if (versionCode.equals("")||versionCode==null) {
				versionCode="sorry��û��ȡ������";
			}
		}
		return versionCode;
	}
	/***
	 * ��ȡ�ֻ�imei
	 * @return
	 */
	public String getIMEI(){
		String imei="";
		if (deviceId!=null&&deviceId!="") {
			List<String> list=cmdTool.getListByCmd("adb -s "+deviceId+" shell dumpsys iphonesubinfo");
			for(String s:list){
				if(s!=null&&s!=""){
					if (s.contains("Device")) {
						String[] ime=s.split("=");
						imei=ime[1];
						break;
					}
				}
			}
			if (imei.equals("")||imei==null) {
				imei="sorry��û��ȡ������";
			}
		}
		return imei;
	}
}
