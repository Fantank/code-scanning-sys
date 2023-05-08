package com.fantank.device;

import com.fantank.data.DataShare;
import com.fantank.service.WebRequest;
import com.fantank.util.MyLog;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

public class DeviceManagement {

    public static DeviceApi device;
    private boolean isScanning = false;

    private int scanInternal = 1000;

    public DeviceManagement() {
        device = new DeviceApi();
    }

    public boolean connectDevice(){
        return device.vbarOpen();
    }

    public boolean isConnected(){
        return connectDevice();
    }

    public void startScanning(int internal) throws InterruptedException {
        this.scanInternal = internal;
        if(!device.vbarOpen()){
            throw new RuntimeException();
        }
        isScanning = true;
        Thread thread = new Thread(new DeviceWorkThread());
        thread.start();
    }

    public void stopScanning(){
        isScanning = false;
        device.vbarClose();
    }


    class DeviceWorkThread implements Runnable{
        @Override
        public void run() {
            while (isScanning){
                String str = device.getResultStr();
                if(str != null && str != ""){
                    MyLog.logToFile("---------------------------" + LocalDateTime.now() +"\n");
                    DataShare.addToDataMap(str);
//                    WebRequest.sendWebMessage(str);
//                    System.out.println(str);
                }
                try {
                    sleep(scanInternal);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}


