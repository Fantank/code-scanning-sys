package com.fantank.data;

import com.fantank.pojo.TicketInf;
import com.fantank.Configuration;
import com.fantank.service.WebRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

public class DataShare implements Configuration {
    public static ConcurrentHashMap<String, TicketInf> dataMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> valiadQueue = new ConcurrentHashMap<>();

    private static Integer errorNum = -1;

    private static Gson gson = new Gson();
    private boolean isDealing = false;

    public static boolean addToDataMap(String jsonString) {

        TicketInf ticket = null;
        try {
            WebRequest request = new WebRequest();
            request.jsonString = jsonString;
            new Thread(request).start();

            ticket = gson.fromJson(jsonString, TicketInf.class);
            System.out.println(ticket);
            String order;
            if (!dataMap.containsKey(order = ticket.getOrder_number())) {
                dataMap.put(order, ticket);
                return true;
            }
        } catch (JsonSyntaxException e) {
            valiadQueue.put(String.valueOf(errorNum--), VALIDSTATE[2]);
        }


        return false;
    }

    public void startDealCode() {
        isDealing = true;
        new Thread(new DataProcessThread()).start();
    }

    public void stopDealCode() {
        isDealing = false;
    }

    class DataProcessThread implements Runnable {
        @Override
        public void run() {
            while (isDealing || !dataMap.isEmpty()) {
                for (TicketInf ticket : dataMap.values()) {
                    //System.out.println(ticket.getCreate_time().equals(LocalDate.now().toString()));
                    if (ticket.getCreate_time().equals(LocalDate.now().toString()))
                        valiadQueue.put(ticket.getOrder_number(), VALIDSTATE[0]);
                    else
                        valiadQueue.put(ticket.getOrder_number(), VALIDSTATE[1]);
                    //System.out.println(valiadQueue.get(order)+" "+valiadQueue.size());
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
