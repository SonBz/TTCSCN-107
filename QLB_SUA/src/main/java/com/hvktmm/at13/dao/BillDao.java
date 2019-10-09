package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Bill;
import com.hvktmm.at13.model.BillIterm;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public int insertBill(Bill bill){
        int generatedKey = -1;
        try {
            connection=getConnection();
            String sql="insert into bill(date_trading,total_money,note,customer_id,user_id) values (?,?,?,?,?)";
            ptmt=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ptmt.setLong(2,bill.getTotal_money());
            ptmt.setTimestamp(1, timestamp);
            ptmt.setString(3,bill.getNote());
            ptmt.setInt(4,bill.getCustomerId());
            ptmt.setInt(5,bill.getUserId());
            ptmt.executeUpdate();
            ResultSet rs = ptmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                return generatedKey;
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

        return generatedKey;
    }

    public ObservableList<BillIterm> billList(){
        BillIterm billIterm=null;
        ObservableList<BillIterm> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select b.id,b.date_trading,b.note,b.total_money,u.first_name from bill as b join user as u where b.user_id= u.id ";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String date_trading = resultSet.getString("date_trading");
                String note = resultSet.getString("note");
                String user = resultSet.getString("first_name");
                long totalMoney = resultSet.getLong("total_money");
                billIterm = new BillIterm(id,date_trading,note,totalMoney,user);
                list.add(billIterm);
            }
            return list;
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
        return null;
    }
    public ObservableList<BillIterm> billCustomerList(int customerId){
        BillIterm billIterm=null;
        ObservableList<BillIterm> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select b.id,b.date_trading,b.note,b.total_money,u.first_name from bill as b join user as u where b.user_id= u.id and b.customer_id=? ";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,customerId);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String date_trading = resultSet.getString("date_trading");
                String note = resultSet.getString("note");
                String user = resultSet.getString("first_name");
                long totalMoney = resultSet.getLong("total_money");
                billIterm = new BillIterm(id,date_trading,note,totalMoney,user);
                list.add(billIterm);
            }
            return list;
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
        return null;
    }
}
