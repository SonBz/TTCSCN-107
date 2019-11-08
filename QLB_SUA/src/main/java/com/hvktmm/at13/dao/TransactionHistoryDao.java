package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.TransactionHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;

public class TransactionHistoryDao {
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
    // 1 history import
    // 0 history export
    public Boolean insertImport(TransactionHistory transactionHistory, int idx){
        Boolean check = false;
        try {
            connection=getConnection();
            if(idx == 1){
                String sql="insert into transaction_history(amount,date_import,note,product_id,user_id) values (?,?,?,?,?)";
                ptmt=connection.prepareStatement(sql);
                ptmt.setInt(1,transactionHistory.getAmount());
                ptmt.setTimestamp(2, timestamp);
                ptmt.setString(3,transactionHistory.getNote());
                ptmt.setInt(4,transactionHistory.getProductId());
                ptmt.setInt(5,transactionHistory.getUserId());
                int row=0;
                row=ptmt.executeUpdate();
                if (row!=0){
                    check=true;
                }
            }else {
                String sql="insert into transaction_history(amount,date_export,note,product_id,user_id) values (?,?,?,?,?)";
                ptmt=connection.prepareStatement(sql);
                ptmt.setInt(1,transactionHistory.getAmount());
                ptmt.setTimestamp(2, timestamp);
                ptmt.setString(3,transactionHistory.getNote());
                ptmt.setInt(4,transactionHistory.getProductId());
                ptmt.setInt(5,transactionHistory.getUserId());
                int row=0;
                row=ptmt.executeUpdate();
                if (row!=0){
                    check=true;
                }
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

    public ObservableList<TransactionHistory> historyList(){
        TransactionHistory transactionHistory=null;
        ObservableList<TransactionHistory> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select * from transaction_history order by id desc";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                transactionHistory=new TransactionHistory();
                transactionHistory.setId(resultSet.getInt("id"));
                transactionHistory.setAmount(resultSet.getInt("amount"));
                transactionHistory.setNote((resultSet.getString("note")));
                transactionHistory.setDateExport(resultSet.getTimestamp("date_export"));
                transactionHistory.setDateImport(resultSet.getTimestamp("date_import"));
                transactionHistory.setProductId(resultSet.getInt("product_id"));
                transactionHistory.setUserId(resultSet.getInt("user_id"));
                list.add(transactionHistory);
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
