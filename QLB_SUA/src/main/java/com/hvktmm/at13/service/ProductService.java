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
        data.clear();
        product_list = productDao.ProductList();
        for(int i=0; i< product_list.size();i++){
            int id = product_list.get(i).getId();
            String name =  product_list.get(i).getName();
            Long price = product_list.get(i).getPrice();
            String capacity = product_list.get(i).getCapacity();
            String  product_type = product_list.get(i).getProduct_type();
            int company_id = product_list.get(i).getCompany_id();
            String company = compayDao.company_name(company_id);
            int amount = product_list.get(i).getAmount();
            if(amount == 0){
                String strAmount = "hết hàng";
                ProductItem productItem = new ProductItem(id,company_id,name,price,capacity,product_type,company,strAmount);
                data.addAll(productItem);
            }else {
                String strAmount = String.valueOf(amount);
                ProductItem productItem = new ProductItem(id,company_id,name,price,capacity,product_type,company,strAmount);
                data.addAll(productItem);
            }

        }
        return data;
    }
}
