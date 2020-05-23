package com.pm.page;

import com.pm.log.LogDemo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.pm.main.StartMonitor;
import org.apache.log4j.Logger;

/**
 * 20200427:��ʼ�����
 */
public class MainActivity {

    public static void main(String[] args) {
        GraphicsDevice graphDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode disMode = graphDevice.getDisplayMode();
        int width = disMode.getWidth();
        int height = disMode.getHeight();
        JFrame jFrame = new DropTargetFrame();
        jFrame.setTitle("PerformanceMonitor--by sai");

//        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setLayout(null);
        jFrame.setSize(width, height);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //�����豸��������豸��Ϣ
        JPanel topPanle = new JPanel();
        topPanle.setLocation(0, 0);
        topPanle.setSize((int)width, 70);
        topPanle.setBackground(Color.YELLOW);
        topPanle.setLayout(null);
        JPanel devicePackPanel = new DevicePackPanel().getDevicePackPanel();
        devicePackPanel.setSize((int) (width*0.7),70);
        topPanle.add(devicePackPanel);
        JPanel deviceInfoPanel = new DeviceInfoPanel().getDeviceInfoPanel();
        deviceInfoPanel.setLocation((int) (width*0.7), 0);
        deviceInfoPanel.setSize((int) (width*0.3), 70);
        topPanle.add(deviceInfoPanel);
        jFrame.add(topPanle);
        //���monkey��log
        JPanel westPanel = new JPanel();
        westPanel.setLocation(0, 70);
        westPanel.setSize((int) (width*0.45), height-70);
        westPanel.setLayout(null);
        JPanel monkeyPanel = new MonkeyPanel().getMonkeyPanel();
        monkeyPanel.setLocation(0, 0);
        monkeyPanel.setSize((int) (width*0.45), 70);
        JPanel logPanel = new LogPanel().getLogPanel();
        logPanel.setLocation(0,70);
        logPanel.setSize((int) (width*0.45), height-70-70-50);
        westPanel.add(monkeyPanel);
        westPanel.add(logPanel);
        jFrame.add(westPanel);
        //������ܼ������ͼ
        StartMonitor startMonitor = new StartMonitor(jFrame);
        startMonitor.run();

        jFrame.setVisible(true);
    }
}
