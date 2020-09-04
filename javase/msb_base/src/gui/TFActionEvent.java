package gui;

import java.awt.*;
import java.awt.event.*;

public class TFActionEvent {

    public static void main(String[] args) {
        new TFFrame1();
    }

}

class TFFrame1 extends Frame {
    TFFrame1() {
        TextField tf = new TextField();
        add(tf);
        tf.addActionListener(new TFActionListener());
        pack();
        setVisible(true);
    }
}

class TFActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        TextField tf = (TextField) e.getSource();
        System.out.println(tf.getText());
        tf.setText("");
    }
}
