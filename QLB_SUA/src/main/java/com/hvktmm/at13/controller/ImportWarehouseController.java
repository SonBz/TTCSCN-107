package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.ProductDao;
import com.hvktmm.at13.dao.TransactionHistoryDao;
import com.hvktmm.at13.model.Product;
import com.hvktmm.at13.model.TransactionHistory;
import com.hvktmm.at13.model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Date;

public class ImportWarehouseController implements Initializable {

    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXComboBox cbProduct;
    @FXML
    private JFXTextArea txtNote;
    @FXML
    private JFXButton btnExit;
    @FXML
    private Label txtNameUser,txtDay;
    RedictUtils redictUtils = new RedictUtils();

    ProductDao productDao= new ProductDao();
    TransactionHistoryDao dao = new TransactionHistoryDao();
    private ObservableList<String> productName = FXCollections.observableArrayList();
    ObservableList<Product> product = FXCollections.observableArrayList();

    public void ClickClose(ActionEvent event) throws Exception {
//        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       productName = productDao.NameProduct();
       cbProduct.getItems().addAll(productName);
       cbProduct.getItems().add(productName.get(0));
       cbProduct.setValue(productName.get(0));
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
        txtNameUser.setText(HomeController.name);
        txtDay.setText(ft.format(dNow));

    }

    public void ClickAdd(ActionEvent event){
        String name = String.valueOf(cbProduct.getValue());
        product = productDao.idProduct(name);
        int idProduct = product.get(0).getId();
        int updateAmount = product.get(0).getAmount() + Integer.valueOf(txtAmount.getText());
        TransactionHistory importData = new TransactionHistory(Integer.valueOf(txtAmount.getText()),
                                                txtNote.getText(),idProduct,HomeController.userId);
        Boolean check = dao.insertImport(importData,1);
        if (check=true){
            productDao.updateAmount(idProduct,updateAmount);
            txtAmount.setText("");

        }else {
            System.out.println("sai");
        }

    }
}
