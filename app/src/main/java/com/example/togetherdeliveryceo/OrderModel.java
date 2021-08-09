package com.example.togetherdeliveryceo;


public class OrderModel {
    String orderId;
    String price;
    String storeId;
    String ranNum;
    String approval;
    String complete;
    String place;
    String payment;
    //long orderTime;


    private OrderModel(){}

    private OrderModel(String orderId, String price, String storeId, String ranNum, String place,  String approval, String complete, String payment){
        this.orderId = orderId;
        //this.orderTime = orderTime;
        this.price = price;
        this.storeId = storeId;
        this.ranNum = ranNum;
        this.approval = approval;
        this.complete = complete;
        this.place = place;
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOrderId(){return orderId;}

    public void setOrderId(String orderId){this.orderId = orderId;}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
