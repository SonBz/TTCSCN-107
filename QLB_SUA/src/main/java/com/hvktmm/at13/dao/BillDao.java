package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Bill;

import java.sql.*;
import java.util.Date;

public class BillDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Date dateTime = new Date();
    long time = dateTime.getTime();
    Timestamp timestamp = new Timestamp(time);
    private Connection getConnection() throws SQLException {
        Connection con;
        con= MySqlDao.getInstance().getConnection();
        return con;
    }

    public Boolean insertBill(Bill bill){
        Boolean check = false;

        try {
            connection=getConnection();
            String sql="insert into bill(date_trading,total_money,note,customer_id,user_id) values (?,?,?,?,?)";
            ptmt=connection.prepareStatement(sql);
            ptmt.setLong(2,bill.getTotal_money());
            ptmt.setTimestamp(1, timestamp);
            ptmt.setString(3,bill.getNote());
            ptmt.setInt(4,bill.getCustomerId());
            ptmt.setInt(5,bill.getUserId());
            int row=0;
            row=ptmt.executeUpdate();
            if (row!=0){
                check=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return check;
    }
}
