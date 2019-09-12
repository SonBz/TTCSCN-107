package com.hvktmm.at13.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlDao {
    String driverClass="com.mysql.jdbc.Driver";
    String connectionUrl="jdbc:mysql://localhost:3306/qlb_sua";
    String user="root";
    String pass="1234";

    private static MySqlDao ConnectionMySql=null;

    private MySqlDao(){
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, user,pass);
        return conn;
    }

    public static MySqlDao getInstance() {
        if (ConnectionMySql == null) {
            ConnectionMySql = new MySqlDao();
        }
        return ConnectionMySql;
    }

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionMySql.getInstance().getConnection();
            System.out.println("ket noi thanh cong");
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
