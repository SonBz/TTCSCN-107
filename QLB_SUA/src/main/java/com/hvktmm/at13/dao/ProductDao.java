package com.hvktmm.at13.dao;

import com.hvktmm.at13.config.MySqlDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.Product;
import com.hvktmm.at13.model.ProductItem;
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
            String sql="insert into product(name,price,capacity,product_type,company_id, amount) values (?,?,?,?,?,?)";
            ptmt=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1,product.getName());
            ptmt.setDouble(2,product.getPrice());
            ptmt.setString(3,product.getCapacity());
            ptmt.setString(4,product.getProduct_type());
            ptmt.setInt(5,product.getCompany_id());
            ptmt.setInt(6, product.getAmount());
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
                product.setPrice(resultSet.getLong("price"));
                product.setCapacity(resultSet.getString("capacity"));
                product.setProduct_type(resultSet.getString("product_type"));
                product.setCompany_id((resultSet.getInt("company_id")));
                product.setAmount(resultSet.getInt("amount"));
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

    public void deleteProduct(String name){
        try {
            connection = getConnection();
            String sql="delete from product where name=?";
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

    public void updateProduct(Product product, Integer id){
        try {
            connection=getConnection();
            String sql="update product set name=?,price=?,capacity=?,product_type=?,company_id=? where id=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,product.getName());
            ptmt.setDouble(2,product.getPrice());
            ptmt.setString(3,product.getCapacity());
            ptmt.setString(4,product.getProduct_type());
            ptmt.setInt(5,product.getCompany_id());
            ptmt.setInt(6,id);
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
    public void updateAmount(int id, int amount){
        try {
            connection=getConnection();
            String sql="update product set amount=? where id=?  ";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,amount);
            ptmt.setInt(2,id);
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
    public String productName(int id){
        String name= "";
        try {
            connection = getConnection();
            String sql="select name from product where id=?";
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

    public ObservableList<Product> idProduct(String name){
        Product product=null;
        ObservableList<Product> list = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            String sql="select id, price,amount from product where name=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,name);
            resultSet=ptmt.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                long price = resultSet.getLong("price");
                int amount = resultSet.getInt("amount");
                product = new Product(id,price,amount);
                list.add(product);
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

    public ObservableList<String> NameProduct(){
        try {
            connection = getConnection();
            String sql="select id,name from product";
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
}
