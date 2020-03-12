package com.example.a80797.bookhotel_recognize.pay.model;

public class orderPayment {
    private String orderData;
    private String orderDataDescription;
    private String orderAccount;

    public orderPayment(String orderData, String orderDataDescription, String orderAccount) {
        this.orderData = orderData;
        this.orderDataDescription = orderDataDescription;
        this.orderAccount = orderAccount;
    }

    public String getOrderData() {
        return orderData;
    }

    public void setOrderData(String orderData) {
        this.orderData = orderData;
    }

    public String getOrderDataDescription() {
        return orderDataDescription;
    }

    public void setOrderDataDescription(String orderDataDescription) {
        this.orderDataDescription = orderDataDescription;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount;
    }
}
