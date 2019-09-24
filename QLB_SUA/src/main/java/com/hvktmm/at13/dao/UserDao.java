package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.User;
import com.hvktmm.at13.model.UserItem;
import com.jfoenix.controls.JFXButton;
import comhvktmm.at13.utils.PassUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    PassUtils pass=new PassUtils();
    private Connection getConnection() throws SQLException {
        Connection con;
        con= MySqlDao.getInstance().getConnection();
        return con;
    }
    // login user
    public User login(String username, String password){
        User user=null;

        try {

            connection=getConnection();
            String sql="select * from user where username=? and password=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,username);
            ptmt.setString(2,pass.encrypt(password));     //pass.encrypt(password)
            resultSet=ptmt.executeQuery();
            if (resultSet.next()){
                user=new User();
                user.setId(resultSet.getInt("id"));
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setDate_of_birth(resultSet.getDate("date_of_birth"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone_number(resultSet.getString("phone_number"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getString("gender"));
                user.setIs_staff(resultSet.getInt("is_staff"));

            }
            return user;
        }
        catch (SQLException e) {
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
    // insert user
    public Boolean insertUser(User user){
        Boolean check = false;

        try {
            connection = getConnection();
            String sql = "insert into user(first_name,last_name,username,password,email," +
                         "phone_number, address,date_of_birth,gender) values (?,?,?,?,?,?,?,?,?)";
            ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,user.getFirst_name());
            ptmt.setString(2,user.getLast_name());
            ptmt.setString(3,user.getUsername());
            ptmt.setString(4,pass.encrypt(user.getPassword()));
            ptmt.setString(5,user.getEmail());
            ptmt.setString(6,user.getPhone_number());
            ptmt.setString(7,user.getAddress());
            ptmt.setDate(8,new Date(user.getDate_of_birth().getTime()));
            ptmt.setString(9,user.getGender());
            int row = 0;
            row = ptmt.executeUpdate();
            if(row != 0){
                check = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
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
    // select user
    public ObservableList<User> userListLimit(int from, int to){
        User user=null;
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select * from user limit ?,? ";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,from);
            ptmt.setInt(2,to);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                user=new User();
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone_number(resultSet.getString("phone_number"));
                user.setAddress(resultSet.getString("address"));
                user.setDate_of_birth(resultSet.getDate("date_of_birth"));
                user.setGender(resultSet.getString("gender"));
                list.add(user);
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
    public ObservableList<User> userList(){
        User user=null;
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select * from user ";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                user=new User();
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone_number(resultSet.getString("phone_number"));
                user.setAddress(resultSet.getString("address"));
                user.setDate_of_birth(resultSet.getDate("date_of_birth"));
                user.setGender(resultSet.getString("gender"));
                list.add(user);
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
    //  select count user
    public int countUser(){
        int count = 0;
        try {
            connection=getConnection();
            String sql="select count(*) from user  ";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            resultSet.first();
            count = resultSet.getInt(1);
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
        return count;
    }

    public void deleteUser(String userName){
        try {
            connection = getConnection();
            String sql="delete from user where username=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,userName);
            ptmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
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

    public void updateUser(User user){
        try {
            connection = getConnection();
            String sql = "update user set first_name=?,last_name=?,email=? ," +
                    "phone_number=?, address=?,gender=? where username =?";
            ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,user.getFirst_name());
            ptmt.setString(2,user.getLast_name());
            ptmt.setString(7,user.getUsername());
            ptmt.setString(3,user.getEmail());
            ptmt.setString(4,user.getPhone_number());
            ptmt.setString(5,user.getAddress());
            ptmt.setString(6,user.getGender());
            int row = 0;
            row = ptmt.executeUpdate();
//            if(row != 0){
//                check = true;
//            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
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
}
