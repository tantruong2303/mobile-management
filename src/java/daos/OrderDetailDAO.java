/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ultils.Connector;

/**
 *
 * @author Lenovo
 */
public class OrderDetailDAO {

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

    public boolean addOneOrderDetail(OrderDetail orderDetail) throws Exception {
        boolean isSuccess = false;
        try {
            connection = Connector.getConnection();
            String query = "INSERT INTO tbl_OrderDetails (orderId, mobileId, unitPrice, quantity) VALUES (?, ?, ?, ?)";
            preStm = connection.prepareStatement(query);
            preStm.setInt(1, orderDetail.getOrderId());
            preStm.setString(2, orderDetail.getMobile().getMobileId());
            preStm.setFloat(3, orderDetail.getUnitPrice());
            preStm.setInt(4, orderDetail.getQuantity());

            isSuccess = preStm.executeUpdate() > 0;
        } finally {
            this.closeConnection();
        }
        return isSuccess;
    }
}
