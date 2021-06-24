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
    private User customer;
    private Date orderDate;
    private float total;

    public Order(int orderId, User customer, Date orderDate, float total) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.total = total;
    }

    public Order(User customer, Date orderDate, float total) {
        this.orderId = 0;
        this.customer = customer;
        this.orderDate = orderDate;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
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
