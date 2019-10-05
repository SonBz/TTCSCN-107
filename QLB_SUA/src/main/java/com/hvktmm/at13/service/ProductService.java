package com.hvktmm.at13.service;

import com.hvktmm.at13.dao.CompayDao;
import com.hvktmm.at13.dao.ProductDao;
import com.hvktmm.at13.model.Product;
import com.hvktmm.at13.model.ProductItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductService {
    private ObservableList<ProductItem> data= FXCollections.observableArrayList();
    private ObservableList<Product> product_list = FXCollections.observableArrayList();
    CompayDao compayDao = new CompayDao();
    ProductDao productDao = new ProductDao();

    public ObservableList<ProductItem> ProductNameCompany() {

        product_list = productDao.ProductList();
        for(int i=0; i< product_list.size();i++){
            String name =  product_list.get(i).getName();
            Long price = product_list.get(i).getPrice();
            String capacity = product_list.get(i).getCapacity();
            String  product_type = product_list.get(i).getProduct_type();
            String company = compayDao.company_name(product_list.get(i).getCompany_id());
            ProductItem productItem = new ProductItem(name,price,capacity,product_type,company);
            data.addAll(productItem);

        }
        return data;
    }
}
