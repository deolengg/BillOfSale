/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Karan
 */
public class SqlConnection {

    private static Connection conn;
    public static Connection DbConnector() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Found");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@dilbert.humber.ca:1521:grok", "n01168570", "oracle");
            System.out.println("Connection Done");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return conn;
    }

}
