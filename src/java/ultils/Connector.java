/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultils;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Lenovo
 */
public class Connector {
    public static Connection getConnection() throws Exception {
        Context context = new InitialContext();
        Context env = (Context) context.lookup("java:comp/env");
        DataSource dataSource = (DataSource) env.lookup("DatabaseConnection");
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
    