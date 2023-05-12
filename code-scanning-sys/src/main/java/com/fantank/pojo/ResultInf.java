package com.fantank.pojo;

import java.util.Map;

public class ResultInf {
    private String result;

    private Map data;

    private Boolean allowed;

    private String order_number;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }



    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    @Override
    public String toString() {
        return "ResultInf{" +
                "result='" + result + '\'' +
                ", data=" + data +
                ", allowed='" + allowed + '\'' +
                ", order_number='" + order_number + '\'' +
                '}';
    }
}