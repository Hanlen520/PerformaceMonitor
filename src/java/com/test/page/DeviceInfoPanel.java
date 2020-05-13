package com.test.page;

import com.test.util.DeviceInfo;

import javax.swing.*;
import java.awt.*;

public class DeviceInfoPanel {
    private static JPanel deviceInfoPanel=new JPanel();
    private JLabel brand=new JLabel("Ʒ�ƣ�");
    private static JLabel brandJlabel=new JLabel();
    private JLabel model=new JLabel("�ͺţ�");
    private static JLabel modelJlabel=new JLabel();
    private JLabel dp=new JLabel("�ֱ��ʣ�");
    private static JLabel dpJlabel=new JLabel();
    private JLabel os = new JLabel("android:");
    private static JLabel osJlabel = new JLabel();

    public JPanel getDeviceInfoPanel() {
        deviceInfoPanel.setBorder(BorderFactory.createTitledBorder("device information"));
        deviceInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        deviceInfoPanel.setBackground(Color.GRAY);

        brandJlabel.setText(new DeviceInfo().getBrand());
        modelJlabel.setText(new DeviceInfo().getModel());
        dpJlabel.setText(new DeviceInfo().getDp());
        osJlabel.setText(new DeviceInfo().getOsVersionCode());

        deviceInfoPanel.add(brand);
        deviceInfoPanel.add(brandJlabel);
        deviceInfoPanel.add(model);
        deviceInfoPanel.add(modelJlabel);
        deviceInfoPanel.add(dp);
        deviceInfoPanel.add(dpJlabel);
        deviceInfoPanel.add(os);
        deviceInfoPanel.add(osJlabel);
        return deviceInfoPanel;
    }
    public void refreshDeviceInfoPanel(){
        brandJlabel.setText(new DeviceInfo().getBrand());
        modelJlabel.setText(new DeviceInfo().getModel());
        dpJlabel.setText(new DeviceInfo().getDp());
        osJlabel.setText(new DeviceInfo().getOsVersionCode());
    }
}
