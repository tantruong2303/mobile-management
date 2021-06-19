/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Lenovo
 */
public class OrderDetail {

    private int orderDetailId;
    private int orderId;
    private String mobileId;
    private float unitPrice;
    private int quantity;

    public OrderDetail(int orderId, String mobileId, float unitPrice, int quantity) {
        this.orderId = orderId;
        this.mobileId = mobileId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.orderDetailId = 0;
    }

    public OrderDetail(int orderDetailId, int orderId, String mobileId, float unitPrice, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.mobileId = mobileId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
