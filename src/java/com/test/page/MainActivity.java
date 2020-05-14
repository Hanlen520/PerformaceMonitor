package com.test.page;

import com.test.log.LogDemo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.apache.log4j.Logger;

/**
 * 20200427:��ʼ�����
 */
public class MainActivity {
    private final static Logger logger = Logger.getLogger(LogDemo.class);

    public static void main(String[] args) {
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
        
        jFrame.add(new DevicePackPanel().getDevicePackPanel());
        jFrame.add(new DeviceInfoPanel().getDeviceInfoPanel());
        jFrame.add(new MonkeyPanel().getMonkeyPanel());
        jFrame.add(new LogPanel().getLogPanel());

        jFrame.setVisible(true);

        //�����ǰ��������ܼ������ͼ��ʹ�ö��߳���ץ���������ƺ��ٴ˲����ã���Ϊ��Ҫʵʱ���ݣ���˲ʱ����ֱ�Ӹ���ǰ������ʾ���������ռ�һ��list��return
//        StartMonitor monitor=new StartMonitor(DeviceAndPack.packagename,DeviceAndPack.deivceid);
//        Thread t1=new Thread(monitor);
//        t1.start();
    }
}
