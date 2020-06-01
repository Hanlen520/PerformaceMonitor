package com.pm.util;

import com.pm.log.LogDemo;
import com.pm.perfordata.DeviceAndPack;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class DeviceInfo {
	private final static Logger logger = Logger.getLogger(LogDemo.class);
	private AdbUtil adbUtil = new AdbUtil();
	private CmdTool cmdTool=new CmdTool();
	private static DeviceInfo deviceInfo = new DeviceInfo();
	//���ӵ���ģʽ�����ٶ���ĳ�ʼ��
	public static DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}
	/**
	 * ��ȡ�ֻ�Ʒ��
	 * @return
	 */
	public String getBrand() {
		String brand = "";
		if (DeviceAndPack.deivceid != null && !DeviceAndPack.deivceid.equals("") ) {
			List<String> list = cmdTool.getListByCmd("adb -s " + DeviceAndPack.deivceid + " shell getprop ro.product.brand");
			for (String s : list) {
				if (s != null && s != "") {
					brand = s;
					break;
				}
			}
		}
		if (brand.equals("") || brand == null) {
			brand = "null";
		}
		return brand;
	}
	/***
	 * ��ȡ�豸�Ĳ�Ʒ�ͺ�
	 * @return
	 */
	public String getModel(){
		String model="";
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			List<String> list=cmdTool.getListByCmd("adb -s "+DeviceAndPack.deivceid+" shell getprop ro.product.model");
			for(String s:list){
				if (s!=null&&s!="") {
					model=s;
					break;
				}
			}
		}
		if (model.equals("")||model==null) {
			model="null";
		}
		return model;
	}
	/***
	 * ��ȡ�ֻ��ֱ���
	 * @return
	 */
	public String getDp(){
		String dp="";
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			List<String> list=cmdTool.getListByCmd("adb -s "+DeviceAndPack.deivceid+" shell dumpsys window|findstr init");
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
			dp="null";
		}
		return dp;
	}
	/***
	 * ��ȡ�ֻ�ϵͳ�汾
	 * @return
	 */
	public String getOsVersionCode(){
		String versionCode="";
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			List<String> list=cmdTool.getListByCmd("adb -s "+DeviceAndPack.deivceid+" shell getprop ro.build.version.release");
			for(String s:list){
				if (s!=null&&s!="") {
					versionCode=s;
					break;
				}
			}
		}
		if (versionCode.equals("")||versionCode==null) {
			versionCode="null";
		}
		return versionCode;
	}
	/***
	 * ��ȡ�ֻ�imei
	 * @return
	 */
	public String getIMEI(){
		String imei="";
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			List<String> list=cmdTool.getListByCmd("adb -s "+DeviceAndPack.deivceid+" shell dumpsys iphonesubinfo");
			for(String s:list){
				if(s!=null&&s!=""){
					if (s.contains("Device")) {
						String[] ime=s.split("=");
						imei=ime[1];
						break;
					}
				}
			}

		}
		if (imei.equals("")||imei==null) {
			imei="null";
		}
		return imei;
	}
	/**
	 * ��װapk
	 * @param path
	 */
	public void installAPK(String path){
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			String command = "adb -s " + DeviceAndPack.deivceid + " install -r " + path;
			logger.info("adb command:" + command);
			adbUtil.runADBNoRequest(command);
//		runADB("adb -s "+ DeviceAndPack.deivceid+" install -r "+path);
		}
	}
	public void clearLogcat(){
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			String command = "adb -s " + DeviceAndPack.deivceid + " shell logcat -c";
			logger.info("adb command:" + command);
			adbUtil.runADBNoRequest(command);
		}
	}
	public String getCurrentPack(){
		String pac="";
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			String command = "adb -s "+ DeviceAndPack.deivceid + " shell dumpsys window|findstr mCurrentFocus";
			logger.info(command);
			String result = adbUtil.getStringByADB(command);
			logger.info(result);
			//mCurrentFocus=Window{65019b9 u0 com.huawei.android.launcher/com.huawei.android.launcher.unihome.UniHomeLauncher}
			if (result!=null && !result.equals("")){
				String pack = result.trim().split("\\s+")[2];
				if (!pack.contains("StatusBar")){
					pac = pack.split("\\/")[0];
				}else {
					logger.info("����������");
				}
			}

		}
		return pac;
	}
	/**
	 * ��ȡ�豸�Ѱ�װapp
	 * @return
	 */
	public String[] getAllPack(){
		String[] packages = null;
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
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

	/**
	 * ��ͼ���裺adbִ�н�ͼ�����ֻ����pull�����ԣ�ɾ���ֻ���ͼƬ
	 */
	public void screenShot() {
		if (DeviceAndPack.deivceid!=null && !DeviceAndPack.deivceid.equals("")) {
			long screenName = System.currentTimeMillis();
			String screenCommand = "adb -s "+DeviceAndPack.deivceid+" shell screencap -p /sdcard/"+String.valueOf(screenName)+".png";
			logger.info(screenCommand);
			logger.info(adbUtil.getStringByADB(screenCommand));
			adbUtil.runADBNoRequest("adb -s "+DeviceAndPack.deivceid+" pull /sdcard/"+String.valueOf(screenName)+".png "+ ResourceBundle.getBundle("config").getString("screenShotPath"));
			adbUtil.runADBNoRequest("adb -s "+DeviceAndPack.deivceid+" shell rm /sdcard/"+String.valueOf(screenName)+".png");
		}
	}
}
