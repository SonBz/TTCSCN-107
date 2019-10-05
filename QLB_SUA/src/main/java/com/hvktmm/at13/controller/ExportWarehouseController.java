package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.CustomerDao;
import com.hvktmm.at13.dao.ProductDao;
import com.hvktmm.at13.model.BillIterm;
import com.hvktmm.at13.model.Customer;
import com.hvktmm.at13.model.Product;
import com.hvktmm.at13.model.ProductItem;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ExportWarehouseController implements Initializable {

    @FXML
    private JFXTextField txtSearch,txtId,txtName,txtTell,txtAddress, txtAmount, txtTotalMoney;

    @FXML
    private TableView tbSearchCus,tbBillProduct;
    @FXML
    private TableColumn tbcId,tbcCusTell, tbcCustomer;
    @FXML
    private JFXComboBox cbProduct;
    @FXML
    private TableColumn tbcNamePr,tbcAmount,tbcMoney;

    @FXML
    private TableColumn<?, ?> tbcDelete;
    private ObservableList<Customer> customer_list = FXCollections.observableArrayList();
    private FilteredList<Customer> filteredList =new FilteredList<>(customer_list, e->true);
    private ObservableList<String> productName = FXCollections.observableArrayList();
    ObservableList<Product> product = FXCollections.observableArrayList();
    ObservableList<BillIterm> productBill = FXCollections.observableArrayList();
    CustomerDao customerDao = new CustomerDao();
    ProductDao productDao= new ProductDao();
    Customer customer = new Customer();
    BillIterm billIterm ;
    long total_money = 0;
    @FXML
    public void ClickAddBill(ActionEvent event) {

    }

    @FXML
    public void ClickAddProduct(ActionEvent event) {
        String name = String.valueOf(cbProduct.getValue());
        product = productDao.idProduct(name);
        int idProduct = product.get(0).getId();
        long price = product.get(0).getPrice();
        int amount = product.get(0).getAmount();
        int txAmount = Integer.valueOf(txtAmount.getText());
        int idx = amount - txAmount;
        long total_money_product = price * txAmount;
        total_money = total_money + total_money_product;
        txtTotalMoney.setText(String.valueOf(total_money));
        if(idx >= 0 ){
            billIterm = new BillIterm(name, txAmount, total_money_product);
            tableProduct(billIterm);
        }else {
            System.out.println("het");
        }

    }

    @FXML
    public void ClickExit(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableCustomer();
        productName = productDao.NameProduct();
        cbProduct.getItems().addAll(productName);
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
        tbBillProduct.setItems(productBill);
        tbBillProduct.setEditable(true);
        tbcDelete.setCellFactory(tableColumCheckBox());

    }

    protected Callback tableColumCheckBox() {
        Callback<TableColumn<BillIterm, String>, TableCell<BillIterm, String>> cellCallback = (pagram) -> {
            final TableCell<BillIterm, String> tableCell = new TableCell<BillIterm, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final CheckBox checkBox =new CheckBox();
                        setGraphic(checkBox);
                        setText(null);
                    }
                }
            };
            return tableCell;
        };
        return cellCallback;
    }
}
