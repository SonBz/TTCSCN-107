package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.CompanyItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompayDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    private Connection getConnection() throws SQLException {
        Connection con;
        con= MySqlDao.getInstance().getConnection();
        return con;
    }

    public int insert(Company company){
        int generatedKey = 0;
//        MemberDao memberDao=new MemberDao();
        try {
            connection=getConnection();
            String sql="insert into company(name,phone_number,address) values (?,?,?)";
            ptmt=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1,company.getName());
            ptmt.setString(2,company.getPhone_number());
            ptmt.setString(3,company.getAddress());
            ptmt.execute();
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
    public ObservableList<Company> companyList(){
        Company company=null;
        ObservableList<Company> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select * from company ";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                company=new Company();
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                company.setAddress(resultSet.getString("address"));
                company.setPhone_number(resultSet.getString("phone_number"));
                list.add(company);
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

    public ObservableList<String> NameCompany(){
        try {
            connection = getConnection();
            String sql="select name from company";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();
            while (resultSet.next()){
                list.add(resultSet.getString("name"));
            }
            return  list;
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
        return null;
    }
    public String company_name(int id){
        String name= "";
        try {
            connection = getConnection();
            String sql="select name from company where id=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,id);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
            return  name;
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
        return name;

    }

    public int idCompany(String name){
        int id= -1;
        try {
            connection = getConnection();
            String sql="select id from company where name=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,name);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("id");
            }
            return  id;
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
        return id;

    }
    public void deleteCompany(String name){
        try {
            connection = getConnection();
            String sql="delete from company where name=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,name);
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
}
