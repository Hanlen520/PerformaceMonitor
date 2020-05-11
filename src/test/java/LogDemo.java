import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogDemo {
    private JScrollPane logScrollPane;
    private JTextArea logTextArea;
    private final static Log log = LogFactory.getLog(LogDemo.class);

    public LogDemo(JTextArea logTextArea, JScrollPane logScrollPane) {
        this.logScrollPane = logScrollPane;
        this.logTextArea = logTextArea;
    }

    public void initLog() {
        try {
            Thread t2;
            t2 = new TextAreaLogAppender(logTextArea, logScrollPane);
            t2.start();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e, "����־����������", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] s) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(500, 500);
        JTextArea logta = new JTextArea();
        JScrollPane logsp = new JScrollPane(logta);
        logta.setColumns(20);
        logta.setRows(5);
        logsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        LogDemo logDemoFrame = new LogDemo(logta, logsp);
        logDemoFrame.initLog();
        jFrame.add(logsp);
        jFrame.setVisible(true);
        for (int i = 0; i < 1000; i++) {
            log.info("������־���:" + i);
        }
    }
}
