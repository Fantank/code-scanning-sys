package com.fantank.client.test;

import com.fantank.service.WebRequest;
import org.junit.Test;

import static java.lang.Thread.sleep;

public class TestWebRequest {
    @Test
    public void testResponse() throws InterruptedException {

        WebRequest.sendWebMessage("40015843","扫码成功");
        sleep(10000);
    }
}
