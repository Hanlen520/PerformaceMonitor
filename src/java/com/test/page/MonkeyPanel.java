package com.test.page;

import com.test.log.LogDemo;
import com.test.util.AppInfo;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MonkeyPanel {
    private final static Logger logger = Logger.getLogger(LogDemo.class);
    private JPanel monkeyPanel=new JPanel();
    private JButton monkeyJButton = new JButton("ִ��monkey");
    private JLabel countLabel = new JLabel("������");
    private JTextField count = new JTextField(8);
    private JCheckBox ignoreCrash=new JCheckBox("ignorecrashes");
    private JCheckBox ignoreTimeouts = new JCheckBox("ignoretimeouts");

    public JPanel getMonkeyPanel(){
        monkeyPanel.setBorder(BorderFactory.createTitledBorder("monkey"));
        monkeyJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (count.getText()!=null&&!count.getText().equals("")){
                        if (Integer.parseInt(count.getText())>0){
//                            if (ignoreCrash.isSelected())
                            new AppInfo().runMonkey(ignoreCrash.isSelected(), ignoreTimeouts.isSelected(), Integer.parseInt(count.getText()));
                        }else {
                            logger.info("ִ�д������벻��ȷ!!");
                        }
                    }else {
                        logger.info("����ִ�д���!!!");
                    }

                } catch (IOException ioException) {
                    logger.error(ioException);
                }
            }
        });
        monkeyPanel.add(ignoreCrash);
        monkeyPanel.add(ignoreTimeouts);
        monkeyPanel.add(countLabel);
        monkeyPanel.add(count);
        monkeyPanel.add(monkeyJButton);
        return monkeyPanel;
    }
}
