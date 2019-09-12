package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
//    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con= MySqlDao.getInstance().getConnection();
        return con;
    }
    public User login(String username, String password){
        User user=null;

        try {

            connection=getConnection();
            String sql="select * from user where username=? and password=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,username);
            ptmt.setString(2,password);     //pass.encrypt(password)
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

}
