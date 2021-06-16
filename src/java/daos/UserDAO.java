/*
 * To change this license header, choose License HeaderesultSet in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ultils.Connector;

/**
 *
 * @author Lenovo
 */
public class UserDAO {

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

    public User getOneUserByUserId(String userId) throws Exception {
        User user = null;

        try {
            connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_User WHERE userId=?";
            preStm = connection.prepareStatement(sql);
            preStm.setString(1, userId);
            resultSet = preStm.executeQuery();

            if (resultSet.next()) {
                int password = resultSet.getInt("password");
                String fullName = resultSet.getString("fullName");
                int role = resultSet.getInt("role");
                user = new User(userId, password, fullName, role);
            }
        } finally {
            this.closeConnection();
        }
        return user;
    }
}
