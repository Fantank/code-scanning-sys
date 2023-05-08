package com.fantank.service;

import com.fantank.Configuration;
import com.fantank.util.MyConfig;
import com.fantank.util.MyLog;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        MultiValueMap requestBody = new LinkedMultiValueMap();
        requestBody.add("order_number", order_number);
        requestBody.add("result", result);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity httpEntity = new HttpEntity(requestBody, headers);
        try {
            template.postForObject(url, httpEntity, String.class);
            //savePage(page);
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
