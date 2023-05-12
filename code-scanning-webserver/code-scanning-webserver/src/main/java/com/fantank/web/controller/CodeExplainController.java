package com.fantank.web.controller;

import com.fantank.handler.MyWebSocketHandler;
import com.fantank.web.Service;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
        //如果请求的order_number是新的，那么更新最新的弹窗数据
        if (order_number != null) {
            lastResult = result;
            lastOrderNumber = order_number;
            System.out.println(result + " " + order_number);
        }
        //将最新的结果数据放入结果集
        Map resMap = new HashMap<>();
        resMap.put("order_number", lastOrderNumber);
        resMap.put("result", lastResult);
        System.out.println(resMap);

        //获取session域中的列表
        ServletContext servletContext = session.getServletContext();
        ConcurrentHashMap<String, String[]> list;
        if ((list = (ConcurrentHashMap<String, String[]>) servletContext.getAttribute("list")) == null)
            list = new ConcurrentHashMap<>();

        if (result != null && result.equals("扫码成功") && !sequence.contains(order_number)) {

            //未叫号情况
            Integer status = service.getStatusByOrderNumberThroughTicketId(order_number);
            resMap.put("allowed", status!=null && status == 1 ? true : false);


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
        //通过websocket推到页面
        MyWebSocketHandler.sendMessageToAll(new TextMessage(new Gson().toJson(resMap)));
        //返回是否允许入场
        return resMap;
    }

}
