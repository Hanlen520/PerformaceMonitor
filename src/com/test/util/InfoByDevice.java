package com.test.util;

import java.util.List;

public class InfoByDevice {
    private String device;
    private AdbUtil adbUtil=new AdbUtil();
    public InfoByDevice(String device){
        this.device = device;
    }
    /**
     * ��ȡ�豸�Ѱ�װapp
     * @return
     */
    public String[] getAllPack(){
        List<String> result= adbUtil.getListByADB("adb -s "+device+" shell pm list package");
        String[] packages=new String[result.size()];
        for (int i=0; i<result.size(); i++){
            packages[i] = result.get(i).split(":")[1];
        }
        return packages;
    }
}
