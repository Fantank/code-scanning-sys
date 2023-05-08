package com.fantank.test.device;

import com.fantank.data.DataShare;
import com.fantank.service.DesktopBrowseApplication;
import com.fantank.service.PerformanceDeamon;
import com.fantank.device.DeviceManagement;
import com.fantank.service.CodeResult;
import org.junit.Test;

import static java.lang.Thread.sleep;


public class DeviceFunctionTest {
    @Test
    public void TestShotScan() throws InterruptedException {
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
        while (true) {
            sleep(100000);
        }
    }

}
