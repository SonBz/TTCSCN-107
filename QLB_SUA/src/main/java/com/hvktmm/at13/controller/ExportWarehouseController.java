package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.*;
import com.hvktmm.at13.model.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import comhvktmm.at13.utils.CheckField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.beans.IntrospectionException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExportWarehouseController implements Initializable {

    @FXML
    private JFXTextField txtSearch,txtId,txtName,txtTell,txtAddress, txtAmount, txtTotalMoney;
    @FXML
    private JFXTextArea txtNote;
    @FXML
    private TableView<BillIterm> tbBillProduct;
    @FXML
    private TableView tbSearchCus;
    @FXML
    private TableColumn tbcId,tbcCusTell, tbcCustomer;
    @FXML
    private JFXComboBox cbProduct;
    @FXML
    private TableColumn tbcNamePr,tbcAmount,tbcMoney;

    @FXML
    private TableColumn tbcDelete;
    private ObservableList<Customer> customer_list = FXCollections.observableArrayList();
    private FilteredList<Customer> filteredList =new FilteredList<>(customer_list, e->true);
    private ObservableList<String> productName = FXCollections.observableArrayList();
    ObservableList<Product> product = FXCollections.observableArrayList();
    ObservableList<BillIterm> productBill = FXCollections.observableArrayList();
    CustomerDao customerDao = new CustomerDao();
    TransactionHistoryDao historyDao = new TransactionHistoryDao();
    ProductDao productDao= new ProductDao();
    BillDao billDao = new BillDao();
    DetailBillDao detailBillDao = new DetailBillDao();
    Customer customer = new Customer();
    BillIterm billIterm ;
    long total_money = 0;
    int idx  = 0;
    @FXML
    public void ClickAddBill(ActionEvent event) {
        int idInsert = 0;
        String name = String.valueOf(cbProduct.getValue());
        product = productDao.idProduct(name);
        int idProduct = product.get(0).getId();
        long price = product.get(0).getPrice();
        int amount = product.get(0).getAmount();
        productBill = FXCollections.observableArrayList();
        String idCustomer = txtId.getText();
        long totalMoney = Long.valueOf(txtTotalMoney.getText());
        if(idCustomer!=null){
            idInsert = Integer.valueOf(idCustomer);
            Customer customer = customerDao.oneCustomer(idInsert);
            Bill bill = new Bill(txtNote.getText(),Long.valueOf(txtTotalMoney.getText()), HomeController.userId,idInsert);
            // update customer
            customerDao.updateCustomer((totalMoney+customer.getMoneyTotal()),(customer.getNumberOf()+1),idInsert);
            // insert bill
            billDao.insertBill(bill);

        }else {
            // add customer
            Customer customer1 = new Customer(txtName.getText(),txtAddress.getText(),txtTell.getText(),totalMoney);
            idInsert = customerDao.insertCustomer(customer1);
            customer_list.add(customer1);
            // add bill
            Bill bill = new Bill(txtNote.getText(),Long.valueOf(txtTotalMoney.getText()), HomeController.userId,idInsert);
            billDao.insertBill(bill);
        }
        productBill = tbBillProduct.getItems();
        for(BillIterm billIterm : productBill){
            // update amount product
            productDao.updateAmount(billIterm.getId(),(amount - billIterm.getAmount()));
            TransactionHistory history = new TransactionHistory(billIterm.getAmount(),txtNote.getText(),billIterm.getId(),HomeController.userId);
            // insert history
            historyDao.insertImport(history,0);
            DetailBill detailBill = new DetailBill(billIterm.getAmount(),billIterm.getTotalMoney(),price,idInsert,billIterm.getId());
            detailBillDao.insertDetailBill(detailBill);
        }
        setNull();
    }

    @FXML
    public void ClickAddProduct(ActionEvent event) {
        CheckBox checkBox = new CheckBox();
        String name = String.valueOf(cbProduct.getValue());
        product = productDao.idProduct(name);
        int idProduct = product.get(0).getId();
        long price = product.get(0).getPrice();
        int amount = product.get(0).getAmount();
        int txAmount = Integer.valueOf(txtAmount.getText());
        idx = amount - txAmount;
        long total_money_product = price * txAmount;
        // tang tien
        total_money = total_money + total_money_product;
        txtTotalMoney.setText(String.valueOf(total_money));
        if(idx >= 0 ){
            billIterm = new BillIterm(idProduct,name, txAmount, total_money_product,checkBox);
            tableProduct(billIterm);
            txtAmount.setText("");
            cbProduct.setValue(null);
        }else {
            System.out.println("het");
        }

    }

    @FXML
    public void ClickExit(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CheckField checkField = new CheckField();
        tableCustomer();
        productName = productDao.NameProduct();
        cbProduct.getItems().addAll(productName);
        try {
            checkField.checkString(txtName);
            checkField.checkInt(txtTell);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void tableCustomer(){
        customer_list.addAll(customerDao.customersList());
        tbcCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcCusTell.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbSearchCus.setItems(customer_list);
    }

    @FXML
    public void searchCustomer(){
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customer ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(customer.getName().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }
                return false;
            });
        });
        SortedList<Customer> userSortedList = new SortedList<>(filteredList);
        userSortedList.comparatorProperty().bind(tbSearchCus.comparatorProperty());
        tbSearchCus.setItems(userSortedList);
    }

    public void onTableClick(){
        customer = (Customer) tbSearchCus.getSelectionModel().getSelectedItem();
        if(customer != null){
            txtId.setText(String.valueOf(customer.getId()));
            txtName.setText(customer.getName());
            txtTell.setText(customer.getPhoneNumber());
            txtAddress.setText(customer.getAddress());
        }
    }

    public void tableProduct(BillIterm billIterm){

        productBill.addAll(billIterm);
        tbcNamePr.setCellValueFactory(new PropertyValueFactory<Object,String>("name"));
        tbcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbcMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
        tbcDelete.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tbBillProduct.setItems(productBill);
        tbBillProduct.setEditable(true);

        tbcDelete.setEditable(true);

    }
    public void setNull(){
        txtId.setText("");
        txtTotalMoney.setText("");
        txtAddress.setText("");
        txtTell.setText("");
        txtName.setText("");
        txtNote.setText("");
        cbProduct.getItems();
    }
    public void ClickDeleteProduct(){
        ObservableList<BillIterm> deleteProductBill = FXCollections.observableArrayList();
        for(BillIterm billIterm: tbBillProduct.getItems()){
            if(billIterm.getCheckBox().isSelected()){
                deleteProductBill.add(billIterm);
            }
        }
        productBill.removeAll(deleteProductBill);
    }
}
