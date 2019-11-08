package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.CompayDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.CompanyItem;
import com.hvktmm.at13.model.ProductItem;
import com.hvktmm.at13.model.User;
import com.hvktmm.at13.service.CompanyService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CompanyController implements Initializable {
    @FXML
    private JFXTextField txtName,txtAddress,txtTell,txtSearch;
    @FXML
    private TableView<CompanyItem> tableView;
    @FXML
    private TableColumn tbId;
    @FXML
    private TableColumn tbName;
    @FXML
    private TableColumn tbAddress;
    @FXML
    private TableColumn tbTell;
    @FXML
    private JFXButton btnExit;
    RedictUtils redictUtils = new RedictUtils();

    private ObservableList<CompanyItem> companyList = FXCollections.observableArrayList();
    private FilteredList<CompanyItem> filteredList =new FilteredList<>(companyList, e->true);
    CompanyService companyService = new CompanyService();
    CompayDao compayDao = new CompayDao();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        companyList.addAll(companyService.CompanyData());
        tbId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CompanyItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CompanyItem, String> param) {
                return new ReadOnlyObjectWrapper(tableView.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbId.setSortable(false);
        tbName.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<Company, String>("address"));
        tbTell.setCellValueFactory(new PropertyValueFactory<Company, String>("phone_number"));
        tableView.setItems(companyList);

    }


    public void ClickAddCompany(ActionEvent event) throws IOException {
        int id;
        Company company = new Company(txtName.getText(),txtTell.getText(),txtAddress.getText());
        CompayDao compayDao = new CompayDao();
        try {
            compayDao.insert(company);
            CompanyItem companyItem = new CompanyItem(txtName.getText(),txtAddress.getText(),txtTell.getText());
            companyList.add(companyItem);
            txtName.setText("");
            txtAddress.setText("");
            txtTell.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ClickClose(ActionEvent event) throws Exception {
//        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
    }

    @FXML
    public void searchUser(){
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(company ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(company.getName().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }
                else if(company.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });
        SortedList<CompanyItem> userSortedList = new SortedList<>(filteredList);
        userSortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(userSortedList);
    }

    public void clickDelete(ActionEvent event){
        CompanyItem company = tableView.getSelectionModel().getSelectedItem();
        if(company != null){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("XÁC NHẬN");
            alert.setHeaderText("Bạn có chắc chắn muốn xóa");
            alert.setContentText("Công ty : "+company.getName());
            ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
            Optional<ButtonType>result = alert.showAndWait();
            if(result.get() == buttonTypeYes){
                compayDao.deleteCompany(company.getName());
                companyList.removeAll(tableView.getSelectionModel().getSelectedItem());
            }
        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Lỗi Xóa Nhà Cung Cấp");
            alert.setContentText("Bạn cần chọn nhà cung cấp muốn xóa ");
            alert.showAndWait();
        }

    }
}
