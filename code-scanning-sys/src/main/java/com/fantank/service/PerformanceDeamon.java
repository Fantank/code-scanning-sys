package com.fantank.service;

import com.fantank.data.DataShare;
import com.fantank.device.DeviceManagement;
import com.fantank.util.MyLog;

import java.time.LocalDateTime;

public class PerformanceDeamon extends Thread {

    public void startDeamon() {
        this.setDaemon(true);
        this.start();
    }

    int offlineTime = 0;

    @Override
    public void run() {
        while (true) {
            //System.out.println(LocalDate.now());
            //System.out.println("Size of DataMap: " + DataShare.dataMap.size() + " " + DataShare.valiadQueue.size());
            if(DataShare.dataMap.size() > 20) DataShare.dataMap.clear();
            if(DataShare.valiadQueue.size() > 20) DataShare.valiadQueue.clear();

            if(DeviceManagement.device.vbarOpen() == false){
                MyLog.logToFile("Device offline "+ LocalDateTime.now()+"\n");
                System.out.println("Device offline");
            }
            try {
                sleep(5000);
                System.gc();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
