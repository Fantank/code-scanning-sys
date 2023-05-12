package com.fantank.service;

import com.fantank.pojo.ResultInf;
import com.fantank.util.MyConfig;
import com.fantank.util.MyLog;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class WebRequest implements Runnable{
    static RestTemplate template = new RestTemplate();
    static String url;

    static String newUrl;
    static File page;

    public String jsonString;

    static {
        try {
            url = MyConfig.getConfig("requestUrl");
            page = new File(MyConfig.getConfig("pagePath"));
            newUrl = MyConfig.getConfig("newRequestUrl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void sendWebMessage(String order_number, String result) {
        System.out.println(url);
        MultiValueMap requestBody = new LinkedMultiValueMap();
        requestBody.add("order_number", order_number);
        requestBody.add("result", result);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity httpEntity = new HttpEntity(requestBody, headers);
        try {
            String response = template.postForObject(url, httpEntity, String.class);

            ResultInf resultInf = new Gson().fromJson(response,ResultInf.class);
            System.out.println(resultInf);
            if(!resultInf.getAllowed()){
               // sleep(200);
                AudioPlay.playAudio(4);
            }
        } catch (Exception e) {
            MyLog.logToFile("Error-->  " + e + "\n");
            System.out.println(e);
        }
    }

    public static void sendWebMessage(String json) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(json, headers);
        try {
            ResponseEntity<String> entity = template.postForEntity(newUrl, httpEntity, String.class);
            //System.out.println(entity);
            //savePage(page);
        } catch (Exception e) {
            MyLog.logToFile("Error-->  " + e + "\n");
            System.out.println(e);
        }
    }

    public static void savePage(String htmlString) throws IOException {
        if (!page.exists())
            page.createNewFile();

        FileWriter writer = new FileWriter(page, false);
        System.out.println(htmlString);
        writer.write(htmlString);
        writer.close();
    }

    @Override
    public void run() {
        if(jsonString != null)
            sendWebMessage(jsonString);
    }
}
