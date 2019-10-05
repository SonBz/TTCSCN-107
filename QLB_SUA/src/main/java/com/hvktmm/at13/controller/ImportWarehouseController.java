package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.ProductDao;
import com.hvktmm.at13.dao.TransactionHistoryDao;
import com.hvktmm.at13.model.Product;
import com.hvktmm.at13.model.TransactionHistory;
import com.hvktmm.at13.model.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportWarehouseController implements Initializable {

    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXComboBox cbProduct;
    @FXML
    private JFXTextArea txtNote;

    ProductDao productDao= new ProductDao();
    TransactionHistoryDao dao = new TransactionHistoryDao();
    private ObservableList<String> productName = FXCollections.observableArrayList();
    ObservableList<Product> product = FXCollections.observableArrayList();

    public void ClickClose(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       productName = productDao.NameProduct();
       cbProduct.getItems().addAll(productName);
       cbProduct.getItems().add(productName.get(0));
       cbProduct.setValue(productName.get(0));

    }

    public void ClickAdd(ActionEvent event){
        String name = String.valueOf(cbProduct.getValue());
        product = productDao.idProduct(name);
        int idProduct = product.get(0).getId();
        int updateAmount = product.get(0).getAmount() + Integer.valueOf(txtAmount.getText());
        TransactionHistory importData = new TransactionHistory(Integer.valueOf(txtAmount.getText()),
                                                txtNote.getText(),idProduct,HomeController.userId);
        Boolean check = dao.insertImport(importData);
        if (check=true){
            productDao.updateAmount(idProduct,updateAmount);
        }else {
            System.out.println("sai");
        }

    }
}
