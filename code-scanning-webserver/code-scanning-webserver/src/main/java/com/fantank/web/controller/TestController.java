package com.fantank.web.controller;

import com.fantank.handler.MyWebSocketHandler;
import com.fantank.web.Service;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class TestController {
    @Autowired
    Service service;

    @RequestMapping(value = "/test")
    @ResponseBody
    @CrossOrigin
    public String getTest(@RequestBody String requestBody) {
        System.out.println("received: "+requestBody);
        return "index";
    }
}
