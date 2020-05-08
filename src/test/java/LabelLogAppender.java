import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JLabel;

public class LabelLogAppender extends LogAppender {
    private JLabel label;

    public LabelLogAppender(JLabel label) throws IOException {
        super("label");
        this.label = label;
    }
    @Override
    public void run() {
        // ����ϵ�ɨ��������
        Scanner scanner = new Scanner(reader);
        // ��ɨ�赽���ַ�����ʾ��ָ����JLabel��
        while (scanner.hasNextLine()) {
            try {
                //˯��
                Thread.sleep(100);
                String line = scanner.nextLine();
                label.setText(line);
                line = null;
            } catch (Exception ex) {
                //�쳣��Ϣ��������
            }
        }
    }
}
