package com.fantank.service;

import com.fantank.Configuration;
import com.fantank.data.DataShare;
import com.fantank.util.MyLog;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static java.lang.Thread.sleep;

public class CodeResult implements Runnable, Configuration {

    private boolean isRunning = false;

    private String codeStateJudge(Integer state) {
        AudioPlay.playAudio(state);
        switch (state) {
            case 1:
                return "扫码成功";
            case 2:
                return "码已过期";
            case 3:
                return "无效码";

        }
        return null;
    }

    public void startReturnResult() {
        isRunning = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (isRunning || !DataShare.valiadQueue.isEmpty()) {
            for (Map.Entry<String, Integer> entry : DataShare.valiadQueue.entrySet()) {
                String key;
                String res = codeStateJudge(entry.getValue());

                String log = "Result--> order_number:" + entry.getKey() + "\t" + res;
                System.out.println(log);

                MyLog.logToFile(log +"\n");

                WebRequest.sendWebMessage(key = entry.getKey(), res);
                if (entry.getValue() != 3)
                    DataShare.dataMap.remove(key);
                DataShare.valiadQueue.remove(key);

            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
