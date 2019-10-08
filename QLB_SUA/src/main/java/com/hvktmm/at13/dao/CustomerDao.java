package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    private Connection getConnection() throws SQLException {
        Connection con;
        con= MySqlDao.getInstance().getConnection();
        return con;
    }
    public ObservableList<Customer> customersList(){
        Customer customer=null;
        ObservableList<Customer> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select * from customer ";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                customer=new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setMoneyTotal(resultSet.getLong("money_total"));
                customer.setStatus(resultSet.getInt("status"));
                customer.setNumberOf(resultSet.getInt("number_of"));
                list.add(customer);
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

    public Customer oneCustomer(int id){
        Customer customer=null;
        try {
            connection=getConnection();
            String sql="select * from customer where id=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,id);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                customer=new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setMoneyTotal(resultSet.getLong("money_total"));
                customer.setStatus(resultSet.getInt("status"));
                customer.setNumberOf(resultSet.getInt("number_of"));
            }
            return customer;
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

    public void updateCustomer(long totalMoney, int numberOf, int id){
        try {
            connection=getConnection();
            String sql="update customer set money_total=?, number_of=? where id=?  ";
            ptmt=connection.prepareStatement(sql);
            ptmt.setLong(1,totalMoney);
            ptmt.setInt(2,numberOf);
            ptmt.setInt(3,id);
            ptmt.executeUpdate();
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
    }
    public int insertCustomer(Customer customer){
        int generatedKey = -1;
        try {
            connection=getConnection();
            String sql="insert into customer(name,address,phone_number,money_total) values (?,?,?,?)";
            ptmt=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1,customer.getName());
            ptmt.setString(2,customer.getAddress());
            ptmt.setString(3,customer.getPhoneNumber());
            ptmt.setLong(4,customer.getMoneyTotal());
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
}
