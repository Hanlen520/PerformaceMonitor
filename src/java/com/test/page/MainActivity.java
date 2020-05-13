package com.test.page;

import com.test.log.LogDemo;
import com.test.main.StartMonitor;
import com.test.perfordata.DeviceAndPack;
import com.test.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.apache.log4j.Logger;

/**
 * 20200427:��ʼ�����
 */
public class MainActivity {
    private final static Logger logger = Logger.getLogger(LogDemo.class);
    /**
     * device�������ʼ��
     * @param deviceJComeboBox
     * @param packJComboBox
     */
    public void initDeviceComboBox(final JComboBox deviceJComeboBox, final JComboBox packJComboBox){
        DevicesInfos devicesInfos = new DevicesInfos();
        String[] devicesArray=devicesInfos.getDevicesArray();
        deviceJComeboBox.setModel(new DefaultComboBoxModel(devicesArray));
        if (devicesArray.length>1) {
            logger.info("�ж���豸��Ĭ��ѡ��"+devicesArray[0]);
            new DeviceAndPack().setDeivceid(devicesArray[0]);
            refreshPackCombobox(packJComboBox);
            new DeviceInfoPanel().refreshDeviceInfoPanel();
            deviceJComeboBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    System.out.println("eeeeee:"+e.getStateChange());
                    if (e.getStateChange() == e.SELECTED) {
                        logger.info("ѡ���豸:"+String.valueOf(deviceJComeboBox.getSelectedItem()));
                        //ѡ���豸����Ҫ��device��ֵ
                        new DeviceAndPack().setDeivceid(String.valueOf(deviceJComeboBox.getSelectedItem()));
                        System.out.println(DeviceAndPack.deivceid+":::::::::::");
                        //����Ҫǿ����package������ˢ�£�����ȡ���µ�packages
                        refreshPackCombobox(packJComboBox);
                        new DeviceInfoPanel().refreshDeviceInfoPanel();
                    }
                }
            });
        }else if(devicesArray.length==1){
            logger.info("����һ���豸��ѡ�У�"+devicesArray[0]);
            new DeviceAndPack().setDeivceid(devicesArray[0]);
            //ˢ��pack������
            refreshPackCombobox(packJComboBox);
            new DeviceInfoPanel().refreshDeviceInfoPanel();
        }else{
            //û���豸
            logger.info("û���豸��������");
        }
    }
    /**
     * �豸������ˢ��
     * @param deviceCombobox
     * @param packJComboBox
     */
    public void refreshDeviceCombobox(JComboBox deviceCombobox, JComboBox packJComboBox){
        logger.info("ˢ���豸�б�");
        System.out.println("refresh device");
//        initDeviceComboBox(deviceCombobox, packJComboBox);
        String[] devicesArray=new DevicesInfos().getDevicesArray();
        System.out.println("devicearray:"+devicesArray.length);
        deviceCombobox.removeAllItems();
        if (devicesArray.length>0){
            deviceCombobox.setModel(new DefaultComboBoxModel(devicesArray));
            new DeviceAndPack().setDeivceid(devicesArray[0]);
            refreshPackCombobox(packJComboBox);

        }else {
            logger.info("û���豸��������");
            new DeviceAndPack().setDeivceid("");
        }
    }
    /**
     * ˢ�°���������
     * @param packJComboBox
     */
    public void refreshPackCombobox(JComboBox packJComboBox){
        //��cmʱ��Ĭ��ѡ��cm
        logger.info("ˢ���Ѱ�װ����б�");
        String[] comboValue=new DeviceInfo().getAllPack();
        packJComboBox.removeAllItems();
        if (comboValue!=null) {
            boolean flag = false;
            for (String s : comboValue) {
                if (s.contains("com.cleanmaster.mguard_cn")) {
                    flag = true;
                }
            }
            System.out.println("flag::::+" + flag);
            packJComboBox.setModel(new DefaultComboBoxModel(comboValue));
            if (flag) {
                logger.info("����Ĭ�ϰ�����com.cleanmaster.mguard_cn");
                packJComboBox.setSelectedItem("com.cleanmaster.mguard_cn");
            }else {
                logger.info("δ��װcom.cleanmaster.mguard_cn��Ĭ��ѡ�а�����"+comboValue[0]);
                new DeviceAndPack().setPackagename(comboValue[0]);
            }
        }
    }
    /**
     * �����������ʼ��
     * @return
     */
    public JComboBox packComboBox(){
        final JComboBox jComboBox = new JComboBox();
        JList jList=new JList();
        JScrollPane jp=new JScrollPane(jList);
        jp.setPreferredSize(new Dimension(100, 200));
        jComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    //ѡ������󣬾Ϳ��Լ���Ӧ����Ϣ
                    DeviceAndPack dp=new DeviceAndPack();
                    dp.setPackagename(String.valueOf(jComboBox.getSelectedItem()));
                    logger.info("ѡ�������"+DeviceAndPack.packagename);
                    System.out.println(dp.deivceid+"::::"+DeviceAndPack.packagename);
                }
            }
        });
        return jComboBox;
    }

    public static void main(String[] args) {
        final MainActivity mainActivity = new MainActivity();
        final DeviceInfoPanel deviceInfoPanel=new DeviceInfoPanel();
        JPanel devicePanel=deviceInfoPanel.getDeviceInfoPanel();

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
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.YELLOW);

        JButton deviceRefreshButton=new JButton("ˢ��");
        JButton screenshotButton = new JButton("��ͼ");
        JButton clearLogcat = new JButton("���logcat");
        JButton packRefreshButton=new JButton("ˢ��");
        JButton clearCach = new JButton("�������");
        JButton uninstallCM = new JButton("ж    ��");
        //����豸ѡ��
        JLabel device=new JLabel("device��");
        final JComboBox packJComboBox=mainActivity.packComboBox();
        final JComboBox deviceJComeboBox = new JComboBox();
        mainActivity.initDeviceComboBox(deviceJComeboBox, packJComboBox);
        //deviceˢ�°�ť��Ӽ���
        deviceRefreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainActivity.refreshDeviceCombobox(deviceJComeboBox, packJComboBox);
                mainActivity.refreshPackCombobox(packJComboBox);
                deviceInfoPanel.refreshDeviceInfoPanel();
            }
        });
        //��ͼ��ť����
        screenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeviceInfo().screenShot();
            }
        });
        clearLogcat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("���"+DeviceAndPack.deivceid+":logcat");
                new DeviceInfo().clearLogcat();
            }
        });
        //packageˢ�°�ť����
        packRefreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainActivity.refreshPackCombobox(packJComboBox);
            }
        });
        
        panel.add(device);
        panel.add(deviceJComeboBox);
        panel.add(deviceRefreshButton);
        panel.add(screenshotButton);
        panel.add(clearLogcat);
        //��Ӱ���ѡ��
        JLabel packagename=new JLabel("package��");
        panel.add(packagename);
        panel.add(packJComboBox);

        panel.add(packRefreshButton);
        //�建�水ť����
        clearCach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //���cm����
                logger.info("������桭������"+DeviceAndPack.packagename);
                new AppInfo().clearAPK();
            }
        });
        //ж�ذ�ť������ж�غ�pack������Ҫˢ��
        uninstallCM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logger.info("ж�ء�������"+DeviceAndPack.packagename);
                new AppInfo().clearAPK();
                new AppInfo().uninstallAPK();
                mainActivity.refreshPackCombobox(packJComboBox);
            }
        });
        panel.add(clearCach);
        panel.add(uninstallCM);

        jFrame.add(panel);
        jFrame.add(devicePanel);
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
