package com.test.main;

import com.test.perfordata.DeviceAndPack;
import com.test.util.AppInfo;
import com.test.util.DeviceInfo;
import com.test.util.DevicesInfos;
import com.test.util.InfoByDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * 20200427:��ʼ�����
 */
public class MainActivity {
    public JComboBox deviceComboBox(JComboBox packJComboBox){
        DevicesInfos devicesInfos = new DevicesInfos();
        String[] devicesArray=devicesInfos.getDevicesArray();
        JComboBox deviceJComeboBox = new JComboBox(devicesArray);

        if (devicesArray.length>1) {
            new DeviceAndPack().setDeivceid(devicesArray[0]);
            refreshPackCombobox(packJComboBox);
            deviceJComeboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == e.SELECTED) {
                        //ѡ���豸����Ҫ��device��ֵ
                        new DeviceAndPack().setDeivceid(String.valueOf(deviceJComeboBox.getSelectedItem()));
                        System.out.println(DeviceAndPack.deivceid+":::::::::::");
                        //����Ҫǿ����package������ˢ�£�����ȡ���µ�packages
                        refreshPackCombobox(packJComboBox);
                    }
                }
            });
        }else if(devicesArray.length==1){
            new DeviceAndPack().setDeivceid(devicesArray[0]);
            //ˢ��pack������
            refreshPackCombobox(packJComboBox);
        }else{
            System.out.println("device is exception");
        }
        return deviceJComeboBox;
    }
    public void refreshDeviceCombobox(JComboBox deviceCombobox){
        deviceCombobox.removeAllItems();
        deviceCombobox.setModel(new DefaultComboBoxModel<>(new DevicesInfos().getDevicesArray()));
    }
    /**
     * ˢ�°���������
     * @param packJComboBox
     */
    public void refreshPackCombobox(JComboBox packJComboBox){
        packJComboBox.removeAllItems();
        packJComboBox.setModel(new DefaultComboBoxModel<>(new InfoByDevice().getAllPack()));
    }

    public JComboBox packComboBox(){
        String[] comboValue=new InfoByDevice().getAllPack();

        JComboBox jComboBox = new JComboBox(comboValue);
        JList jList=new JList();
        JScrollPane jp=new JScrollPane(jList);
        jp.setPreferredSize(new Dimension(100, 200));

        jComboBox.addItemListener(new ItemListener() {
            @Override
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
        MainActivity mainActivity = new MainActivity();

        JFrame jFrame = new JFrame("PerformanceMonitor--by sai");

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
//        JButton bt1=new JButton("TEST");
//        panel.add(bt1);

        JButton refreshButton=new JButton("ˢ��");


        //����豸ѡ��
        JLabel device=new JLabel("device��");
        panel.add(device);
        JComboBox packJComboBox=mainActivity.packComboBox();
        JComboBox deviceJComeboBox = mainActivity.deviceComboBox(packJComboBox);
        panel.add(deviceJComeboBox);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("refreshhhh");
                mainActivity.refreshDeviceCombobox(deviceJComeboBox);
            }
        });
        panel.add(refreshButton);
        //��Ӱ���ѡ��
        JLabel packagename=new JLabel("package��");
        panel.add(packagename);

        panel.add(packJComboBox);

        DeviceInfo deviceInfo = new DeviceInfo();
        JLabel fenbian=new JLabel("�ֱ��ʣ�");
        panel.add(fenbian);
        JLabel fenbianlv=new JLabel(deviceInfo.getDp());
        panel.add(fenbianlv);


        jFrame.add(panel);
        jFrame.setVisible(true);
    }
}
