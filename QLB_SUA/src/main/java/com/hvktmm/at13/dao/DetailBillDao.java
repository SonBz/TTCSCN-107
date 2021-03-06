package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Bill;
import com.hvktmm.at13.model.DetailBill;
import com.hvktmm.at13.model.DetailBillIterm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class DetailBillDao {
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

    public Boolean insertDetailBill(DetailBill detailBill){
        Boolean check = false;

        try {
            connection=getConnection();
            String sql="insert into detail_bill(amount,total_money,price,bill_id,product_id) values (?,?,?,?,?)";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,detailBill.getAmount());
            ptmt.setLong(2,detailBill.getTotal_money());
            ptmt.setLong(3,detailBill.getPrice());
            ptmt.setInt(4,detailBill.getBillId());
            ptmt.setInt(5,detailBill.getProductId());
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
    public ObservableList<DetailBillIterm> detailBill(){
        DetailBillIterm detailBillIterm=null;
        ObservableList<DetailBillIterm> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="SELECT db.id,db.amount,db.total_money,db.price,p.name FROM detail_bill as db join product p where db.product_id= p.id";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int amount = resultSet.getInt("amount");
                long totalMoney = resultSet.getLong("total_money");
                long price = resultSet.getLong("price");
                String name = resultSet.getString("name");
                detailBillIterm = new DetailBillIterm(id,amount,price, totalMoney,name);
                list.add(detailBillIterm);
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
    public ObservableList<DetailBillIterm> detailBillList(int billId){
        DetailBillIterm detailBillIterm=null;
        ObservableList<DetailBillIterm> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="SELECT db.id,db.amount,db.total_money,db.price,p.name FROM detail_bill as db join product p where db.product_id= p.id and db.bill_id=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,billId);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int amount = resultSet.getInt("amount");
                long totalMoney = resultSet.getLong("total_money");
                long price = resultSet.getLong("price");
                String name = resultSet.getString("name");
                detailBillIterm = new DetailBillIterm(id,amount,price, totalMoney,name);
                list.add(detailBillIterm);
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
