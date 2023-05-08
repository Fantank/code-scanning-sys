package com.fantank.starter;

import com.fantank.data.DataShare;
import com.fantank.device.DeviceManagement;
import com.fantank.service.CodeResult;
import com.fantank.service.DesktopBrowseApplication;
import com.fantank.service.PerformanceDeamon;

import javax.swing.*;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Starter {
    private static boolean isRunning = false;
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setTitle("扫码程序控制终端");
        frame.setSize(800,200);
        frame.setLocation(400,400);
        frame.setPreferredSize(new Dimension(800,100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        //JButton initBtn = new JButton("开始运行");
        //initBtn.setPreferredSize(new Dimension(300,90));
//        initBtn.addActionListener(e -> {
//            try {
//                init();
//                isRunning = true;
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
        init();
        JButton closeBtn = new JButton("关闭程序");
        closeBtn.setPreferredSize(new Dimension(300,90));
        closeBtn.addActionListener(e -> {
            System.exit(0);
        });

        //panel.add(initBtn);
        panel.add(closeBtn);

        frame.setVisible(true);
    }
    private synchronized static void init() throws InterruptedException {
       // if(isRunning) return;
        DeviceManagement device = new DeviceManagement();
        device.connectDevice();
        device.startScanning(1000);
        DataShare dataShare = new DataShare();
        dataShare.startDealCode();
        CodeResult codeResult = new CodeResult();
        codeResult.startReturnResult();
        PerformanceDeamon performanceDeamon = new PerformanceDeamon();
        performanceDeamon.startDeamon();
        DesktopBrowseApplication browseApplication = new DesktopBrowseApplication();
        browseApplication.startRun();
    }
}
