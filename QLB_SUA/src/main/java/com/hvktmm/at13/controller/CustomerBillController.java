package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.BillDao;
import com.hvktmm.at13.dao.CustomerDao;
import com.hvktmm.at13.dao.DetailBillDao;
import com.hvktmm.at13.model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.BitSet;
import java.util.ResourceBundle;

public class CustomerBillController implements Initializable {
        @FXML
        private TableView tableCustomer;
        @FXML
        private TableColumn tbId, tbIdCustomer,tbNameCustomer,tbAddress, tbTell, tbMoneyCustomer;
        @FXML
        private TableView tableBill;
        @FXML
        private TableColumn tbBill,tbDateBill,tbNoteBill,tbMoneyBil,tbUserBill;
        @FXML
        private TableView tableBillDetail;
        @FXML
        private TableColumn tbIdDetail,tbAmount,tbPrice,tbMoneyDetail,tbNameProduct;
        @FXML
        private JFXButton btnExit;
        @FXML
        private JFXTextField txtSearch;

        Customer customer = new Customer();
        CustomerDao customerDao = new CustomerDao();
        BillDao billDao = new BillDao();
        DetailBillDao detailBillDao = new DetailBillDao();
    RedictUtils redictUtils = new RedictUtils();

    private ObservableList<Customer> customer_list = FXCollections.observableArrayList();
    FilteredList<Customer> filteredList =new FilteredList<>(customer_list, e->true);
    private ObservableList<BillIterm> billCustomerList = FXCollections.observableArrayList();
    private ObservableList<DetailBillIterm> detailBillList = FXCollections.observableArrayList();


    public void ClickExit(ActionEvent event) throws Exception {
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableCustomer();
        billCustomerList = billDao.billList();
        detailBillList = detailBillDao.detailBill();
        tableBill(billCustomerList);
        tableBillDetail(detailBillList);

    }

    public void tableCustomer(){
        customer_list.addAll(customerDao.customersList());
        tbId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductItem, String> param) {
                return new ReadOnlyObjectWrapper(tableCustomer.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbId.setSortable(false);
        tbNameCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbTell.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tbIdCustomer.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbMoneyCustomer.setCellValueFactory(new PropertyValueFactory<>("moneyTotal"));
        tableCustomer.setItems(customer_list);

//        tableBill();


    }
    public void tableBill(ObservableList<BillIterm> billCustomerList){
//        tableBill.getItems().clear();
        tbBill.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductItem, String> param) {
                return new ReadOnlyObjectWrapper(tableBill.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbBill.setSortable(false);
        tbDateBill.setCellValueFactory(new PropertyValueFactory<>("dateTrading"));
        tbNoteBill.setCellValueFactory(new PropertyValueFactory<>("note"));
        tbMoneyBil.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
        tbUserBill.setCellValueFactory(new PropertyValueFactory<>("user"));
        tableBill.setItems(billCustomerList);

    }
    public void tableBillDetail(ObservableList<DetailBillIterm> detailBillList){
        tbIdDetail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductItem, String> param) {
                return new ReadOnlyObjectWrapper(tableBillDetail.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbIdDetail.setSortable(false);
        tbAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbMoneyDetail.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
        tbNameProduct.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        tableBillDetail.setItems(detailBillList);

    }
    public void onTableCustomerClick(){
        ObservableList<BillIterm> billCustomerList = FXCollections.observableArrayList();
        Customer customer = (Customer) tableCustomer.getSelectionModel().getSelectedItem();
        billCustomerList.addAll(billDao.billCustomerList(customer.getId()));
        tableBill(billCustomerList);
    }
    public void onTableBillClick(){
        ObservableList<DetailBillIterm> detailBillList = FXCollections.observableArrayList();
        BillIterm billIterm = (BillIterm) tableBill.getSelectionModel().getSelectedItem();
        detailBillList.addAll(detailBillDao.detailBillList(billIterm.getId()));
        tableBillDetail(detailBillList);
    }

    public void searchCustomer(){
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customer ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(customer.getName().toLowerCase().indexOf(lowerCaseFilter) !=-1 ){
                    return true;
                }

                return false;
            });
        });
        SortedList<Customer> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableCustomer.comparatorProperty());
        tableCustomer.setItems(sortedList);
    }
//    public void  clickTableCustomer(){
//        tableCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                ObservableList<BillIterm> billCustomerList = FXCollections.observableArrayList();
//                Customer customer = (Customer) tableCustomer.getSelectionModel().getSelectedItem();
//                billCustomerList.addAll(billDao.billCustomerList(customer.getId()));
//                tableBill(billCustomerList);
//            }
//        });
//    }
}
