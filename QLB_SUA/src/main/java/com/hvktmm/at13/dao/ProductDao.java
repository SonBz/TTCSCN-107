package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProductDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    private Connection getConnection() throws SQLException {
        Connection con;
        con= MySqlDao.getInstance().getConnection();
        return con;
    }

    public int insert(Product product){
        int generatedKey = 0;
//        MemberDao memberDao=new MemberDao();
        try {
            connection=getConnection();
            String sql="insert into product(name,price,capacity,product_type,company_id) values (?,?,?,?,?)";
            ptmt=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1,product.getName());
            ptmt.setDouble(2,product.getPrice());
            ptmt.setString(3,product.getCapacity());
            ptmt.setString(4,product.getProduct_type());
            ptmt.setInt(5,product.getCompany_id());
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

    public ObservableList<Product> ProductList(){
        Product product=null;
        ObservableList<Product> list = FXCollections.observableArrayList();
        try {
            connection=getConnection();
            String sql="select * from product ";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                product=new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCapacity(resultSet.getString("capacity"));
                product.setProduct_type(resultSet.getString("product_type"));
                product.setCompany_id((resultSet.getInt("company_id")));
                list.add(product);
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
