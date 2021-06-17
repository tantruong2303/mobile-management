/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Mobile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import ultils.Connector;

/**
 *
 * @author Lenovo
 */
public class MobileDAO {

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

    public ArrayList<Mobile> getMobiles(float min, float max) throws Exception {
        ArrayList<Mobile> mobileList = new ArrayList<>();
        try {
            connection = Connector.getConnection();
            String query = "SELECT * FROM tbl_Mobile WHERE price >= ? AND price <= ? ORDER BY price ASC";
            preStm = connection.prepareStatement(query);
            preStm.setFloat(1, min);
            preStm.setFloat(2, max);
            resultSet = preStm.executeQuery();

            while (resultSet.next()) {

                String mobileId = resultSet.getString("mobileId");
                String description = resultSet.getString("description");
                float price = resultSet.getFloat("price");
                String mobileName = resultSet.getString("mobileName");
                int yearOfProduction = resultSet.getInt("yearOfProduction");
                int quantity = resultSet.getInt("quantity");
                boolean notSale = resultSet.getBoolean("notSale");

                Mobile mobile = new Mobile(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);

                mobileList.add(mobile);
            }
        } finally {
            this.closeConnection();
        }
        return mobileList;
    }
}
