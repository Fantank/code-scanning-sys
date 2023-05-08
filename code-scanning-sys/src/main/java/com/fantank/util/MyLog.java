package com.fantank.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyLog {
    static File logFile;

    static {
        try {
            logFile = new File(MyConfig.getConfig("logPath"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logToFile(String inf) {

        FileOutputStream fos = null;
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fos = new FileOutputStream(logFile, true);
            fos.write(inf.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
