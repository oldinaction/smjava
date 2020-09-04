package gui;

/*	�������ƣ�Java�¼��������
 * 	Դ�ļ����ƣ�TestActionEvent.java
 *	Ҫ  �㣺
 *  	1. Java�¼��������
 *		2. �¼�Դ���¼��������������
 *		3. �����һ�����������ע�������
 */

import java.awt.*;
import java.awt.event.*;

public class TestActionEvent {
    public static void main(String args[]) {
        Frame f = new Frame("Test");
        Button b = new Button("Press Me!");
        Monitor1 bh = new Monitor1();
        b.addActionListener(bh);
        f.add(b, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }
}

class Monitor1 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("a button has been pressed");
    }
}
