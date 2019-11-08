package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.*;
import com.hvktmm.at13.model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import comhvktmm.at13.utils.CheckField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
    private JFXButton btnExit;
    @FXML
    private Label lbResult;

    RedictUtils redictUtils = new RedictUtils();

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
        int idBill = 0;
        productBill = FXCollections.observableArrayList();
        String idCustomer = txtId.getText();
        if(txtTotalMoney.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Tạo Hóa Đơn Không Thành Công");
            alert.setContentText("Bạn hãy thêm sản phẩm ");
            alert.showAndWait();
            txtAmount.setText("");
        }else {
            long totalMoney = Long.valueOf(txtTotalMoney.getText());
            if(!idCustomer.equals("")){
                idInsert = Integer.valueOf(idCustomer);
                Customer customer = customerDao.oneCustomer(idInsert);
                Bill bill = new Bill(txtNote.getText(),Long.valueOf(txtTotalMoney.getText()), HomeController.userId,idInsert);
                // update customer
                customerDao.updateCustomer((totalMoney+customer.getMoneyTotal()),(customer.getNumberOf()+1),idInsert);
                // insert bill
                idBill = billDao.insertBill(bill);

            }else {
                if(txtName.getText().equals("")||txtAddress.getText().equals("")||txtTell.getText().equals("")){
                    lbResult.setText("");
                    lbResult.setText("Không Thành Công");
                }else{
                    // add customer
                    Customer customer1 = new Customer(txtName.getText(),txtAddress.getText(),txtTell.getText(),totalMoney);
                    idInsert = customerDao.insertCustomer(customer1);
                    customer_list.add(customer1);
                    // add bill
                    Bill bill = new Bill(txtNote.getText(),Long.valueOf(txtTotalMoney.getText()), HomeController.userId,idInsert);
                    idBill = billDao.insertBill(bill);
                    lbResult.setText("");
                    lbResult.setText("Thành Công");
                }
            }
            if(txtName.getText().equals("")||txtAddress.getText().equals("")||txtTell.getText().equals("")) {
                lbResult.setText("");
                lbResult.setText("Không Thành Công");
            }else {
                productBill = tbBillProduct.getItems();
                for (int i=0 ; i<= productBill.size(); i++) {
                    product = productDao.idProduct(billIterm.getName());
                    long price = product.get(0).getPrice();
                    int amount = product.get(0).getAmount();
                    // update amount product
                    productDao.updateAmount(billIterm.getId(), (amount - billIterm.getAmount()));
                    TransactionHistory history = new TransactionHistory(billIterm.getAmount(), txtNote.getText(), billIterm.getId(), HomeController.userId);
                    // insert history
                    historyDao.insertImport(history, 0);
                    DetailBill detailBill = new DetailBill(billIterm.getAmount(), billIterm.getTotalMoney(), price, idBill, billIterm.getId());
                    detailBillDao.insertDetailBill(detailBill);
                    lbResult.setText("");
                    lbResult.setText("Thành Công");
                    setNull();
                }
        }
        }


    }

    @FXML
    public void ClickAddProduct(ActionEvent event) {
        CheckBox checkBox = new CheckBox();
        String name = String.valueOf(cbProduct.getValue());
        product = productDao.idProduct(name);
        int idProduct = product.get(0).getId();
        long price = product.get(0).getPrice();
        int amount = product.get(0).getAmount();

        if(txtAmount.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Lỗi Thêm Sản Phẩm");
            alert.setContentText("Thêm số lượng sản phảm bạn cần mua ");
            alert.showAndWait();
        }else {
            int txAmount = Integer.valueOf(txtAmount.getText());
            if(txAmount > 0){
                idx = amount - txAmount;
                long total_money_product = price * txAmount;
                // tang tien
                total_money = total_money + total_money_product;
                if(idx >= 0 ){
                    txtTotalMoney.setText(String.valueOf(total_money));
                    billIterm = new BillIterm(idProduct,name, txAmount, total_money_product,checkBox);
                    tableProduct(billIterm);
                    txtAmount.setText("");
                    cbProduct.setValue(null);
                }else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Số Lượng Sản Phẩm Không Đủ");
                    alert.setContentText("Sản Phẩm Chỉ Còn "+amount);
                    alert.showAndWait();
                    cbProduct.getItems();
                    txtAmount.setText("");
                }
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Số lượng sản phẩm phải nhiều hơn 0");
                alert.setContentText("Bạn nhập lại số lượng ");
                alert.showAndWait();
                txtAmount.setText("");
            }
        }
    }

    @FXML
    public void ClickExit(ActionEvent event) throws Exception {
//        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
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
        tbcId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer, String> param) {
                return new ReadOnlyObjectWrapper(tbSearchCus.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbcId.setSortable(false);
//        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        txtAmount.setText("");
        cbProduct.getItems();
        tbBillProduct.getItems().clear();
    }
    public void ClickDeleteProduct(){
        ObservableList<BillIterm> deleteProductBill = FXCollections.observableArrayList();
        for(BillIterm billIterm: tbBillProduct.getItems()){
            if(billIterm.getCheckBox().isSelected()){
                deleteProductBill.add(billIterm);
                total_money = total_money - billIterm.getTotalMoney();

            }
        }
        txtTotalMoney.setText(String.valueOf(total_money));
        productBill.removeAll(deleteProductBill);
    }
}
