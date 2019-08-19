package cn.aezo.testbase.swing.dialog;

import javax.swing.*;

public class DialogTest {
    public static void main(String[] args) {
        // PLAIN_MESSAGE、ERROR_MESSAGE、WARNING_MESSAGE、INFORMATION_MESSAGE、QUESTION_MESSAGE
        JOptionPane.showMessageDialog(null,"内容", "标题", JOptionPane.INFORMATION_MESSAGE);
        // JOptionPane.showMessageDialog(null,"内容");
        // JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, null);
    }
}
