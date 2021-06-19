/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import ultils.Connector;

/**
 *
 * @author Lenovo
 */
public class OrderDAO {

    private Connection connection;
    private PreparedStatement preStm;
    private ResultSet resultSet;

    private void closeConnection() throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }

        if (preStm != null) {
            preStm.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public ArrayList<Order> getAllOrders() throws Exception {
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            connection = Connector.getConnection();
            String query = "SELECT * FROM tbl_Orders";
            preStm = connection.prepareStatement(query);
            resultSet = preStm.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                String customerId = resultSet.getString("customerId");
                float total = resultSet.getFloat("total");
                Date orderDate = resultSet.getDate("orderDate");
                Order order = new Order(orderId, customerId, orderDate, total);
                orderList.add(order);
            }
        } finally {
            this.closeConnection();
        }
        return orderList;
    }
    
    
    public boolean addOneOrder(Order order) throws Exception {
        boolean isSuccess = false;
        try {
            connection = Connector.getConnection();
            String query = "INSERT INTO tbl_Orders (orderId, customerId, orderDate, total) VALUES (?, ?, ?, ?)";
            preStm = connection.prepareStatement(query);
            preStm.setInt(1, order.getOrderId());
            preStm.setString(2, order.getCustomerId());
            preStm.setDate(3, order.getOrderDate());
            preStm.setFloat(4, order.getTotal());

            isSuccess = preStm.executeUpdate() > 0;
        } finally {
            this.closeConnection();
        }
        return isSuccess;
    }
    
    public boolean updateOneOrder(Order order) throws Exception {
        boolean isSuccess = false;
        try {
            connection = Connector.getConnection();
            String query = "UPDATE tbl_Orders SET customerId = ?, orderDate = ?, total = ? WHERE orderId = ?";
            preStm = connection.prepareStatement(query);
            preStm.setInt(4, order.getOrderId());
            preStm.setString(1, order.getCustomerId());
            preStm.setDate(2, order.getOrderDate());
            preStm.setFloat(3, order.getTotal());

            isSuccess = preStm.executeUpdate() > 0;
        } finally {
            this.closeConnection();
        }
        return isSuccess;
    }
}
