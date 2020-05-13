package com.test.util;

import com.test.log.LogDemo;
import com.test.perfordata.DeviceAndPack;
import org.apache.log4j.Logger;

import java.util.List;

public class DeviceInfo {
	private final static Logger logger = Logger.getLogger(LogDemo.class);
	private AdbUtil adbUtil = new AdbUtil();
	private CmdTool cmdTool=new CmdTool();
	private String deviceId= DeviceAndPack.deivceid;

	/**
	 * ��ȡ�ֻ�Ʒ��
	 * @return
	 */
	public String getBrand() {
		String brand = "";
		if (deviceId != null && !deviceId.equals("") ) {
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
		if (deviceId!=null && !deviceId.equals("")) {
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
		if (deviceId!=null && !deviceId.equals("")) {
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
		if (deviceId!=null && !deviceId.equals("")) {
			System.out.println("ossssssss");
			List<String> list=cmdTool.getListByCmd("adb -s "+deviceId+" shell getprop ro.build.version.release");
			for(String s:list){
				if (s!=null&&s!="") {
					versionCode=s;
					System.out.println("sssssssssssss:"+s);
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
		if (deviceId!=null && !deviceId.equals("")) {
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
	/**
	 * ��װapk
	 * @param path
	 */
	public void installAPK(String path){
		if (deviceId!=null && !deviceId.equals("")) {
			String command = "adb -s " + deviceId + " install -r " + path;
			logger.info("adb command:" + command);
			adbUtil.runADB(command);
//		runADB("adb -s "+ DeviceAndPack.deivceid+" install -r "+path);
		}
	}
	public void clearLogcat(){
		if (deviceId!=null && !deviceId.equals("")) {
			String command = "adb -s " + deviceId + " shell logcat -c";
			logger.info("adb command:" + command);
			adbUtil.runADB(command);
		}
	}
	/**
	 * ��ȡ�豸�Ѱ�װapp
	 * @return
	 */
	public String[] getAllPack(){
		String[] packages = null;
		if (deviceId!=null && !deviceId.equals("")) {
			String command="adb -s "+ DeviceAndPack.deivceid +" shell pm list package";
			List<String> result= adbUtil.getListByADB(command);
			packages=new String[result.size()];
			for (int i=0; i<result.size(); i++){
				if (result.get(i)!=null&&!result.get(i).equals("")) {
					packages[i] = result.get(i).split(":")[1];
				}
			}
		}else {
			logger.info("�豸�����⡭��");
		}
		return packages;
	}
	public void screenShot(){
		if (deviceId!=null && !deviceId.equals("")) {
			
		}
	}
}
