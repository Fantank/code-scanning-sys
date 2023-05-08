package com.fantank.web.controller;

import com.fantank.handler.MyWebSocketHandler;
import com.fantank.web.Service;
import com.fantank.web.service.ServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller

public class CodeExplainController {
    static Deque<String> sequence = new LinkedList<>();
    static String lastOrderNumber = null;
    static String lastResult = null;


    @Autowired
    Service service;

    @RequestMapping(value = "/code")
    @ResponseBody
    @CrossOrigin
    public Map getCodeResult(HttpServletResponse response, HttpSession session, @RequestParam(required = false) String order_number, @RequestParam(required = false) String result) throws IOException {

//        response.setHeader("Cache-Control", "max-age=5");

        if (order_number != null) {
            lastResult = result;
            lastOrderNumber = order_number;
            System.out.println(result + " " + order_number);
        }

        Map resMap = new HashMap<>();
        resMap.put("order_number", lastOrderNumber);
        resMap.put("result", lastResult);
        System.out.println(resMap.toString());

        ServletContext servletContext = session.getServletContext();
        ConcurrentHashMap<String, String[]> list;
        if ((list = (ConcurrentHashMap<String, String[]>) servletContext.getAttribute("list")) == null)
            list = new ConcurrentHashMap<>();

        if (result != null && result.equals("扫码成功") && !sequence.contains(order_number)) {

            Map<String, String> res = service.addNewOrderData(order_number);
            System.out.println("controller " + res);
            if (res != null) {
                sequence.addFirst(order_number);
                if (sequence.size() > 10) {
                    list.remove(sequence.pollLast());
                }
                list.put(order_number, new String[]{res.get("vehicle_number"), res.get("time_in")});
                servletContext.setAttribute("list", list);

            }
        }
        resMap.put("data", list);
//        model.addAttribute("list", list);

        MyWebSocketHandler.sendMessageToAll(new TextMessage(new Gson().toJson(resMap)));
        return resMap;
    }

}
