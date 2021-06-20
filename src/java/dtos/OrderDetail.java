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
    private Mobile mobile;
    private float unitPrice;
    private int quantity;

    public OrderDetail(int orderId, Mobile mobile, float unitPrice, int quantity) {
        this.orderId = orderId;
        this.mobile = mobile;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.orderDetailId = 0;
    }

    public OrderDetail(int orderDetailId, int orderId, Mobile mobile, float unitPrice, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.mobile = mobile;
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

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
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
