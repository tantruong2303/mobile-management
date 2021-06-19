/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;



/**
 *
 * @author Lenovo
 */
public class Order {

    private int orderId;
    private String customerId;
    private Date orderDate;
    private float total;

    public Order(int orderId, String customerId, Date orderDate, float total) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.total = total;
    }

    public Order(String customerId, Date orderDate, float total) {
        this.orderId = 0;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
