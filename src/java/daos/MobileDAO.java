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

    public boolean addOneMobile(Mobile mobile) throws Exception {
        boolean isSuccess = false;
        try {
            connection = Connector.getConnection();
            String query = "INSERT INTO tbl_Mobile VALUES (?,?,?,?,?,?,?)";
            preStm = connection.prepareStatement(query);
            preStm.setString(1, mobile.getMobileId());
            preStm.setString(2, mobile.getDescription());
            preStm.setFloat(3, mobile.getPrice());
            preStm.setString(4, mobile.getMobileName());
            preStm.setInt(5, mobile.getYearOfProduction());
            preStm.setInt(6, mobile.getQuantity());
            preStm.setBoolean(7, mobile.isNotSale());

            isSuccess = preStm.executeUpdate() > 0;
        } finally {
            this.closeConnection();
        }
        return isSuccess;
    }

    public boolean updateOneMobile(Mobile mobile) throws Exception {
        boolean isSuccess = false;
        try {
            connection = Connector.getConnection();
            String query = "UPDATE tbl_Mobile SET description = ?, price = ?, mobileName = ?, yearOfProduction = ?, quantity = ?, notSale = ? WHERE mobileId = ?";
            preStm = connection.prepareStatement(query);

            preStm.setString(1, mobile.getDescription());
            preStm.setFloat(2, mobile.getPrice());
            preStm.setString(3, mobile.getMobileName());
            preStm.setInt(4, mobile.getYearOfProduction());
            preStm.setInt(5, mobile.getQuantity());
            preStm.setBoolean(6, mobile.isNotSale());
            preStm.setString(7, mobile.getMobileId());

            isSuccess = preStm.executeUpdate() > 0;
        } finally {
            this.closeConnection();
        }
        return isSuccess;
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

    public boolean deleteOneMobileById(String mobileId) throws Exception {
        boolean isSuccess = false;
        try {
            connection = Connector.getConnection();
            String query = "DELETE FROM tbl_Mobile WHERE mobileId = ?";
            preStm = connection.prepareStatement(query);
            preStm.setString(1, mobileId);
            isSuccess = preStm.executeUpdate() > 0;
        } finally {
            this.closeConnection();
        }
        return isSuccess;
    }

    public Mobile getOneMobile(String field, String value) throws Exception {
        Mobile mobile = null;
        try {
            connection = Connector.getConnection();
            String query = "SELECT * FROM tbl_Mobile WHERE " + field + " = ?";
            preStm = connection.prepareStatement(query);

            preStm.setString(1, value);

            resultSet = preStm.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(field).equals(value)) {
                    String mobileId = resultSet.getString("mobileId");
                    String description = resultSet.getString("description");
                    float price = resultSet.getFloat("price");
                    String mobileName = resultSet.getString("mobileName");
                    int yearOfProduction = resultSet.getInt("yearOfProduction");
                    int quantity = resultSet.getInt("quantity");
                    boolean notSale = resultSet.getBoolean("notSale");

                    mobile = new Mobile(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
                }
            }
        } finally {
            this.closeConnection();
        }
        return mobile;
    }
}
