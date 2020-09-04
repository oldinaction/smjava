package cn.aezo.testbase.swing.tray;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrayTest {
    public static void main(String[] args) throws Exception {
        // 将本机系统外观设置为窗体当前外观
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // 定义弹出菜单
        JPopupMenu jMenu = new JPopupMenu();

        // 为JPopupMenu设置UI
        jMenu.setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                super.paint(g, c);

                // 画弹出菜单左侧的灰色背景
                g.setColor(new Color(236, 237, 238));
                g.fillRect(0, 0, 25, c.getHeight());

                // 画弹出菜单右侧的白色背景
                g.setColor(new Color(255, 255, 255));
                g.fillRect(25, 0, c.getWidth() - 25, c.getHeight());
            }
        });

        // 定义弹出菜单项
        JMenuItem online = new JMenuItem("我在线上",
                new ImageIcon(TrayTest.class.getClassLoader().getResource("images/tray/ms-icon-16px.png")));
        JMenuItem changeTrayImage = new JMenuItem("修改托盘图标");
        JMenuItem openMenu = new JMenuItem("打开主面板");
        JMenuItem closeMenu = new JMenuItem("退出MyQQ");

        // 添加弹出菜单项到弹出菜单
        jMenu.add(online);
        jMenu.add(changeTrayImage);
        jMenu.addSeparator(); // 添加分割线
        jMenu.add(openMenu);
        jMenu.add(closeMenu);

        // 得到当前系统托盘
        final SystemTray systemtray = SystemTray.getSystemTray();

        // 创建带指定图像、工具提示和弹出菜单的 MyTrayIcon
        ImageIcon trayImage = new ImageIcon(TrayTest.class.getClassLoader().getResource("images/tray/tray-warning.png"));
        final MyTrayIcon trayIcon = new MyTrayIcon(trayImage.getImage(), "MyQQ", jMenu);

        // 将TrayIcon添加到系统托盘
        try {
            systemtray.add(trayIcon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }

        // 设置单击系统托盘图标显示主窗口
        trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String outStr = "";
                if (e.getButton() == e.BUTTON1) {
                    outStr = "左键";
                } else if (e.getButton() == e.BUTTON3) {
                    outStr = "右键";
                } else {
                    outStr = "中键";
                }

                if (e.getClickCount() == 2) {
                    outStr = outStr + "双击";
                } else {
                    outStr = outStr + "点击";
                }

                System.out.println(outStr);
            }
        });

        // 定义ActionListener监听器
        ActionListener MenuListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("修改托盘图标")) {
                    System.out.println("修改托盘图标, actionPerformed = " + e);
                    ImageIcon trayImage = new ImageIcon(TrayTest.class.getClassLoader().getResource("images/tray/tray-running.png"));
                    trayIcon.setImage(trayImage.getImage());
                } else if (e.getActionCommand().equals("打开主面板")) {
                    System.out.println("打开主面板, actionPerformed = " + e);
                } else if (e.getActionCommand().equals("退出MyQQ")) {
                    systemtray.remove(trayIcon);
                    System.exit(0);
                }
            }
        };

        // 为弹出菜单项添加监听器
        changeTrayImage.addActionListener(MenuListen);
        openMenu.addActionListener(MenuListen);
        closeMenu.addActionListener(MenuListen);
    }
}
