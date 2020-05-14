package com.test.page;

import com.test.log.LogDemo;
import com.test.perfordata.DeviceAndPack;
import com.test.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 20200427:��ʼ�����
 */
public class MainActivity {
    private final static Logger logger = Logger.getLogger(LogDemo.class);

    public static void main(String[] args) {
        JPanel deviceInfoPanel=new DeviceInfoPanel().getDeviceInfoPanel();

        JFrame jFrame = new DropTargetFrame();
        jFrame.setTitle("PerformanceMonitor--by sai");
        jFrame.setSize(1500, 1000);
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("closing����");
                System.exit(0);
            }
        });
        JPanel devicePackPanel = new DevicePackPanel().getDevicePackPanel();

        jFrame.add(devicePackPanel);
        jFrame.add(deviceInfoPanel);
        //���monkeyִ��panel
        JPanel monkeyJPanel = new MonkeyPanel().getMonkeyPanel();
        jFrame.add(monkeyJPanel);

        //�����־��ӡ��������ʾlog��Ϣ
        JPanel logJPanel = new JPanel();
        JTextArea logta = new JTextArea();
        JScrollPane logsp = new JScrollPane(logta);
        logta.setColumns(80);
        logta.setRows(30);
        logsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        LogDemo logDemoFrame = new LogDemo(logta, logsp);
        logDemoFrame.initLog();
        logJPanel.add(logsp);
        jFrame.add(logJPanel);

        jFrame.setVisible(true);

        //�����ǰ��������ܼ������ͼ��ʹ�ö��߳���ץ���������ƺ��ٴ˲����ã���Ϊ��Ҫʵʱ���ݣ���˲ʱ����ֱ�Ӹ���ǰ������ʾ���������ռ�һ��list��return
//        StartMonitor monitor=new StartMonitor(DeviceAndPack.packagename,DeviceAndPack.deivceid);
//        Thread t1=new Thread(monitor);
//        t1.start();
    }
}
