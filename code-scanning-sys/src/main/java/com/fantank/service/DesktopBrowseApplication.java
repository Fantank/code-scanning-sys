package com.fantank.service;

import com.fantank.Configuration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class DesktopBrowseApplication extends Thread implements Configuration {
    public static Desktop desktop = Desktop.getDesktop();
    private boolean isRunning = false;

    public void startRun() {
        isRunning = true;
        this.start();
    }

    @Override
    public void run() {
            File page = new File(pagePath);

            URI uri = page.toURI();

            //System.out.println(page.exists());
        if (page.exists()) {
                try {
                    desktop.browse(uri);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
