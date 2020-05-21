package com.test.page;

import com.test.log.LogDemo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.test.main.StartMonitor;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;

/**
 * 20200427:��ʼ�����
 */
public class MainActivity {
    private final static Logger logger = Logger.getLogger(LogDemo.class);

    public static void main(String[] args) {
        JFrame jFrame = new DropTargetFrame();
        jFrame.setTitle("PerformanceMonitor--by sai");
        jFrame.setPreferredSize(new Dimension(1500, 1000));
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("closing����");
                System.exit(0);
            }
        });
        JPanel topPanle = new JPanel();
        topPanle.add(new DevicePackPanel().getDevicePackPanel());
        topPanle.add(new DeviceInfoPanel().getDeviceInfoPanel());
        jFrame.add(topPanle);
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());
        westPanel.add(new MonkeyPanel().getMonkeyPanel(), BorderLayout.NORTH);
        westPanel.add(new LogPanel().getLogPanel(), BorderLayout.SOUTH);
        jFrame.add(westPanel);
//        jFrame.add(new PerforPanel().getPerforPanel(), BorderLayout.NORTH);

        StartMonitor startMonitor = new StartMonitor(jFrame);
        startMonitor.run();

        jFrame.setVisible(true);

        //�����ǰ��������ܼ������ͼ��ʹ�ö��߳���ץ���������ƺ��ٴ˲����ã���Ϊ��Ҫʵʱ���ݣ���˲ʱ����ֱ�Ӹ���ǰ������ʾ���������ռ�һ��list��return
//        StartMonitor monitor=new StartMonitor(DeviceAndPack.packagename,DeviceAndPack.deivceid);
//        Thread t1=new Thread(monitor);
//        t1.start();
    }
}
