package com.example.togetherdeliveryceo;


public class OrderModel {
    String orderId;
    String price;
    String store;
    String ranNum;
    //long orderTime;


    private OrderModel(){}

    private OrderModel(String orderId, long orderTime, String price, String store, String ranNum){
        this.orderId = orderId;
        //this.orderTime = orderTime;
        this.price = price;
        this.store = store;
        this.ranNum = ranNum;
    }

    public String getOrderId(){return orderId;}

    public void setOrderId(String orderId){this.orderId = orderId;}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
    public String getRanNum() {
        return ranNum;
    }

    public void setRanNum(String ranNum) {
        this.ranNum = ranNum;
    }

   /* public long getOrderTime(){return orderTime;}

    public void setOrderTime(long orderTime){this.orderTime = orderTime;}*/


}
