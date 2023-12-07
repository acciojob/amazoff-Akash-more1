package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    private String time;
    public Order(String id, String time) {
           this.id=id;
           this.time=time;
           int hh=Integer.parseInt(time.substring(0,2));
           int mm=Integer.parseInt(time.substring(3));

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM

        this.deliveryTime=(hh*60)+mm;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
