package com.test.page;

import com.test.main.StartMonitor;
import com.test.perfordata.DeviceAndPack;
import com.test.util.DeviceInfo;
import com.test.util.DevicesInfos;
import com.test.util.InfoByDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.apache.log4j.Logger;

/**
 * 20200427:��ʼ�����
 */
public class MainActivity {

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
            new DeviceAndPack().setDeivceid(devicesArray[0]);
            refreshPackCombobox(packJComboBox);
            new DeviceInfoPanel().refreshDeviceInfoPanel();
            deviceJComeboBox.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == e.SELECTED) {
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
            new DeviceAndPack().setDeivceid(devicesArray[0]);
            //ˢ��pack������
            refreshPackCombobox(packJComboBox);
        }else{
            //û���豸
            System.out.println("device is exception");
//            new DeviceAndPack().setDeivceid("");
        }
    }

    /**
     * �豸������ˢ��
     * @param deviceCombobox
     * @param packJComboBox
     */
    public void refreshDeviceCombobox(JComboBox deviceCombobox, JComboBox packJComboBox){
        System.out.println("refresh device");
        initDeviceComboBox(deviceCombobox, packJComboBox);
    }
    /**
     * ˢ�°���������
     * @param packJComboBox
     */
    public void refreshPackCombobox(JComboBox packJComboBox){
        packJComboBox.removeAllItems();
        packJComboBox.setModel(new DefaultComboBoxModel(new InfoByDevice().getAllPack()));
    }
    public void refreshBrand(JLabel brand){
        brand.setText(new DeviceInfo().getBrand());
    }
    /**
     * �ֱ���ˢ��
     * @param fenbianlv
     */
    public void refreshDp(JLabel fenbianlv){
        fenbianlv.setText(new DeviceInfo().getDp());
    }

    /**
     * �����������ʼ��
     * @return
     */
    public JComboBox packComboBox(){
        String[] comboValue=new InfoByDevice().getAllPack();

        final JComboBox jComboBox = new JComboBox(comboValue);
        JList jList=new JList();
        JScrollPane jp=new JScrollPane(jList);
        jp.setPreferredSize(new Dimension(100, 200));

        jComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    //ѡ������󣬾Ϳ��Լ���Ӧ����Ϣ
                    DeviceAndPack dp=new DeviceAndPack();
                    dp.setPackagename(String.valueOf(jComboBox.getSelectedItem()));
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

        JButton refreshButton=new JButton("ˢ��");

        //�����豸ѡ��
        JLabel device=new JLabel("device��");
        final JComboBox packJComboBox=mainActivity.packComboBox();
        final JComboBox deviceJComeboBox = new JComboBox();
        mainActivity.initDeviceComboBox(deviceJComeboBox, packJComboBox);
        //ˢ�°�ť���Ӽ���
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainActivity.refreshDeviceCombobox(deviceJComeboBox, packJComboBox);
                mainActivity.refreshPackCombobox(packJComboBox);
                deviceInfoPanel.refreshDeviceInfoPanel();
            }
        });
        
        panel.add(device);
        panel.add(deviceJComeboBox);
        panel.add(refreshButton);
        //���Ӱ���ѡ��
        JLabel packagename=new JLabel("package��");
        panel.add(packagename);
        panel.add(packJComboBox);



        jFrame.add(panel);
        jFrame.add(devicePanel);
        jFrame.setVisible(true);

        //�����ǰ���������ܼ������ͼ��ʹ�ö��߳���ץ���������ƺ��ٴ˲����ã���Ϊ��Ҫʵʱ���ݣ���˲ʱ����ֱ�Ӹ���ǰ������ʾ���������ռ�һ��list��return
//        StartMonitor monitor=new StartMonitor(DeviceAndPack.packagename,DeviceAndPack.deivceid);
//        Thread t1=new Thread(monitor);
//        t1.start();
    }
}