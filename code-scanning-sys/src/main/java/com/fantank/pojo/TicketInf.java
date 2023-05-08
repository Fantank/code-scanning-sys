package com.fantank.pojo;

public class TicketInf {
    private Integer type;
    private String order_number;
    private String create_time;

    public TicketInf(Integer type, String order_number, String create_time) {
        this.type = type;
        this.order_number = order_number;
        this.create_time = create_time;
    }

    public TicketInf() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "TicketInf{" +
                "type=" + type +
                ", order_number='" + order_number + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
